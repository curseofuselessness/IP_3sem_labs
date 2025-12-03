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
        
        TileField field = new TileField();

        for(int i = 1; i <= 10; i++) {
            Tile tile1 = new Tile(i*30);  // Высота 50
            field.addTile(tile1, i-1);
        }
        
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