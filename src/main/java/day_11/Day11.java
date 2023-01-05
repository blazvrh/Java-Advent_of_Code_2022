package day_11;

import InputFileReader.InputFileReader;
import day_11.models.Monkey;

import java.util.ArrayList;
import java.util.Comparator;

public class Day11 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(11, false).readLines();

        System.out.println("Day 11");
        System.out.println("\tPart 01: " + part01(input_lines));
        System.out.println("\tPart 02: " + part02(input_lines));
    }


    private static long part01(ArrayList<String> input_lines) throws Exception {
        ArrayList<Monkey> monkeys = getMonkeys(input_lines);

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                monkey.playRound(true);
            }
        }

        return getMonkeyBusinessLevel(monkeys);
    }

    private static long part02(ArrayList<String> input_lines) throws Exception {
        ArrayList<Monkey> monkeys = getMonkeys(input_lines);

        for (int i = 0; i < 10000; i++) {
            try {
                for (Monkey monkey : monkeys) {
                    monkey.playRound(false);
                }
            } catch (Exception e) {
                System.out.println(i);
                throw e;
            }
        }

        return getMonkeyBusinessLevel(monkeys);
    }

    private static ArrayList<Monkey> getMonkeys(ArrayList<String> inputLines) {
        ArrayList<Monkey> monkeys = new ArrayList<>();
        ArrayList<String> monkeyInput = new ArrayList<>();
        for (String line : inputLines) {
            if (line.equals("")) {
                monkeys.add(new Monkey(monkeyInput));
                monkeyInput = new ArrayList<>();
                continue;
            }
            monkeyInput.add(line);
        }
        monkeys.add(new Monkey(monkeyInput));

        long lcm = 1;
        for (Monkey monkey : monkeys) {
            monkey.setMonkeyTestFailure(monkeys.get(monkey.getMonkeyIndexTestFailure()));
            monkey.setMonkeyTestSuccess(monkeys.get(monkey.getMonkeyIndexTestSuccess()));
            lcm *= monkey.getDivisibleTestNumber();
        }

        for (Monkey monkey : monkeys) {
            monkey.setLcm(lcm);
        }

        return monkeys;
    }

    private static long getMonkeyBusinessLevel(ArrayList<Monkey> monkeys) {
        monkeys.sort(new Comparator<Monkey>() {
            @Override
            public int compare(Monkey m1, Monkey m2) {
                return Long.compare(m2.getItemInspectCount(), m1.getItemInspectCount());
            }
        });

        return monkeys.get(0).getItemInspectCount() * monkeys.get(1).getItemInspectCount();
    }

}
