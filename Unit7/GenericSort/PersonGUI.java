/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: PersonMain.java
 * DESCRIPTION: PersonMain
 * worked with Carlos, Nassir, Trace, Kierra, Luke
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonGUI extends JFrame {

    private ArrayList<Person> personList;
    private JComboBox<Person> personComboBox;
    private JMenuItem saveMenuItem;
    LocalDate dateOfBirth;
    public PersonGUI() {
        super("Person Hierarchy Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        personList = new ArrayList<>();

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open...");
        saveMenuItem = new JMenuItem("Save");
        JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
        JMenuItem deleteMenuItem = new JMenuItem("Delete Person");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(deleteMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // Create help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenu.add(helpMenuItem);
        menuBar.add(helpMenu);

        // Add action listeners
        openMenuItem.addActionListener(e -> openFile());
        saveMenuItem.addActionListener(e -> saveFile());
        saveAsMenuItem.addActionListener(e -> saveAsFile());
        deleteMenuItem.addActionListener(e -> deletePerson());
        exitMenuItem.addActionListener(e -> System.exit(0));
        helpMenuItem.addActionListener(e -> showHelpDialog());

        setJMenuBar(menuBar);

        // Create GUI components
        JPanel mainPanel = new JPanel(new BorderLayout());

        personComboBox = new JComboBox<>();
        mainPanel.add(personComboBox, BorderLayout.NORTH);

        JButton editButton = new JButton("Edit Person");
        mainPanel.add(editButton, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Person");
        mainPanel.add(addButton, BorderLayout.SOUTH);

        add(mainPanel);

        // Set up event listeners
        addButton.addActionListener(e -> addPerson());
        editButton.addActionListener(e -> editPerson());

        // Disable save menu item initially
        saveMenuItem.setEnabled(false);

        setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                personList = (ArrayList<Person>) ois.readObject();
                refreshComboBox();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        saveDataToFile("person_data.txt");
    }

    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            saveDataToFile(fileChooser.getSelectedFile().getAbsolutePath());
            // Enable save menu item after Save As
            saveMenuItem.setEnabled(true);
        }
    }

    private void showHelpDialog() {
        JOptionPane.showMessageDialog(this, "Help information goes here.");
    }

    private void editPerson() {
        if (personComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No persons available for editing.");
            return;
        }

        Person selectedPerson = (Person) personComboBox.getSelectedItem();

        String newFirstName = JOptionPane.showInputDialog(this, "Enter new first name:", selectedPerson.getFirstName());
        String newLastName = JOptionPane.showInputDialog(this, "Enter new last name:", selectedPerson.getLastName());

        selectedPerson.setFirstName(newFirstName);
        selectedPerson.setLastName(newLastName);

        refreshComboBox();
    }

    private void addPerson() {
        String firstName = JOptionPane.showInputDialog(this, "Enter first name:");
        String lastName = JOptionPane.showInputDialog(this, "Enter last name:");

        String[] options = {"Regular Person", "Registered Person", "OCCC Person"};
        int choice = JOptionPane.showOptionDialog(this, "Select person type:", "Person Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Person person = null;

        switch (choice) {
            case 0: // Regular Person

                person = new Person(firstName, lastName, dateOfBirth);
                break;
            case 1: // Registered Person
                String dateOfBirthString = JOptionPane.showInputDialog(this, "Enter date of birth (yyyy-MM-dd):");
                if (dateOfBirthString != null && !dateOfBirthString.isEmpty()) {
                    LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
                    String govID = JOptionPane.showInputDialog(this, "Enter government ID:");
                    if (govID != null && !govID.isEmpty()) {
                        person = new RegisteredPerson(firstName, lastName, dateOfBirth, govID);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid government ID.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid date of birth.");
                    return;
                }
                break;
            case 2: // OCCC Person
                if (!personList.isEmpty()) {
                    Person selectedPerson = (Person) JOptionPane.showInputDialog(this, "Select Registered Person:",
                            "Select Registered Person", JOptionPane.QUESTION_MESSAGE, null,
                            personList.toArray(), personList.get(0));
                    String studentID = JOptionPane.showInputDialog(this, "Enter student ID:");
                    if (selectedPerson != null && selectedPerson instanceof RegisteredPerson) {
                        person = new OCCCPerson((RegisteredPerson) selectedPerson, studentID);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid selection or person type.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No Registered Persons available.");
                    return;
                }
                break;
            default:
                // Should not happen
                JOptionPane.showMessageDialog(this, "Invalid selection.");
                return;
        }

        personList.add(person);
        refreshComboBox();
    }

    private void deletePerson() {
        if (personComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No persons available for deletion.");
            return;
        }

        int selectedIndex = personComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            personList.remove(selectedIndex);
            refreshComboBox();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a person to delete.");
        }
    }

    private void saveDataToFile(String filename) {
        if (personList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data to save.");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(personList);
            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error: File not found. Please check the file path.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshComboBox() {
        personComboBox.removeAllItems();
        for (Person person : personList) {
            personComboBox.addItem(person);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PersonGUI::new);
    }
}


