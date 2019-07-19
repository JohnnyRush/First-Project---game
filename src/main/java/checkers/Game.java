package checkers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Game extends Application {
    private int x1, y1;
    private int x2, y2;
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
            grid.getRowConstraints().add(new RowConstraints(70));
        }
        Board board = new Board(grid);

        grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                clicCount++;
                if(clicCount == 1) {
                    getOldPosition(e);
                }
                if(clicCount == 2) {
                    getNewPosition(e);

                    System.out.println(y1 + ""+ x1 +""+ y2 +""+ x2);
                    board.move(y1, x1, y2, x2);
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

    private void getOldPosition(MouseEvent e) {
        x1 = (int) e.getSceneX() / 70;
        y1 = (int) e.getSceneY() / 70;
    }

    private void getNewPosition(MouseEvent e) {
        x2 = (int) e.getSceneX() / 70;
        y2 = (int) e.getSceneY() / 70;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
