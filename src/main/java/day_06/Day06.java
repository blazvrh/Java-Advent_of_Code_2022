package day_06;

import InputFileReader.InputFileReader;

import java.util.ArrayList;
import java.util.HashSet;

public class Day06 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(6, false).readLines();
        String signal = input_lines.get(0);

        System.out.println("Day 06");
        System.out.println("\tPart 01: " + part01(signal));
        System.out.println("\tPart 02: " + part02(signal));
    }

    private static int part01(String signal) throws Exception {
        return getPositionOfLastCharacterInUniqueSubstring(signal, 4);
    }

    private static int part02(String signal) throws Exception {
        return getPositionOfLastCharacterInUniqueSubstring(signal, 14);
    }

    private static int getPositionOfLastCharacterInUniqueSubstring(String signal, int substringLength) throws Exception {
        for (int i = substringLength - 1; i < signal.length(); i++) {

            HashSet<Character> uniqueChars = new HashSet<>();
            for (int j = 0; j < substringLength; j++) {
                uniqueChars.add(signal.charAt(i - j));
            }

            if (uniqueChars.size() == substringLength) {
                return i + 1;
            }
        }
        throw new Exception("Unique signal not found");
    }

}
