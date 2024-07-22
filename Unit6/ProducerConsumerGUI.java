/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 04 16, 2024
 * PROJECT NAME: ConcurrencyGUI.java
 * DESCRIPTION: concurrencyGUI
 * worked with Carlos, Trace, Nassir, Luke, Kierra
 */
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.Random;

public class ProducerConsumerGUI extends JFrame {
    private final JTextField numProducersField = new JTextField("2");
    private final JTextField numConsumersField = new JTextField("2");
    private final JTextField averageProducedField = new JTextField("6");
    private final JTextField averageConsumedField = new JTextField("6");
    private final JTextArea logArea = new JTextArea();
    private final JLabel resourceLabel = new JLabel("Resource: 0");
    private ExecutorService pool;
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public ProducerConsumerGUI() {
        setTitle("Producer Consumer Simulation");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(5, 2));
        northPanel.add(new JLabel("Number of Producers:"));
        northPanel.add(numProducersField);
        northPanel.add(new JLabel("Number of Consumers:"));
        northPanel.add(numConsumersField);
        northPanel.add(new JLabel("Average Value Produced:"));
        northPanel.add(averageProducedField);
        northPanel.add(new JLabel("Average Value Consumed:"));
        northPanel.add(averageConsumedField);
        JButton startButton = new JButton("Start");
        northPanel.add(startButton);

        add(northPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(logArea);
        logArea.setEditable(false);
        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(resourceLabel);
        add(southPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startSimulation());
    }

    private void startSimulation() {
        if (pool != null) {
            pool.shutdownNow();
            logArea.setText("");
        }
        queue.clear();
        resourceLabel.setText("Resource: 0");

        pool = Executors.newCachedThreadPool();
        int numProducers = Integer.parseInt(numProducersField.getText());
        int numConsumers = Integer.parseInt(numConsumersField.getText());
        int averageProduced = Integer.parseInt(averageProducedField.getText());
        int averageConsumed = Integer.parseInt(averageConsumedField.getText());

        for (int i = 0; i < numProducers; i++) {
            pool.execute(new Producer(queue, averageProduced, this));
        }
        for (int i = 0; i < numConsumers; i++) {
            pool.execute(new Consumer(queue, averageConsumed, this));
        }
    }

    public synchronized void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    public synchronized void updateResource(int value) {
        SwingUtilities.invokeLater(() -> resourceLabel.setText("Resource: " + value));
    }

    class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int averageValue;
        private final ProducerConsumerGUI gui;

        Producer(BlockingQueue<Integer> queue, int averageValue, ProducerConsumerGUI gui) {
            this.queue = queue;
            this.averageValue = averageValue;
            this.gui = gui;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (!Thread.currentThread().isInterrupted()) {
                int value = averageValue - 3 + random.nextInt(7);
                try {
                    queue.put(value);
                    gui.log("Produced: " + value);
                    gui.updateResource(queue.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;
        private final int averageValue;
        private final ProducerConsumerGUI gui;

        Consumer(BlockingQueue<Integer> queue, int averageValue, ProducerConsumerGUI gui) {
            this.queue = queue;
            this.averageValue = averageValue;
            this.gui = gui;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    int value = queue.take();
                    gui.log("Consumed: " + value);
                    gui.updateResource(queue.size());
                    Thread.sleep(1000 + random.nextInt(500));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProducerConsumerGUI().setVisible(true);
        });
    }
}
