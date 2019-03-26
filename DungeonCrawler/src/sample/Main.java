package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<KeyEvent> {
    static final Move move = new Move();
    TextArea arena = new TextArea();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        move.generateGrid();
        move.grid[1][2]=1;
        arena.setText(move.drawMap());

        Button moveUP = new Button("UP");
        moveUP.setOnMousePressed(e -> {
            move.up();
            arena.setText(move.drawMap());

        });
        Button moveDO = new Button("DOWN");
        moveDO.setOnAction(e -> {
            move.down();
            arena.setText(move.drawMap());
        });
        Button moveLE = new Button("LEFT");
        moveLE.setOnAction(e -> {
            move.left();
            arena.setText(move.drawMap());
        });

        Button moveRI = new Button("RIGHT");
        moveRI.setOnAction(e -> {
            move.right();
            arena.setText(move.drawMap());
        });

        primaryStage.setTitle("Game Title");
        arena.setPrefRowCount(30);
        arena.setPrefColumnCount(30);

      //  arena.setEditable(false);
       arena.setDisable(true);

        VBox layout = new VBox();
        layout.getChildren().add(arena);
    //    layout.getChildren().addAll(moveUP,moveDO,moveLE,moveRI);


        Scene scene = new Scene(layout,500,500);
        scene.setOnKeyPressed(this);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                move.up();
                System.out.println('w');
                break;
            case DOWN:
                move.down();
                System.out.println('s');
                break;

            case LEFT:
                move.left();
                System.out.println('a');
                break;

            case RIGHT:
                move.right();
                System.out.println('d');
                break;

        }
        arena.setText(move.drawMap());
        System.out.println(event.toString());
    }
}
