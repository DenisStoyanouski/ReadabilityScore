package readability;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final int NUMBER_OF_SYMBOLS = 100;
    public static void main(String[] args) throws IOException {
        String line = getInput();
        isReadable(countAmountOfWords(line), countAmountOfSentences(line));
    }

    private static String getInput() throws IOException {
        String input = "";
        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextLine();
        }
        return input;
    }

    private static void isConsidered(String input) {
        if (input.length() > NUMBER_OF_SYMBOLS) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }

    private static int countAmountOfSentences(String input) {
        var sentences = input.split("[.?!]");
        return sentences.length;
    }

    private static int countAmountOfWords(String input) {
        var words = input.split("\\s+");
        return words.length;
    }

    private static void isReadable(int amountOfWords, int amountOfSentences) {

        double readable = (double) amountOfWords / amountOfSentences;
        if (readable > 10) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }


}
