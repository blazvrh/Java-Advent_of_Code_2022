package day_08;

import InputFileReader.InputFileReader;
import day_08.models.Tree;

import java.util.ArrayList;
import java.util.HashMap;

public class Day08 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(8, false).readLines();
        HashMap<String, Tree> trees = getTrees(input_lines);

        System.out.println("Day 08");
        System.out.println("\tPart 01: " + part01(trees));
        System.out.println("\tPart 02: " + part02(trees));
    }

    private static HashMap<String, Tree> getTrees(ArrayList<String> inputLines) {
        HashMap<String, Tree> trees = new HashMap<>();


        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            for (int x = 0; x < line.length(); x++) {
                String treeLocation = x + "," + y;
                int height = Integer.parseInt(String.valueOf(line.charAt(x)));
                trees.put(treeLocation, new Tree(height, y, x));
            }
        }
        return trees;
    }

    private static int part01(HashMap<String, Tree> trees) {
        int count = 0;
        for (Tree tree : trees.values()) {
            if (tree.isVisible(trees)) {
                count++;
            }
        }
        return count;
    }

    private static int part02(HashMap<String, Tree> trees) {
        int maxScenicScore = -1;
        for (Tree tree : trees.values()) {
            int scenicScore = tree.getScenicScore(trees);
            if (scenicScore > maxScenicScore) {
                maxScenicScore = scenicScore;
            }
        }

        return maxScenicScore;
    }


}
