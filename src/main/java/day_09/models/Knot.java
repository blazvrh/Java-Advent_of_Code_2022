package day_09.models;

import java.util.HashSet;

public class Knot {

    private int x = 0;
    private int y = 0;
    private final Knot tail;
    private final HashSet<String> visitedPositions = new HashSet<>();

    public Knot(Knot tail) {
        this.tail = tail;
        visitedPositions.add(x + "," + y);
    }

    public void move(Instruction instruction) {

        for (int i = 0; i < instruction.steps; i++) {
            x += instruction.getHorizontalDirection();
            y += instruction.getVerticalDirection();

            if (tailMoveRequired()) {
                tail.moveTail(x, y);
            }
        }
    }

    private boolean tailMoveRequired() {
        return Math.abs(x - tail.getX()) > 1 || Math.abs(y - tail.getY()) > 1;
    }

    private void moveTail(int targetX, int targetY) {
        int xDistance = targetX - x;
        int yDistance = targetY - y;

        if (xDistance == 0 || yDistance == 0) {
            x += xDistance / 2;
            y += yDistance / 2;
        } else {
            x += xDistance / Math.abs(xDistance);
            y += yDistance / Math.abs(yDistance);
        }

        String newPosition = x + "," + y;
        visitedPositions.add(newPosition);

        if (tail != null && tailMoveRequired()) {
            tail.moveTail(x, y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumberOfVisitedPositions() {
        return visitedPositions.size();
    }

}
