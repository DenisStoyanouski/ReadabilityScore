package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static int words = 0;
    private static int sentences = 0;
    private static int characters = 0;

    static final int NUMBER_OF_SYMBOLS = 100;
    public static void main(String[] args) throws IOException {
        String line = getInput(args[0]);
        printResult(line, getScore(line));

    }

    private static String getInput(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static void isConsidered(String input) {
        if (input.length() > NUMBER_OF_SYMBOLS) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }

    private static void countSentences(String input) {
        var line = input.split("[.?!]");
        sentences =  line.length;
    }

    private static void countWords(String input) {
        var line = input.split("\\s+");
        words =  line.length;
    }

    private static void countCharacters(String input) {
        String line = input.replaceAll("[\\s|\\n|\\t]", "");
        characters = line.length();
    }

    private static float getScore(String line) {
        float readable;
        countSentences(line);
        countWords(line);
        countCharacters(line);

        readable =  4.71f * characters / words + 0.5f * words/sentences - 21.43f;

        return readable;
    }

    private static void printResult(String line, float score) {
        System.out.println("The text is:");
        System.out.println(line);
        System.out.printf("Words: %d%n", words);
        System.out.printf("Sentences: %d%n", sentences);
        System.out.printf("Characters: %d%n", characters);
        System.out.printf("The score is: %f%n", score);
        System.out.printf("This text should be understood by %s-year-olds", getRage(score));
    }

    private static String getRage(float score) {
        String rage = "";
        switch ((int) Math.ceil(score)) {
            case 1: rage = "5-6";
            break;
            case 2 : rage = "6-7";
            break;
            case 3 : rage = "7-8";
            break;
            case 4 : rage = "8-9";
            break;
            case 5 : rage = "9-10";
            break;
            case 6 : rage = "10-11";
                break;
            case 7 : rage = "11-12";
                break;
            case 8 : rage = "12-13";
                break;
            case 9 : rage = "13-14";
                break;
            case 10 : rage = "14-15";
                break;
            case 11 : rage = "15-16";
                break;
            case 12 : rage = "16-17";
                break;
            case 13 : rage = "17-18";
                break;
            case 14 : rage = "18-19";
                break;
            default: break;
        }
        return rage;
    }



}
