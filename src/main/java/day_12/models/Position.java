package day_12.models;

import java.util.ArrayList;

public class Position {
    private final int height;
    private final int x;
    private final int y;
    private final boolean isStartingPosition;
    private final boolean isTargetPosition;

    public Position(int x, int y, char inputChar, boolean isStartingPosition) {
        this.x = x;
        this.y = y;
        this.isStartingPosition = isStartingPosition;
        isTargetPosition = inputChar == 'E';

        if (inputChar == 'S') {
            height = calculateHeight('a');
        } else if (isTargetPosition) {
            height = calculateHeight('z');
        } else {
            height = calculateHeight(inputChar);
        }
    }

    public Position(int x, int y, char inputChar) {
        this.x = x;
        this.y = y;
        isStartingPosition = inputChar == 'S';
        isTargetPosition = inputChar == 'E';

        if (isStartingPosition) {
            height = calculateHeight('a');
        } else if (isTargetPosition) {
            height = calculateHeight('z');
        } else {
            height = calculateHeight(inputChar);
        }
    }

    private int calculateHeight(char inputChar) {
        return inputChar - 97;
    }

    public String getPositionKey() {
        return getPositionKey(x, y);
    }

    public String getPositionKey(int targetX, int targetY) {
        return targetX + "," + targetY;
    }

    public ArrayList<String> getNeighbourPositionKeys() {
        ArrayList<String> neighbourPositionKeys = new ArrayList<>();

        neighbourPositionKeys.add(getPositionKey(x - 1, y));
        neighbourPositionKeys.add(getPositionKey(x + 1, y));
        neighbourPositionKeys.add(getPositionKey(x, y - 1));
        neighbourPositionKeys.add(getPositionKey(x, y + 1));

        return neighbourPositionKeys;
    }

    public boolean isPositionReachable(Position previousPosition) {
        return height <= previousPosition.height + 1;
    }

    public boolean isStartingPosition() {
        return isStartingPosition;
    }

    public boolean isTargetPosition() {
        return isTargetPosition;
    }

    @Override
    public boolean equals(Object obj) {
        Position position = obj instanceof Position ? ((Position) obj) : null;
        if (position == null) {
            return false;
        }
        return getPositionKey().equals(position.getPositionKey());
    }
}
