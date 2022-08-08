package readability;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int words = 0;
    private static int sentences = 0;
    private static int characters = 0;

    private static int syllables = 0;
    private static int polysyllables = 0;
    static final int NUMBER_OF_SYMBOLS = 100;
    public static void main(String[] args) throws IOException {
        String line = getInput(args[0]);
        printResult(line);

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

    private static void countSyllables(String input) {
        var line = input.split("\\s+" );
        for (String word : line) {
            String[]vowels = word.split("[^aeiouy]{0,2}[aeiouy]{1,2}[^e]");
            if (vowels.length == 0) {
                syllables += 1;
            } else {
                syllables += vowels.length;
            }
            syllables += vowels.length;
            if (vowels.length >= 3) {
                polysyllables++;
            }
        }
    }

    private static void printResult(String line) {

        countSentences(line);
        countWords(line);
        countCharacters(line);
        countSyllables(line);

        System.out.println("The text is:");
        System.out.println(line);
        System.out.printf("Words: %d%n", words);
        System.out.printf("Sentences: %d%n", sentences);
        System.out.printf("Characters: %d%n", characters);
        System.out.printf("Syllables: %d%n", syllables);
        System.out.printf("Polysyllables: %d%n", polysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        chooseIndex();
    }

    private static void chooseIndex() {
        try (Scanner scanner = new Scanner(System.in);) {
            String score = scanner.nextLine();
            switch (score) {
                case "ARI" : getScoreARI();
                break;
                case "FK" : getScoreFK();
                break;
                case "SMOG" : getScoreSMOG();
                break;
                case "CL" : getScoreCL();
                break;
                case "all" :
                    break;
                default : System.out.println("Choose correct item");
                break;
            }
        }

    }

    private static void getScoreCL() {
        float score;
        score = 0.0588f * (float) (characters / words) - 0.296f * (float) (sentences / words) - 15.8f;
        System.out.printf("Coleman–Liau index: %.2f (about 17-year-olds).%n", score);
    }

    private static void getScoreSMOG() {
        float score;
        score = (1.043f * (float) Math.sqrt((double) (polysyllables * 30 / sentences))) + 3.1291f;
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).%n", score, getRage(score));
    }

    private static void getScoreARI() {
        float score;
        score =  4.71f * characters / words + 0.5f * words/sentences - 21.43f;
        System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).%n", score, getARIRage(score));
    }

    private static void getScoreFK() {
        float score;
        score = (((0.39f * words) / sentences) + ((11.8f * syllables) / words)) - 15.59f;
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s-year-olds).%n", score, getRage(score));
    }



    private static String getARIRage(float score) {
        String rage = "";
        switch ((int) Math.ceil(score)) {
            case 1: rage = "6";
            break;
            case 2 : rage = "7";
            break;
            case 3 : rage = "8";
            break;
            case 4 : rage = "9";
            break;
            case 5 : rage = "10";
            break;
            case 6 : rage = "11";
                break;
            case 7 : rage = "12";
                break;
            case 8 : rage = "13";
                break;
            case 9 : rage = "14";
                break;
            case 10 : rage = "15";
                break;
            case 11 : rage = "16";
                break;
            case 12 : rage = "17";
                break;
            case 13 : rage = "18";
                break;
            case 14 : rage = "19";
                break;
            default: break;
        }
        return rage;
    }
    private static String getRage(float score) {
        String rage = "";
        switch ((int) Math.ceil(score)) {
            case 1: rage = "6";
                break;
            case 2 : rage = "7";
                break;
            case 3 : rage = "8";
                break;
            case 4 : rage = "9";
                break;
            case 5 : rage = "10";
                break;
            case 6 : rage = "11";
                break;
            case 7 : rage = "12";
                break;
            case 8 : rage = "13";
                break;
            case 9 : rage = "14";
                break;
            case 10 : rage = "15";
                break;
            case 11 : rage = "16";
                break;
            case 12 : rage = "17";
                break;
            case 13 : rage = "18";
                break;
            case 14 : rage = "22";
                break;
            default: break;
        }
        return rage;
    }



}
