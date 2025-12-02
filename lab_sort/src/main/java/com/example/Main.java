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
        
        Scene scene = new Scene(root, 400, 300);
        
        // Создаем разные плитки
        Tile tile1 = new Tile(50);  // Высота 50
        tile1.select(true);
        Tile tile2 = new Tile(100); // Высота 100
        Tile tile3 = new Tile(150); // Высота 150
        
        TileField field = new TileField();
        
        // Добавляем плитки в разные колонки
        field.addTile(tile1, 0);
        field.addTile(tile2, 1);
        field.addTile(tile3, 2);
        
        root.getChildren().add(field.getGrid());
        primaryStage.setTitle("Tile Field Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}