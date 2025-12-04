package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #aca9afff; -fx-padding: 20;");
        
        Scene scene = new Scene(root, 1800, 900);
        
        TileField field = new TileField();

        field.fillRandomTiles();
        
        field.swapTiles(1, 4);
        
        // Добавляем плитки в разные колонки
        

        
        root.getChildren().add(field.getGrid());
        primaryStage.setTitle("Tile Field Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}