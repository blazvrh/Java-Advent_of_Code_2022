package day_09.models;

public class Instruction {

    public final String direction;
    public final int steps;

    public Instruction(String direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public int getHorizontalDirection() {
        switch (direction) {
            case "R":
                return 1;
            case "L":
                return -1;
            default:
                return 0;
        }
    }

    public int getVerticalDirection() {
        switch (direction) {
            case "U":
                return 1;
            case "D":
                return -1;
            default:
                return 0;
        }
    }
}
