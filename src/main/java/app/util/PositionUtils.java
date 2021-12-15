package app.util;

import app.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionUtils {
    public  static List<Position> getNeighbours(Position position) {
        var neighbours = new Arrpackage app.util;

import app.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionUtils {
    public  static List<Position> getNeighbours(Position position) {
        var neighbours = new ArrayList<Position>();

        neighbours.add(Position.of(position.row() + 1, position.col()));
        neighbours.add(Position.of(position.row() -1, position.col()));
        neighbours.add(Position.of(position.row(), position.col() + 1));
        neighbours.add(Position.of(position.row(), position.col() - 1));
        neighbours.add(Position.of(position.row() + 1, position.col() + 1));
        neighbours.add(Position.of(position.row() + 1, position.col() - 1));
        neighbours.add(Position.of(position.row() - 1, position.col() + 1));
        neighbours.add(Position.of(position.row() - 1, position.col() - 1));

        return neighbours;
    }

    public static List<Position> getDirectNeighbours(Position position) {
        var neighbours = new ArrayList<Position>();

        neighbours.add(Position.of(position.row() + 1, position.col()));
        neighbours.add(Position.of(position.row() -1, position.col()));
        neighbours.add(Position.of(position.row(), position.col() + 1));
        neighbours.add(Position.of(position.row(), position.col() - 1));

        return neighbours;
    }
}
