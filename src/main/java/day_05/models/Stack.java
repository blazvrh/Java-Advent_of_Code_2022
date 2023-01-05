package day_05.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stack {
    int stackId;
    ArrayList<String> crates = new ArrayList<>();

    public Stack(int stackId) {
        this.stackId = stackId;
    }

    public void addCrateToBottom(String crate) {
        crates.add(0, crate);
    }


    public void moveCreates(int moveCount, Stack destinationStack, boolean oneAtTime) {
        if (moveCount == 0) {
            return;
        }
        int stackSize = crates.size();
        List<String> movedCrates = crates.subList(stackSize - moveCount, stackSize);
        List<String> remainingCrates = crates.subList(0, stackSize - moveCount);

        destinationStack.addCrates(movedCrates, oneAtTime);
        crates = new ArrayList<>(remainingCrates);
    }

    public void addCrates(List<String> newCrates, boolean oneAtTime) {
        if (oneAtTime) {
            Collections.reverse(newCrates);
        }
        crates.addAll(newCrates);
    }

    public String getTopCrate() {
        if (crates.size() == 0) {
            return "";
        }

        return crates.get(crates.size() - 1);
    }

    @Override
    public String toString() {
        return "Stack " + stackId + "; Crates: " + crates.toString();
    }
}
