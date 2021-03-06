package app.model;

import app.gui.GameController;
import javafx.geometry.Pos;

import java.util.*;

import static app.util.PositionUtils.getDirectNeighbours;
import static app.util.PositionUtils.getNeighbours;

public class GameState {
    private Set<Position> selectedPositions;
    private List<Position> minePositions;
    private NeighbourMapping neighbourMapping;

    public GameState() {
        selectedPositions = new HashSet<>();
        minePositions = generateRandomPositions(GameController.fieldSize, GameController.fieldSize);
        neighbourMapping = new NeighbourMapping(GameController.fieldSize, minePositions);
    }

    private static Position generateRandomPosition(int bound) {
        var random = new Random();

        var row = random.nextInt(bound);
        var col = random.nextInt(bound);

        return Position.of(row, col);
    }


    public boolean isPositionSelected(Position position) {
        return selectedPositions.contains(position);
    }

    public void selectPosition(Position position) {
        selectedPositions.add(position);
    }

    public List<Position> revealZeroFill(Position position) {
        Stack<Position> toBeRevealed = new Stack<>();
        Set<Position> revealed = new HashSet<>();

        toBeRevealed.add(position);

        while (!toBeRevealed.empty()) {
            var currentPosition = toBeRevealed.pop();
            revealed.add(currentPosition);

            if(neighbourMapping.getNumberOfMineNeighbours(currentPosition).get() != 0) {
                continue;
            }

            for(var neighbour: getDirectNeighbours(currentPosition)) {
                if(revealed.contains(neighbour)) {
                    continue;
                }

                if(neighbourMapping.getNumberOfMineNeighbours(neighbour).isEmpty()) {
                    continue;
                }

                if(minePositions.contains(neighbour)) {
                    continue;
                }

                toBeRevealed.add(neighbour);
            }

        }

        return revealed.stream().toList();
    }

    private List<Position> generateRandomPositions(int dimension, int numberOfMines) {
        var positionList = new ArrayList<Position>();

        for(int i = 0; i < numberOfMines; ++i) {
            Position newPosition;

            do {
                newPosition = generateRandomPosition(dimension);
            } while (positionList.contains(newPosition));

            positionList.add(newPosition);
        }

        System.out.println(positionList);
        return positionList;
    }

    public boolean isPlayerWinner() {
        System.out.println(String.format("Selected: %s, mines: %s, cells: %s",
                selectedPositions.size(), minePositions.size(), GameController.fieldSize * GameController.fieldSize));
        return selectedPositions.size() + minePositions.size() == GameController.fieldSize * GameController.fieldSize;
    }

    public boolean isPositionMine(Position position) {
        return minePositions.contains(position);
    }

    public Optional<Integer> getNumberOfMineNeighbours(Position position) {
        return neighbourMapping.getNumberOfMineNeighbours(position);
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    package app.model;

import app.gui.GameController;
import javafx.geometry.Pos;

import java.util.*;

import static app.util.PositionUtils.getDirectNeighbours;
import static app.util.PositionUtils.getNeighbours;

public class GameState {
    private Set<Position> selectedPositions;
    private List<Position> minePositions;
    private NeighbourMapping neighbourMapping;

    public GameState() {
        selectedPositions = new HashSet<>();
        minePositions = generateRandomPositions(GameController.fieldSize, GameController.fieldSize);
        neighbourMapping = new NeighbourMapping(GameController.fieldSize, minePositions);
    }

    private static Position generateRandomPosition(int bound) {
        var random = new Random();

        var row = random.nextInt(bound);
        var col = random.nextInt(bound);

        return Position.of(row, col);
    }


    public boolean isPositionSelected(Position position) {
        return selectedPositions.contains(position);
    }

    public void selectPosition(Position position) {
        selectedPositions.add(position);
    }

    public List<Position> revealZeroFill(Position position) {
        Stack<Position> toBeRevealed = new Stack<>();
        Set<Position> revealed = new HashSet<>();

        toBeRevealed.add(position);

        while (!toBeRevealed.empty()) {
            var currentPosition = toBeRevealed.pop();
            revealed.add(currentPosition);

            if(neighbourMapping.getNumberOfMineNeighbours(currentPosition).get() != 0) {
                continue;
            }

            for(var neighbour: getDirectNeighbours(currentPosition)) {
                if(revealed.contains(neighbour)) {
                    continue;
                }

                if(neighbourMapping.getNumberOfMineNeighbours(neighbour).isEmpty()) {
                    continue;
                }

                if(minePositions.contains(neighbour)) {
                    continue;
                }

                toBeRevealed.add(neighbour);
            }

        }

        return revealed.stream().toList();
    }

    private List<Position> generateRandomPositions(int dimension, int numberOfMines) {
        var positionList = new ArrayList<Position>();

        for(int i = 0; i < numberOfMines; ++i) {
            Position newPosition;

            do {
                newPosition = generateRandomPosition(dimension);
            } while (positionList.contains(newPosition));

            positionList.add(newPosition);
        }

        System.out.println(positionList);
        return positionList;
    }

    public boolean isPlayerWinner() {
        System.out.println(String.format("Selected: %s, mines: %s, cells: %s",
                selectedPositions.size(), minePositions.size(), GameController.fieldSize * GameController.fieldSize));
        return selectedPositions.size() + minePositions.size() == GameController.fieldSize * GameController.fieldSize;
    }

    public boolean isPositionMine(Position position) {
        return minePositions.contains(position);
    }

    public Optional<Integer> getNumberOfMineNeighbours(Position position) {
        return neighbourMapping.getNumberOfMineNeighbours(position);
    }
}
