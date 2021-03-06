package app.gui.components;

import app.model.Position;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {

    public Square(double length) {
        super(length, length);
    }

    public Square(double length, Paint fill) {
        super(length, length, fill);
    }

    public Square(Position position, double length) {
        super(position.col(), position.row(), length, length);
    }
}
                                                                                                                            package app.gui.components;

import app.model.Position;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {

    public Square(double length) {
        super(length, length);
    }

    public Square(double length, Paint fill) {
        super(length, length, fill);
    }

    public Square(Position position, double length) {
        super(position.col(), position.row(), length, length);
    }
}
