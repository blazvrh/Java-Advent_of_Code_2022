package day_01;

import InputFileReader.InputFileReader;
import day_02.Day02;

import java.util.ArrayList;
import java.util.Collections;

public class Day01 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(1, false).readLines();
        ArrayList<Integer> calories = getCalories(input_lines);

        System.out.println("Day 01");
        System.out.println("\tPart 01: " + part01(calories));
        System.out.println("\tPart 02: " + part02(calories));
    }


    private static ArrayList<Integer> getCalories(ArrayList<String> input_lines) {
        ArrayList<Integer> allCalories = new ArrayList<>();
        allCalories.add(0);
        int lastIndex = 0;

        int elfCalories;
        for (String line : input_lines) {
            if (line.equals("")) {
                allCalories.add(0);
                lastIndex += 1;
                continue;
            }

            elfCalories = Integer.parseInt(line);
            allCalories.set(lastIndex, allCalories.get(lastIndex) + elfCalories);
        }

        return allCalories;
    }

    private static int part01(ArrayList<Integer> calories) {
        return Collections.max(calories);
    }

    private static int part02(ArrayList<Integer> calories) {
        ArrayList<Integer> sortedCalories = new ArrayList<>(calories);
        sortedCalories.sort(Collections.reverseOrder());

        int result = 0;
        for (int i = 0; i < 3; i++) {
            result += sortedCalories.get(i);
        }

        return result;
    }

}
