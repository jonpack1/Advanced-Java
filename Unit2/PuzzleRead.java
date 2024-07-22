/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 05 20, 2024
 * PROJECT NAME: PuzzleRead.java
 * DESCRIPTION:
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class PuzzleRead {
    public static void main(String[] args) {
        String fileName = ""; // Set fileName variable
        if (args.length == 1) { // If there is an argument set first argument to fileName.
            fileName = args[0];
        } else {
            System.out.println("Please enter file name: "); // Otherwise run program like usual.
            Scanner input = new Scanner(System.in);
            fileName = input.nextLine();
            input.close();
        }
        try {
            // Populate a character vector with the characters in file.
            Vector<Character> chars = readFile(fileName);
            if (isValid(chars)) { // Check the vector to make sure it is valid to use for puzzle
                char[][] puzzle = createPuzzle(chars); // Assign the vector to a 2d array for table.
                displayPuzzle(puzzle); // Display that 2d array in a table.
            } else { // If char isn't a square send error message and quit.
                System.out.println("Puzzle is not a perfect square.");
                System.exit(0);
            }

        } catch (IOException ioe) { // If there's trouble with the file send message and quit.
            System.out.println("Could not read file.");
            System.exit(0);
        }
    }

    // All this method does is read the file into the vector.
    public static Vector<Character> readFile(String fileName) throws IOException {
        Vector<Character> chars = new Vector<>();
        Scanner s = new Scanner(new File(fileName));

        // input loop
        while (s.hasNext()) {
            char c = s.next().charAt(0);
            chars.add(c);
        }

        s.close();
        // return the vector
        return chars;
    }

    // This method is for validation.
    public static boolean isValid(Vector<Character> chars) {

        for (char ch : chars) {
            // This mess of a validation loop checks every character to make sure it fits in
            // our standards.
            if (!((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'z') || ch == '*'
                    || ch == '-')) {
                // If there is a character that doesn't: Display error message and quit.
                System.out.println("Only accepted values are: (0-9), (a-z/A-Z), and (* or -).");
                System.exit(0);
            }
        }

        // If it's perfectly square the square root should be a whole number.
        double size = Math.sqrt(chars.size()); // Grab the double square root.
        int squaredInt = (int) Math.sqrt(chars.size()); // Grab the int casted square root.

        // This is used to make sure the square root is a whole number
        // For instance (4.6654 == 4) won't work but (4.0 == 4) will.
        if (size == squaredInt) {
            return true;
        } else {
            return false; // Else return false.
        }
    }

    // This is where the vector gets assigned to the 2d array.
    public static char[][] createPuzzle(Vector<Character> chars) {
        // Since the table is a perfect square, the width and height are the exact same.
        int size = (int) Math.sqrt(chars.size());
        char[][] puzzle = new char[size][size];

        // This populates the 2d array.
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[i][j] = chars.get(index++);
            }
        }
        return puzzle; // Return the completed array.
    }

    // Finally, we get to displaying the puzzle.
    private static void displayPuzzle(char[][] puzzle) {
        // We separate the the 2d array.
        int rows = puzzle.length;
        int cols = puzzle[0].length;

        // This finds the maximum length of each column so the output is aligned
        // correctly.
        int[] maxColLength = new int[cols];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int length = String.valueOf(puzzle[j][i]).length();
                maxColLength[i] = Math.max(maxColLength[i], length);
            }
        }

        // This code just goes through and displays all of the values.
        // It also converts everything to base10 thanks to the Character wrapper class.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char value = puzzle[i][j];
                if (value == '-' || value == '*') {
                    System.out.printf("%" + (maxColLength[j] + 1) + "s", "   ");
                } else if (Character.isDigit(value)) {
                    int base10Val = Character.getNumericValue(value);
                    System.out.printf("%-" + (maxColLength[j] + 1) + "d ", base10Val);
                } else {
                    int base10Val = Character.toUpperCase(value) - 'A' + 10;
                    System.out.printf("%-" + (maxColLength[j] + 1) + "d ", base10Val);

                }
            }
            // This is for the sake of making the command prompt look nice.
            System.out.println();
        }

    }
}