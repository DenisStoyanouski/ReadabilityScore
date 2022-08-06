package readability;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Hello world!");
    }

    private static String getInput() throws IOException {
        String input = "";
        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextLine();
        }
        return input;
    }


}
