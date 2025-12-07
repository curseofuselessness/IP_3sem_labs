package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TileField field;

    @Override
    public void start(Stage primaryStage) {
        
        // 1. Создаем объект поля (где рисуются плитки)
        field = new TileField();

        // 2. Создаем элементы управления
        
        // Заголовок
        Label titleLabel = new Label("Визуализация алгоритмов сортировки");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");

        // Выпадающий список для выбора алгоритма
        ComboBox<String> algoSelector = new ComboBox<>();
        algoSelector.getItems().addAll(
            "Selection Sort", 
            "Bubble Sort", 
            "Insertion Sort"
        );
        algoSelector.setValue("Selection Sort"); // Выбрано по умолчанию
        algoSelector.setStyle("-fx-font-size: 14px;");
        algoSelector.setPrefWidth(150);

        // Кнопка для перемешивания (генерации новых данных)
        Button btnRandom = new Button("Перемешать");
        btnRandom.setStyle("-fx-font-size: 14px;");
        
        // Кнопка для запуска выбранной сортировки
        Button btnStart = new Button("Запуск");
        btnStart.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        // 3. Настраиваем действия кнопок

        // Кнопка "Перемешать"
        btnRandom.setOnAction(e -> {
            field.fillRandomTiles();
        });
        
        // Кнопка "Запуск"
        btnStart.setOnAction(e -> {
            String selectedAlgo = algoSelector.getValue();
            
            // Запускаем нужный метод в зависимости от выбора в меню
            switch (selectedAlgo) {
                case "Selection Sort":
                    field.startSelectionSort();
                    break;
                case "Bubble Sort":
                    field.startBubbleSort();
                    break;
                case "Insertion Sort":
                    field.startInsertionSort();
                    break;
            }
        });

        // 4. Компоновка интерфейса (Layout)

        // Панель с кнопками (горизонтальная)
        HBox controls = new HBox(15, algoSelector, btnRandom, btnStart);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(15));
        controls.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 10;");
        controls.setMaxWidth(600); // Ограничим ширину панели кнопок

        // Основной контейнер (вертикальный)
        VBox root = new VBox(20); // 20 - расстояние между элементами по вертикали
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #aca9afff;");
        
        // Добавляем все элементы: Заголовок, Поле с плитками, Кнопки
        // ВАЖНО: используем field.getRoot(), так как в TileField мы заменили GridPane на Pane
        root.getChildren().addAll(titleLabel, field.getRoot(), controls);

        // 5. Создание сцены и отображение окна
        Scene scene = new Scene(root, 900, 700);
        
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}