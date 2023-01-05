package day_05.models;

public class Instruction {
    private final int moveCount;
    private final int sourceStackIndex;
    private final int destinationStackIndex;

    public Instruction(String input) {
        String cleanedInput = input
                .replace("move ", "")
                .replace("from ", "")
                .replace("to ", "");
        String[] inputNumbers = cleanedInput.split(" ");

        moveCount = Integer.parseInt(inputNumbers[0]);
        sourceStackIndex = Integer.parseInt(inputNumbers[1]);
        destinationStackIndex = Integer.parseInt(inputNumbers[2]);
    }

    @Override
    public String toString() {
        return "Move: " + moveCount + " from: " + sourceStackIndex + " to: " + destinationStackIndex;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getSourceStackIndex() {
        return sourceStackIndex;
    }

    public int getDestinationStackIndex() {
        return destinationStackIndex;
    }
}
