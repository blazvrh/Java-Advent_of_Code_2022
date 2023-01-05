package day_10.models;

public class Instruction {

    private static final String NOOP = "noop";

    private final int addX;
    private final String instruction;

    public Instruction(String instructionLine) {
        if (instructionLine.equals(NOOP)) {
            instruction = instructionLine;
            addX = 0;
        } else {
            String[] lineSplit = instructionLine.split(" ");
            this.instruction = lineSplit[0];
            this.addX = Integer.parseInt(lineSplit[1]);
        }
    }

    public boolean isNoop() {
        return instruction.equals(NOOP);
    }

    public int getAddX() {
        return addX;
    }
}
