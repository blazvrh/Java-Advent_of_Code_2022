package day_10;

import InputFileReader.InputFileReader;
import day_10.models.Cpu;
import day_10.models.Instruction;

import java.util.ArrayList;

public class Day10 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(10, false).readLines();

        System.out.println("Day 10");
        System.out.println("\tPart 01: " + part01(input_lines));
        System.out.println("\tPart 02: PZGPKPEB" + part02(input_lines));
    }

    private static int part01(ArrayList<String> input_lines) {
        Cpu cpu = new Cpu();

        for (String line : input_lines) {
            Instruction instruction = new Instruction(line);
            cpu.executeInstruction(instruction);
        }

        return cpu.getSignalSum();
    }


    private static String part02(ArrayList<String> input_lines) {
        Cpu cpu = new Cpu();

        for (String line : input_lines) {
            Instruction instruction = new Instruction(line);
            cpu.executeInstruction(instruction);
        }

        return cpu.renderImage();
    }


}
