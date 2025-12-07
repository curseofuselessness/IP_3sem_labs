package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        // Центрируем контент
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #aca9afff; -fx-padding: 20;");
        
        // Размер окна поменьше, чтобы соответствовать полю
        Scene scene = new Scene(root, 800, 600);
        
        TileField field = new TileField();
        
        // Перемешиваем
        field.fillRandomTiles();

        // Добавляем поле в интерфейс
        root.getChildren().add(field.getGrid());
        
        primaryStage.setTitle("Selection Sort Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Запускаем сортировку
        field.startSelectionSort();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}