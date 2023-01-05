package day_03;

import InputFileReader.InputFileReader;

import java.util.ArrayList;
import java.util.Arrays;

public class Day03 {
    // X: Rock; Y: Paper; Z: Scissors
    // X: Lose; Y: Draw; Z: Win
    // A: Rock; B: Paper; C: Scissors
    // Score 1: 1 for Rock, 2 for Paper, and 3 for Scissors
    //  +
    // Score 2: 0 if you lost, 3 if the round was a draw, and 6 if you won


    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(3, false).readLines();

        System.out.println("Day 03");
        System.out.println("\tPart 01: " + part01(input_lines));
        System.out.println("\tPart 02: " + part02(input_lines));
    }

    private static int part01(ArrayList<String> input_lines) {
        ArrayList<Character> invalidItems = new ArrayList<>();

        for (String line : input_lines) {
            int compartmentSize = line.length() / 2;
            String leftCompartment = line.substring(0, compartmentSize);
            String rightCompartment = line.substring(compartmentSize);

            for (char leftItem : leftCompartment.toCharArray()) {
                if (rightCompartment.contains(String.valueOf(leftItem))) {
                    invalidItems.add(leftItem);
                    break;
                }
            }
        }

        return invalidItems.stream()
                .map(Day03::charToInt)
                .mapToInt(item -> item)
                .sum();

    }


    private static int part02(ArrayList<String> input_lines) {
        ArrayList<Character> badgeItems = new ArrayList<>();

        for (int i = 0; i < input_lines.size(); i += 3) {
            String elf_1 = input_lines.get(i);
            String elf_2 = input_lines.get(i + 1);
            String elf_3 = input_lines.get(i + 2);

            for (char item : elf_1.toCharArray()) {
                if (elf_2.contains(String.valueOf(item)) && elf_3.contains(String.valueOf(item))) {
                    badgeItems.add(item);
                    break;
                }
            }
        }

        return badgeItems.stream()
                .map(Day03::charToInt)
                .mapToInt(item -> item)
                .sum();
    }

    private static int charToInt(char item) {
        String stringItem = String.valueOf(item);
        if (stringItem.toLowerCase().equals(stringItem)) {
            return (int) item - 96;
        } else {
            return (int) item - 38;
        }
    }
}
