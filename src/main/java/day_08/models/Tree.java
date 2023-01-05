package day_08.models;

import java.util.HashMap;

public class Tree {

    public final int height;
    public final int rowIndex;
    public final int columnIndex;

    public Tree(int height, int rowIndex, int columnIndex) {
        this.height = height;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public boolean isVisible(HashMap<String, Tree> trees) {
        boolean isVisibleFromTop = isVisibleFromDirection(0, -1, trees);
        boolean isVisibleFromBottom = isVisibleFromDirection(0, 1, trees);
        boolean isVisibleFromLeft = isVisibleFromDirection(-1, 0, trees);
        boolean isVisibleFromRight = isVisibleFromDirection(1, 0, trees);

        return isVisibleFromTop || isVisibleFromBottom || isVisibleFromLeft || isVisibleFromRight;
    }

    private boolean isVisibleFromDirection(int xDirection, int yDirection, HashMap<String, Tree> trees) {
        int xLocation = columnIndex + xDirection;
        int yLocation = rowIndex + yDirection;
        String location = xLocation + "," + yLocation;

        while (trees.containsKey(location)) {
            if (trees.get(location).height >= height) {
                return false;
            }

            xLocation += xDirection;
            yLocation += yDirection;
            location = xLocation + "," + yLocation;
        }

        return true;
    }


    public int getScenicScore(HashMap<String, Tree> trees) {
        int topTreeCount = getVisibleTrees(0, -1, trees);
        int bottomTreeCount = getVisibleTrees(0, 1, trees);
        int leftTreeCount = getVisibleTrees(-1, 0, trees);
        int rightTreeCount = getVisibleTrees(1, 0, trees);

        return topTreeCount * bottomTreeCount * leftTreeCount * rightTreeCount;
    }

    private int getVisibleTrees(int xDirection, int yDirection, HashMap<String, Tree> trees) {
        int xLocation = columnIndex + xDirection;
        int yLocation = rowIndex + yDirection;
        int count = 0;
        String location = xLocation + "," + yLocation;

        while (trees.containsKey(location)) {
            if (trees.get(location).height >= height) {
                count++;
                break;
            }

            count++;

            xLocation += xDirection;
            yLocation += yDirection;
            location = xLocation + "," + yLocation;
        }
        return count;
    }
}
