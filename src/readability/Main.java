package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int words = 0;
    private static int sentences = 0;
    private static int characters = 0;

    private static int syllables = 0;
    private static int polysyllables = 0;

    private static float aRI = 0;

    private static float fK = 0;

    private static float sMOG = 0;

    private static float cL = 0;

    public static void main(String[] args) throws IOException {
        String line = getInput(args[0]);
        printResult(line);

    }

    private static String getInput(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
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
        String line = input.replaceAll("[\\s\\n\\t]", "");
        characters = line.length();
    }

    private static void countSyllables(String input) {
        String[] words = input.split("\\s+");
        for(String word : words) {
            int amount = count(word);
            syllables += amount;
            if (amount >= 3) {
                polysyllables++;
            }
        }
    }

    protected static int count(String word)
    {
        int count = 0;
        word = word.toLowerCase().replaceAll("[!?.,]", "");

        if (word.charAt(word.length()-1) == 'e') {
            if (silente(word)){
                String newword = word.substring(0, word.length()-1);
                count = count + countit(newword);
            } else {
                count++;
            }
        } else {
            count = count + countit(word);
        }
        return count != 0 ? count : 1;
    }

    private static int countit(String word) {
        int count = 0;
        Pattern splitter = Pattern.compile("[^aeiouy]*[aeiouy]+");
        Matcher m = splitter.matcher(word);

        while (m.find()) {
            count++;
        }
        return count;
    }

    private static boolean silente(String word) {
        word = word.substring(0, word.length()-1);

        Pattern yup = Pattern.compile("[aeiouy]");
        Matcher m = yup.matcher(word);

        return m.find();
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
        try (Scanner scanner = new Scanner(System.in)) {
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
                case "all" : startAllMethods();
                break;
                default : System.out.println("Choose correct item");
                break;
            }
        }

    }

    private static void startAllMethods() {
        getScoreARI();
        getScoreFK();
        getScoreSMOG();
        getScoreCL();
        getAverageAge();

    }


    private static void getScoreARI() {

        aRI =  4.71f * characters / words + 0.5f * words / sentences - 21.43f;
        System.out.println();
        System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).%n", aRI, getARIRage(aRI));
    }

    private static void getScoreFK() {
        fK = (((0.39f * words) / sentences) + ((11.8f * syllables) / words)) - 15.59f;
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).%n", fK, getRage(fK));
    }

    private static void getScoreSMOG() {
        sMOG = (1.043f * (float) Math.sqrt((double) (polysyllables * 30) / sentences)) + 3.1291f;
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).%n", sMOG, getRage(sMOG));
    }

    private static void getScoreCL() {
        cL = 0.0588f *  ((float) characters / words) * 100 - 0.296f * ((float) sentences / words) * 100 - 15.8f;
        System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).%n", cL, getRage(cL));
    }

    private static void getAverageAge() {
        float age;
        age = (aRI + sMOG + cL + fK) / 4;
        System.out.println();
        System.out.printf("This text should be understood in average by %.2f-year-olds.", age);
    }



    private static int getARIRage(float score) {
        int age = 0;
        switch ((int) Math.ceil(score)) {
            case 1: age = 6;
            break;
            case 2 : age = 7;
            break;
            case 3 : age = 8;
            break;
            case 4 : age = 9;
            break;
            case 5 : age = 10;
            break;
            case 6 : age = 11;
            break;
            case 7 : age = 12;
            break;
            case 8 : age = 13;
            break;
            case 9 : age = 14;
            break;
            case 10 : age = 15;
            break;
            case 11 : age = 16;
            break;
            case 12 : age = 17;
            break;
            case 13 : age = 18;
            break;
            case 14 : age = 19;
            break;
            default:
                break;
        }
        return age;
    }
    private static int getRage(float score) {
        int age = 0;
        switch ((int) Math.ceil(score)) {
            case 1: age = 6;
                break;
            case 2 : age = 7;
                break;
            case 3 : age = 8;
                break;
            case 4 : age = 9;
                break;
            case 5 : age = 10;
                break;
            case 6 : age = 11;
                break;
            case 7 : age = 12;
                break;
            case 8 : age = 13;
                break;
            case 9 : age = 14;
                break;
            case 10 : age = 15;
                break;
            case 11 : age = 16;
                break;
            case 12 : age = 17;
                break;
            case 13 : age = 18;
                break;
            case 14 : age = 22;
                break;
            default:
                break;
        }
        return age;
    }



}
