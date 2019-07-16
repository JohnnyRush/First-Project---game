package checkers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Game extends Application {
    private int col1, row1;
    private int col2, row2;
    private int clicCount = 0;



    private Image imageback = new Image("file:src/main/resources/board.jpg");

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(560, 560, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setBackground(background);
        Scene scene = new Scene(grid, 560, 560);
        primaryStage.setResizable(false);

        for (int i = 0; i < 8; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(70));
        }
        for (int i = 0; i < 8; i++) {
            grid.getRowConstraints().add(new RowConstraints(70));
        }
        Board board = new Board(grid);

        grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                clicCount++;
                if(clicCount == 1) {
                    col1 = (int) e.getSceneX() / 70;
                    row1 = (int) e.getSceneY() / 70;

                }
                if(clicCount == 2) {
                    col2 = (int) e.getSceneX() / 70;
                    row2 = (int) e.getSceneY() / 70;
                    System.out.println(row1 + ""+ col1 +""+ row2 +""+ col2);
                    board.move(row1, col1, row2, col2);
                    clicCount = 0;
                }
                board.showBoard();
            }

        });



        board.initBoard();
        board.showBoard();
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
