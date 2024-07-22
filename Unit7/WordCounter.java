/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 05 07, 2024
 * PROJECT NAME: WordCounter.java
 * DESCRIPTION: WordCounter
 * worked with Carlos, Nassir, Jonathan, Nurlan, Trace, and Kierra
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {
    public static void main(String[] args) {
        // check for command line arguments
        if (args.length == 0) { // if there's none show user how to run the program
            System.out.println("Usage: java WordCounter (input filename) (output filename)");
            return;
        }

        // set variables with input and output file names
        String inputFileName = args[0];
        String outputFileName = args.length == 1 ? getOutputFileName(inputFileName) : args[1];

        // try to access input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
             PrintWriter writer = new PrintWriter(outputFileName)) {

            // make two maps, one for the word count and another to keep track of what's
            // always capital
            Map<String, Integer> wordCounts = new TreeMap<>();
            Map<String, Boolean> alwaysCapital = new TreeMap<>();

            String line; // read through the file
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String rawWord : words) {
                    if (!rawWord.isEmpty()) {
                        String word = rawWord.replaceAll("^[^a-zA-Z']+|[^a-zA-Z']+$", "");
                        if (!word.isEmpty()) {
                            String wordLower = word.toLowerCase();
                            boolean isCapital = Character.isUpperCase(word.charAt(0));

                            // update count case-insensitivly
                            wordCounts.put(wordLower, wordCounts.getOrDefault(wordLower, 0) + 1);

                            // determine if the word is always capitalized
                            alwaysCapital.compute(wordLower, (k, v) -> (v == null) ? isCapital : v && isCapital);
                        }
                    }
                }
            }

            // make array list of the sorted words
            List<String> sortedWords = new ArrayList<>(wordCounts.keySet());
            sortedWords.sort(String.CASE_INSENSITIVE_ORDER);

            // print out the words to display and file
            for (String word : sortedWords) {
                int count = wordCounts.get(word);
                // decide on display case
                String displayWord = alwaysCapital.get(word) ? word.substring(0, 1).toUpperCase() + word.substring(1)
                        : word;
                if (count > 1) { // if the count is larger than 1 show the count
                    writer.println(displayWord + " (" + count + ")");
                    System.out.println(displayWord + " (" + count + ")");
                } else { // otherwise just display the word
                    writer.println(displayWord);
                    System.out.println(displayWord);
                }
            }
        } catch (IOException e) { // exception catch
            System.err.println("Error reading or writing file: " + e.getMessage());
        }

        System.out.println("Word counts written to " + outputFileName);
    }

    // put .out on the end of the output file.

    private static String getOutputFileName(String inputFileName) {
        int dotIndex = inputFileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return inputFileName.substring(0, dotIndex) + ".out";
        } else {
            return inputFileName + ".out";
        }
    }
}
