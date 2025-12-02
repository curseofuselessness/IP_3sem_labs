package com.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// mvn javafx:run

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) {
        
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #aca9afff; -fx-padding: 20;");
        
        label.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");
        
        root.getChildren().add(label);
        
        Scene scene = new Scene(root, 400, 300);
        
        Tile test = new Tile(50, 50, 20, 100);

        root.getChildren().addAll(test.rect);
        primaryStage.setTitle("Dark Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
