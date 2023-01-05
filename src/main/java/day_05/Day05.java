package day_05;

import InputFileReader.InputFileReader;
import day_05.models.Instruction;
import day_05.models.Stack;

import java.util.ArrayList;

public class Day05 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(5, false).readLines();

        System.out.println("Day 05");
        System.out.println("\tPart 01: " + part01(input_lines));
        System.out.println("\tPart 02: " + part02(input_lines));
    }

    private static String part01(ArrayList<String> input_lines) {
        ArrayList<Stack> stacks = getStacks(input_lines);
        ArrayList<Instruction> instructions = parseInstructions(input_lines);
        return getResult(stacks, instructions, true);
    }

    private static String part02(ArrayList<String> input_lines) {
        ArrayList<Stack> stacks = getStacks(input_lines);
        ArrayList<Instruction> instructions = parseInstructions(input_lines);
        return getResult(stacks, instructions, false);
    }

    private static String getResult(ArrayList<Stack> stacks, ArrayList<Instruction> instructions, boolean moveOneCrateAtTime) {
        for (Instruction instruction : instructions) {
            int sourceStackIndex = instruction.getSourceStackIndex() - 1;
            int destinationStackIndex = instruction.getDestinationStackIndex() - 1;
            Stack sourceStack = stacks.get(sourceStackIndex);
            Stack destinationStack = stacks.get(destinationStackIndex);

            sourceStack.moveCreates(instruction.getMoveCount(), destinationStack, moveOneCrateAtTime);
        }

        StringBuilder output = new StringBuilder();
        for (Stack stack : stacks) {
            output.append(stack.getTopCrate());
        }
        return output.toString();
    }

    private static ArrayList<Stack> getStacks(ArrayList<String> input_lines) {
        ArrayList<Stack> stacks = new ArrayList<>();

        String first_line = input_lines.get(0);
        int crateCount = (first_line.length() + 1) / 4;
        for (int i = 0; i < crateCount; i++) {
            stacks.add(new Stack(i + 1));
        }

        for (String line : input_lines) {
            if (line.equals("")) {
                break;
            }

            ArrayList<String> crates = getLineCrates(line);
            for (int i = 0; i < crates.size(); i++) {
                String crate = crates.get(i);
                if (crate.equals(" ") || isNumeric(crate)) {
                    continue;
                }
                stacks.get(i).addCrateToBottom(crate);
            }
        }

        return stacks;
    }

    private static ArrayList<String> getLineCrates(String line) {
        ArrayList<String> crates = new ArrayList<>();

        for (int i = 1; i < line.length(); i += 4) {
            String crate = line.substring(i, i + 1);
            crates.add(crate);
        }

        return crates;
    }

    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static ArrayList<Instruction> parseInstructions(ArrayList<String> input_lines) {
        ArrayList<Instruction> instructions = new ArrayList<>();

        boolean isInstruction = false;
        for (String line : input_lines) {
            if (!isInstruction && line.equals("")) {
                isInstruction = true;
                continue;
            } else if (!isInstruction) {
                continue;
            }

            instructions.add(new Instruction(line));
        }

        return instructions;
    }

}
