package day_12.models;

import java.util.ArrayList;

public class Path {

    private final ArrayList<Position> visitedPositions;

    public Path(Position startingPosition) {
        visitedPositions = new ArrayList<>();
        visitedPositions.add(startingPosition);
    }

    public Path(Path path) {
        visitedPositions = path.getVisitedPositions();
    }

    public ArrayList<Position> getVisitedPositions() {
        return new ArrayList<>(visitedPositions);
    }

    public Position getLastPosition() {
        if (visitedPositions.size() == 0) {
            return null;
        }
        return visitedPositions.get(visitedPositions.size() - 1);
    }

    public void addVisitedPosition(Position position) {
        visitedPositions.add(position);
    }

    public boolean isPositionInPath(Position position) {
        return visitedPositions.contains(position);
    }

    public int length() {
        return visitedPositions.size() - 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Position position : visitedPositions) {
            stringBuilder.append(position.getPositionKey()).append("  ");
        }

        return stringBuilder.toString();
    }
}
