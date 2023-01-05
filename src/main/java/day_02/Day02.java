package day_02;

import InputFileReader.InputFileReader;

import java.util.ArrayList;
import java.util.Arrays;

public class Day02 {
    // X: Rock; Y: Paper; Z: Scissors
    // X: Lose; Y: Draw; Z: Win
    // A: Rock; B: Paper; C: Scissors
    // Score 1: 1 for Rock, 2 for Paper, and 3 for Scissors
    //  +
    // Score 2: 0 if you lost, 3 if the round was a draw, and 6 if you won


    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(2, false).readLines();

        System.out.println("Day 02");
        System.out.println("\tPart 01: " + part01(input_lines));
        System.out.println("\tPart 02: " + part02(input_lines));
    }

    private static int part01(ArrayList<String> input_lines) throws Exception {
        ArrayList<String> drawCombos = new ArrayList<>(Arrays.asList("A X", "B Y", "C Z"));
        ArrayList<String> winCombos = new ArrayList<>(Arrays.asList("A Y", "B Z", "C X"));
        ArrayList<String> loseCombos = new ArrayList<>(Arrays.asList("A Z", "B X", "C Y"));

        int finalScore = 0;

        for (String line : input_lines) {
            String myElement = line.split(" ")[1];

            int outcomeScore;
            if (winCombos.contains(line)) {
                outcomeScore = 6;
            } else if (drawCombos.contains(line)) {
                outcomeScore = 3;
            } else if (loseCombos.contains(line)) {
                outcomeScore = 0;
            } else {
                throw new Exception("Invalid combo");
            }

            int elementScore;
            switch (myElement) {
                case "X":
                    elementScore = 1;
                    break;
                case "Y":
                    elementScore = 2;
                    break;
                case "Z":
                    elementScore = 3;
                    break;
                default:
                    throw new Exception("Invalid selection");
            }

            finalScore += outcomeScore + elementScore;
        }

        return finalScore;
    }

    private static int part02(ArrayList<String> input_lines) throws Exception {

        ArrayList<String> rockCombos = new ArrayList<>(Arrays.asList("A Y", "B X", "C Z"));
        ArrayList<String> paperCombos = new ArrayList<>(Arrays.asList("A Z", "B Y", "C X"));
        ArrayList<String> scissorsCombos = new ArrayList<>(Arrays.asList("A X", "B Z", "C Y"));

        int finalScore = 0;

        for (String line : input_lines) {
            String outcome = line.split(" ")[1];

            int elementScore;
            if (rockCombos.contains(line)) {
                elementScore = 1;
            } else if (paperCombos.contains(line)) {
                elementScore = 2;
            } else if (scissorsCombos.contains(line)) {
                elementScore = 3;
            } else {
                throw new Exception("Invalid combo");
            }

            int outcomeScore;
            switch (outcome) {
                case "X":
                    outcomeScore = 0;
                    break;
                case "Y":
                    outcomeScore = 3;
                    break;
                case "Z":
                    outcomeScore = 6;
                    break;
                default:
                    throw new Exception("Invalid selection");
            }

            finalScore += outcomeScore + elementScore;
        }

        return finalScore;
    }
}
