package app.gui;

import app.gui.components.Square;
import app.model.GameState;
import app.model.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
impackage app.gui;

import app.gui.components.Square;
import app.model.GameState;
import app.model.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class GameController {
    public static final int fieldSize = 10;

    @FXML
    GridPane mineField;

    @FXML
    Label endgamelabel;

    private GameState gameState;

    @FXML
    public void initialize() {
        gameState = new GameState();

        for (int row = 0; row < fieldSize; ++row) {
            for (int col = 0; col < fieldSize; ++col) {
                var square = new Square(30);

                square.setFill(Color.DARKGRAY);
                square.setOnMouseClicked(this::onSquareClickHandler);
                mineField.add(square, col, row);
            }
        }
    }

    private Position getPositionOfSquare(Node square) {
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);

        return Position.of(row, col);
    }

    private static Image getImageMapping(int numberOfMineNeighbours) {
        return switch (numberOfMineNeighbours) {
            case 0 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/fieldrevealed.png").toString());
            case 1 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field1.png").toString());
            case 2 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field2.png").toString());
            case 3 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field3.png").toString());
            case 4 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field4.png").toString());
            case 5 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field5.png").toString());
            case 6 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field6.png").toString());
            case 7 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field7.png").toString());
            case 8 -> new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/field8.png").toString());
            default -> throw new IllegalArgumentException();
        };
    }

    private static Image getFlaggerImage() {
        return new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/fieldflagged.png").toString());
    }

    private static Image getBombImage() {
        return new Image(GameController.class.getClassLoader().getResource("minesweeper-fields/bomb.png").toString());
    }


    private int getMouseButton(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) return 1;
        if (event.getButton() == MouseButton.SECONDARY) return 2;
        return 0;
    }

    private void reveal() {
        var nodes = mineField.getChildren().filtered(Square.class::isInstance);

        for (var node : nodes) {
            var position = getPositionOfSquare(node);
            var fillImage = getBombImage();

            // no isPresent check needed, because
            if (!gameState.isPositionMine(position)) {
                fillImage = getImageMapping(gameState.getNumberOfMineNeighbours(position).get());
            }
            ((Square) node).setFill(new ImagePattern(fillImage));
        }
    }

    private Optional<Square> getSquareByPosition(Position position) {
        for (var node : mineField.getChildren().filtered(Square.class::isInstance)) {
            var nodePosition = getPositionOfSquare(node);

            if (nodePosition.equals(position)) {
                return Optional.of((Square) node);
            }
        }

        return Optional.empty();
    }

    private Paint createSquareFill(Position pos) {
        return new ImagePattern(getImageMapping(gameState.getNumberOfMineNeighbours(pos).get()));
    }

    private Paint createSquareFlagger() {
        return new ImagePattern(getFlaggerImage());
    }

    // refactor needed


    private void onSquareClickHandler(MouseEvent event) {
        var square = (Square)event.getSource();
        var squarePosition = getPositionOfSquare(square);


        if (getMouseButton(event) == 1) {
            if(gameState.isPositionMine(squarePosition)) {
                System.out.println("game over");
                endgamelabel.setText("You lost!");
                endgamelabel.setStyle("-fx-text-fill: red");
                reveal();
                return;
            }

            if(gameState.isPositionSelected(squarePosition)) {
                System.out.println("vótmá");
                return;
            }
            gameState.selectPosition(squarePosition);

            square.setFill(createSquareFill(squarePosition));

            if(gameState.getNumberOfMineNeighbours(squarePosition).get() == 0) {
                var lst  = gameState.revealZeroFill(squarePosition);

                for(var position: lst) {
                    gameState.selectPosition(position);
                    var node = getSquareByPosition(position);

                    if (node.isPresent()) {
//                  node.get().setFill(getColorMapping(gameState.getNumberOfMineNeighbours(position).get()));
                        node.get().setFill(createSquareFill(position));
                    }
                }
            }
            if(gameState.isPlayerWinner()) {
                System.out.println("You won!");
                endgamelabel.setText("You won!");
                endgamelabel.setStyle("-fx-text-fill: green");
                reveal();
            }
        }

        if (getMouseButton(event) == 2 && !gameState.isPositionSelected(squarePosition)) {
            square.setFill(createSquareFlagger());
        }

    }



    public void OnResetButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/game.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root, 720, 480));
        stage.show();
    }
}
