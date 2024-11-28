import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UniqueCompoundWordFinder {
    public static void main(String[] args) {
        String file1 = "Input_01.txt";
        String file2 = "Input_02.txt";
        String outputFile = "Output.txt";

        try {
            // Start timer
            long startTime = System.nanoTime();

            // Read words from both files
            List<String> words = new ArrayList<>(readWords(file1));
            words.addAll(readWords(file2));

            // Use a HashSet for quick lookups
            Set<String> wordSet = new HashSet<>(words);

            String longestCompound = "";
            String secondLongestCompound = "";

            for (String word : words) {
                if (isCompound(word, wordSet, true)) {
                    if (word.length() > longestCompound.length()) {
                        secondLongestCompound = longestCompound;
                        longestCompound = word;
                    } else if (word.length() > secondLongestCompound.length()) {
                        secondLongestCompound = word;
                    }
                }
            }

            // End timer
            long endTime = System.nanoTime();
            long timeTaken = (endTime - startTime) / 1_000_000; // Convert to milliseconds

            // Write results to output file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write("Longest Compound Word: " + longestCompound + "\n");
                writer.write("Second Longest Compound Word: " + secondLongestCompound + "\n");
                writer.write("Time Taken: " + timeTaken + " ms\n");
            }

            System.out.println("Results have been written to " + outputFile);

        } catch (IOException e) {
            System.err.println("Error reading files or writing output: " + e.getMessage());
        }
    }

    private static List<String> readWords(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    private static boolean isCompound(String word, Set<String> wordSet, boolean isOriginalWord) {
        int len = word.length();
        for (int i = 1; i < len; i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            if (wordSet.contains(prefix)) {
                if (wordSet.contains(suffix) || isCompound(suffix, wordSet, false)) {
                    return true;
                }
            }
        }
        return !isOriginalWord;
    }
}
