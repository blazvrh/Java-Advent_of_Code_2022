package day_12;

import InputFileReader.InputFileReader;
import day_12.models.PositionMap;

import java.util.ArrayList;
import java.util.Collections;

public class Day12 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(12, false).readLines();

        System.out.println("Day 12");
        System.out.println("\tPart 01: " + part01(input_lines));
        System.out.println("\tPart 02: " + part02(input_lines));
    }

    private static int part01(ArrayList<String> input_lines) {
        PositionMap positionMap = new PositionMap(input_lines);

        return positionMap.getShortestPathLength();
    }


    private static int part02(ArrayList<String> input_lines) {
        ArrayList<Integer> pathLengths = new ArrayList<>();

        for (int i = 0; i < input_lines.size(); i++) {
            String line = input_lines.get(i);
            if (line.charAt(2) != 'c') {
                continue;
            }

            PositionMap positionMap = new PositionMap(input_lines, i);
            pathLengths.add(positionMap.getShortestPathLength());
        }

        return Collections.min(pathLengths);
    }

}
