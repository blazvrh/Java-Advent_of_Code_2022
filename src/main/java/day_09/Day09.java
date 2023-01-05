package day_09;

import InputFileReader.InputFileReader;
import day_09.models.Instruction;
import day_09.models.Knot;

import java.util.ArrayList;

public class Day09 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(9, false).readLines();
        ArrayList<Instruction> instructions = getInstructions(input_lines);

        System.out.println("Day 09");
        System.out.println("\tPart 01: " + part01(instructions));
        System.out.println("\tPart 02: " + part02(instructions));
    }

    private static ArrayList<Instruction> getInstructions(ArrayList<String> inputLines) {
        ArrayList<Instruction> instructions = new ArrayList<>();

        for (String line : inputLines) {
            String[] lineElements = line.split(" ");
            String direction = lineElements[0];
            int steps = Integer.parseInt(lineElements[1]);
            instructions.add(new Instruction(direction, steps));
        }

        return instructions;
    }

    private static int part01(ArrayList<Instruction> instructions) {
        Knot tail = new Knot(null);
        Knot head = new Knot(tail);

        for (Instruction instruction : instructions) {
            head.move(instruction);
        }

        return tail.getNumberOfVisitedPositions();
    }


    private static int part02(ArrayList<Instruction> instructions) {
        Knot lastTail = new Knot(null);
        Knot knot = lastTail;
        for (int i = 0; i < 8; i++) {
            knot = new Knot(knot);
        }
        Knot head = new Knot(knot);

        for (Instruction instruction : instructions) {
            head.move(instruction);
        }

        return lastTail.getNumberOfVisitedPositions();
    }


}
