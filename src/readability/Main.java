package readability;

import java.io.IOException;
import java.util.Scanner;

public class Main {
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
        if (input.length() > 100) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }


}
