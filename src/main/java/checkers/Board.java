package checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final String YEllOW = "yellow";
    public static final String GREEN = "green";
    private final GridPane grid;
    private List<Row> boardRows = new ArrayList<>();


    public Board(GridPane grid) {
        for (int i = 0; i < 8; i++) {
            boardRows.add(i, new Row());
        }
        this.grid = grid;
    }

    public void setFigure(int row, int col, Figure figure) {
        boardRows.get(row).getFigures().add(col, figure);
        boardRows.get(row).getFigures().remove(col + 1);

    }

    public Figure getFigure(int row, int col) {
        return boardRows.get(row).getFigures().get(col);
    }

    public void move(int row1, int col1, int row2, int col2) {
        Figure movedFig = boardRows.get(row1).getFigures().get(col1);

        if (col1 != col2 && row2 != row1) {
            if (row2 >= 0 & row2 <= 7 & col2 >= 0 & col2 <= 7) {

                if (boardRows.get(row2).getFigures().get(col2).getColor() == "none") {
                    if (col2 == (col1 + 1) || col2 == (col1 - 1)) {
                        if (movedFig.getColor().equals(GREEN) && row2 == (row1 - 1))
                            moveFig(row1, col1, row2, col2, movedFig);
                        if (movedFig.getColor().equals(YEllOW) && row2 == (row1 + 1))
                            moveFig(row1, col1, row2, col2, movedFig);
                    }
                }
                if (row2 == (row1 + 2) && movedFig.getColor().equals(YEllOW)) {
                    if (boardRows.get(row2).getFigures().get(col2).getColor() == "none") {
                        if (col2 == (col1 + 2)) {
                            if (getFigure(row1 + 1, col1 + 1).getColor().equals(GREEN)) {
                                kill(row1, col1, row2, col2, movedFig, row1 + 1, col1 + 1);
                            }
                        }
                        if (col2 == (col1 - 2)) {
                            if (getFigure(row1 + 1, col1 - 1).getColor().equals(GREEN)) {
                                kill(row1, col1, row2, col2, movedFig, row1 + 1, col1 - 1);
                            }
                        }
                    }
                }
            }

            if (movedFig.getColor().equals(GREEN) && row2 == (row1 - 2)) {
                if (boardRows.get(row2).getFigures().get(col2).getColor() == "none") {
                    if (col2 == (col1 + 2)) {
                        if (getFigure(row1 - 1, col1 + 1).getColor().equals(YEllOW)) {
                            kill(row1, col1, row2, col2, movedFig, row1 - 1, col1 + 1);
                        }
                    }
                    if (col2 == (col1 - 2)) {
                        if (getFigure(row1 - 1, col1 - 1).getColor().equals(YEllOW)) {
                            kill(row1, col1, row2, col2, movedFig, row1 - 1, col1 - 1);
                        }
                    }
                }
            }
        }
    }


    private void kill(int row1, int col1, int row2, int col2, Figure movedFig, int i, int i2) {
        moveFig(row1, col1, row2, col2, movedFig);
        cleaningField(i, i2);
    }

    private void cleaningField(int i, int i2) {
        boardRows.get(i).getFigures().remove(i2);
        boardRows.get(i).getFigures().add(i2, new None("none"));
    }

    private void moveFig(int row1, int col1, int row2, int col2, Figure movedFig) {
        boardRows.get(row2).getFigures().remove(col2);
        boardRows.get(row2).getFigures().add(col2, movedFig);
        cleaningField(row1, col1);
    }

    @Override
    public String toString() {
        String s = "";
        for (Row row : boardRows) {
            s += row.toString() + "\n";
        }
        return s;
    }


    public void showBoard() {
        grid.getChildren().clear();
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++) {
                if (getFigure(row, col) instanceof Pawn) {
                    if (getFigure(row, col).getColor().equals(YEllOW)) {
                        grid.add(new Circle(30, Color.YELLOW), col, row, 1, 1);
                    } else {
                        grid.add(new Circle(30, Color.GREEN), col, row, 1, 1);
                    }
                }
                if (getFigure(row, col) instanceof Queen) {
                    if (getFigure(row, col).getColor().equals(YEllOW)) {
                        grid.add(new Rectangle(30, 30, Color.DARKORANGE), col, row, 1, 1);
                    } else {
                        grid.add(new Rectangle(30, 30, Color.DARKGREEN), col, row, 1, 1);
                    }
                }
            }
    }

    public void initBoard() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (y <= 2 && (x + y) % 2 == 0) {
                    setFigure(y, x, new Pawn(YEllOW));
                }
                if (y >= 5 && (x + y) % 2 == 0) {
                    setFigure(y, x, new Pawn(GREEN));
                }

            }
        }
    }

}