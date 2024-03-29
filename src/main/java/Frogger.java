import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Frogger extends Application {
    /*private Image imageback = new Image("file:src/main/resources/table.png");*/
    private AnimationTimer timer;

    private Pane root;

    private List<Node> cars = new ArrayList<>();
    private Node frog;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(800, 600);
        /*BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);*/

        frog = initFrog();

        root.getChildren().add(frog);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private Node initFrog() {
        Rectangle rect = new Rectangle(38, 38, Color.GREEN);
        rect.setTranslateY(600 - 39);

        return rect;
    }

    private Node spawnCar() {
        Rectangle rect = new Rectangle(40, 40, Color.RED);
        rect.setTranslateY((int)(Math.random() * 14) * 40);

        root.getChildren().add(rect);
        return rect;
    }

    private void onUpdate() {

        for (Node car : cars)
            car.setTranslateX(car.getTranslateX() + Math.random() * 10 );

        for (Node car : cars)
            if( car.getTranslateX() > 840 ) {
                root.getChildren().remove(car);
            }

        if (Math.random() < 0.075) {
            cars.add(spawnCar());
        }
        System.out.println(Math.random());
        checkState();
    }

    private void checkState() {
        for (Node car : cars) {
            if (car.getBoundsInParent().intersects(frog.getBoundsInParent())) {
                frog.setTranslateX(0);
                frog.setTranslateY(600 - 39);
                return;
            }
        }

        if (frog.getTranslateY() <= 0) {
            timer.stop();
            String win = "YOU WIN";

            HBox hBox = new HBox();
            hBox.setTranslateX(300);
            hBox.setTranslateY(250);
            root.getChildren().add(hBox);

            for (int i = 0; i < win.toCharArray().length; i++) {
                char letter = win.charAt(i);

                Text text = new Text(String.valueOf(letter));
                text.setFont(Font.font(48));
                text.setOpacity(0);

                hBox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));

        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    frog.setTranslateY(frog.getTranslateY() - 40);
                    break;
                case S:
                    frog.setTranslateY(frog.getTranslateY() + 40);
                    break;
                case A:
                    if(frog.getTranslateX() < 40) {
                        frog.setTranslateX(38);
                    }
                    frog.setTranslateX(frog.getTranslateX() - 40);
                    break;
                case D:
                    if(frog.getTranslateX() >= 801){
                        frog.setTranslateX(0);
                    }
                    frog.setTranslateX(frog.getTranslateX() + 40);
                    break;
                default:
                    break;
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}