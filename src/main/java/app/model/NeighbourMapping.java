package app.model;

import java.util.*;

import static app.util.PositionUtils.getNeighbours;

public class NeighbourMapping {
    private Map<Position, Integer> mapping;
    private int fieldSize;
    private List<Position> minePositions;

    NeighbourMapping(int fieldSize, List<Position> minePositions) {
        this.fieldSize = fieldSize;
        this.minePositions = minePositions;
        mapping = createMapping();

    }

    private Map<Position, Integer> createMapping() {
        var neighbourMapping = new HashMap<Position, Integer>();

        for (int row = 0; row < fieldSize; ++row) {
            for (int col = 0; col < fieldSize; ++col) {
                var position = Position.of(row, col);

                if (minePositions.contains(position)) {
                    continue;
                }

                neighbourMapping.put(position, calculateNumberOfMineNeighbours(position));
            }
        }

        return neighbourMapping;
    }

    private int calculateNumberOfMineNeighbours(Position position) {
        int mineNeighbours = 0;

        for(var neighbour : getNeighbours(position)) {
            if (minePositions.contains(neighbour)) {
                mineNeighbours += 1;
            }
        }

        return mineNeighbours;
    }

    public Optional<Integer> getNumberOfMineNeighbours(Position position) {
        return Optional.ofNullable(mapping.get(position));
    }


}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               package app.model;

import java.util.*;

import static app.util.PositionUtils.getNeighbours;

public class NeighbourMapping {
    private Map<Position, Integer> mapping;
    private int fieldSize;
    private List<Position> minePositions;

    NeighbourMapping(int fieldSize, List<Position> minePositions) {
        this.fieldSize = fieldSize;
        this.minePositions = minePositions;
        mapping = createMapping();

    }

    private Map<Position, Integer> createMapping() {
        var neighbourMapping = new HashMap<Position, Integer>();

        for (int row = 0; row < fieldSize; ++row) {
            for (int col = 0; col < fieldSize; ++col) {
                var position = Position.of(row, col);

                if (minePositions.contains(position)) {
                    continue;
                }

                neighbourMapping.put(position, calculateNumberOfMineNeighbours(position));
            }
        }

        return neighbourMapping;
    }

    private int calculateNumberOfMineNeighbours(Position position) {
        int mineNeighbours = 0;

        for(var neighbour : getNeighbours(position)) {
            if (minePositions.contains(neighbour)) {
                mineNeighbours += 1;
            }
        }

        return mineNeighbours;
    }

    public Optional<Integer> getNumberOfMineNeighbours(Position position) {
        return Optional.ofNullable(mapping.get(position));
    }


}
