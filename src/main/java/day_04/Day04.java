package day_04;

import InputFileReader.InputFileReader;
import day_04.models.SectionRange;

import java.util.ArrayList;

public class Day04 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(4, false).readLines();
        ArrayList<SectionRange[]> sectionPairs = getSectionPairs(input_lines);

        System.out.println("Day 04");
        System.out.println("\tPart 01: " + part01(sectionPairs));
        System.out.println("\tPart 02: " + part02(sectionPairs));
    }

    private static int part01(ArrayList<SectionRange[]> sectionPairs) {
        int count = 0;

        for (SectionRange[] sectionPair : sectionPairs) {
            SectionRange firstRange = sectionPair[0];
            SectionRange secondRange = sectionPair[1];

            if (firstRange.containsWholeRange(secondRange) || secondRange.containsWholeRange(firstRange)) {
                count++;
            }
        }

        return count;
    }


    private static int part02(ArrayList<SectionRange[]> sectionPairs) {
        int count = 0;

        for (SectionRange[] sectionPair : sectionPairs) {
            SectionRange firstRange = sectionPair[0];
            SectionRange secondRange = sectionPair[1];

            if (firstRange.overlapsWithRange(secondRange)) {
                count++;
            }
        }

        return count;
    }

    private static ArrayList<SectionRange[]> getSectionPairs(ArrayList<String> input_lines) {
        ArrayList<SectionRange[]> sectionPairs = new ArrayList<>();

        for (String line : input_lines) {
            String[] ranges = line.split(",");
            SectionRange firstRange = new SectionRange(ranges[0]);
            SectionRange secondRange = new SectionRange(ranges[1]);

            SectionRange[] sectionPair = {firstRange, secondRange};
            sectionPairs.add(sectionPair);
        }

        return sectionPairs;
    }

}
