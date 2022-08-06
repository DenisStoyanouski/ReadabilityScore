package readability;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final int NUMBER_OF_SYMBOLS = 100;
    public static void main(String[] args) throws IOException {
        isConsidered(getInput());
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


}
