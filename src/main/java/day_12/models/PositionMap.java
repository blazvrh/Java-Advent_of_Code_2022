package day_12.models;

import java.util.*;

public class PositionMap {

    private final HashMap<String, Position> positionMap = new HashMap<>();
    private Position startingPosition;
    private Position targetPosition;

    private final HashSet<String> deadEnds = new HashSet<>();

    public PositionMap(ArrayList<String> inputLines, int startingRow) {
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);

            for (int x = 0; x < line.length(); x++) {
                boolean isStartingPosition = x == 0 && y == startingRow;
                Position position = new Position(x, y, line.charAt(x), isStartingPosition);
                positionMap.put(position.getPositionKey(), position);

                if (position.isStartingPosition()) {
                    startingPosition = position;
                }
                if (position.isTargetPosition()) {
                    targetPosition = position;
                }
            }
        }
    }

    public PositionMap(ArrayList<String> inputLines) {
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);

            for (int x = 0; x < line.length(); x++) {
                Position position = new Position(x, y, line.charAt(x));
                positionMap.put(position.getPositionKey(), position);

                if (position.isStartingPosition()) {
                    startingPosition = position;
                }
                if (position.isTargetPosition()) {
                    targetPosition = position;
                }
            }
        }
    }

    public int getShortestPathLength() {
        ArrayList<Path> finishedPaths = new ArrayList<>();
        ArrayList<Path> activePaths = new ArrayList<>();

        activePaths.add(new Path(startingPosition));

        while (activePaths.size() != 0) {
            ArrayList<Path> newPaths = new ArrayList<>();
            for (Path path : activePaths) {
                if (path.getLastPosition().equals(targetPosition)) {
                    finishedPaths.add(path);
                    continue;
                }
                newPaths.addAll(getNewPaths(path));
            }

            activePaths = getUsefulPaths(newPaths);
        }

        return finishedPaths
                .stream()
                .map(Path::length)
                .sorted()
                .findFirst()
                .orElseThrow();
    }

    private ArrayList<Path> getNewPaths(Path path) {
        ArrayList<Path> newPaths = new ArrayList<>();

        for (Position position : getPossiblePositions(path.getLastPosition())) {
            if (path.isPositionInPath(position)) {
                continue;
            }
            Path newPath = new Path(path);
            newPath.addVisitedPosition(position);
            newPaths.add(newPath);
        }
        return newPaths;
    }

    private ArrayList<Position> getPossiblePositions(Position currentPosition) {
        ArrayList<Position> positions = new ArrayList<>();

        if (currentPosition == null) {
            return positions;
        }

        for (String positionKey : currentPosition.getNeighbourPositionKeys()) {
            if (!positionMap.containsKey(positionKey)) {
                continue;
            }

            Position neighbourPosition = positionMap.get(positionKey);
            if (!neighbourPosition.isPositionReachable(currentPosition)) {
                continue;
            }
            positions.add(neighbourPosition);
        }
        return positions;
    }

    private ArrayList<Path> getUsefulPaths(ArrayList<Path> paths) {
        ArrayList<Path> usefulPats = new ArrayList<>();

        for (Path path : paths) {
            Position lastPosition = path.getLastPosition();
            if (deadEnds.contains(lastPosition.getPositionKey())) {
                continue;
            }
            boolean skipPath = false;
            for (Path usefulPath : usefulPats) {
                if (usefulPath.isPositionInPath(lastPosition)) {
                    skipPath = true;
                    break;
                }
            }
            if (skipPath) {
                deadEnds.add(path.getLastPosition().getPositionKey());
                continue;
            }
            usefulPats.add(path);
            deadEnds.add(path.getLastPosition().getPositionKey());
        }

        return usefulPats;
    }

}
