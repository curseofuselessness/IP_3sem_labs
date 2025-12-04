package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class TileField {

    private List<Tile> Tiles = new ArrayList<>();
    private GridPane grid = new GridPane();

    // Параметры Плиток

    private static final double TILE_WIDTH = 10;  
    private static final int TILES_AMOUNT = 60;

    // Параметры поля

    private static final double FIELD_WIDTH = 600;
    private static final int FIELD_HEIGHT = 400;

    // 

    private static final double DELAY = 0.2;

    public TileField() {
        this.grid.getChildren().clear();
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();

        this.grid.setHgap(0);
        this.grid.setVgap(0);
        this.grid.setStyle("-fx-padding: 0; -fx-background-color: #333333ff;");
        
        // КРИТИЧЕСКИ ВАЖНО: задаем размеры для строки
        RowConstraints row = new RowConstraints();
      
        row.setValignment(VPos.BOTTOM); // выравнивание по нижнему краю
        row.setVgrow(Priority.NEVER);   // запрещаем растягивание
        this.grid.getRowConstraints().add(row);
        
        // Устанавливаем размеры GridPane
        grid.setMinSize(FIELD_WIDTH, FIELD_HEIGHT);
        grid.setMaxSize(FIELD_WIDTH, FIELD_HEIGHT);

        for(int i = 1; i <= getTilesAmount(); i++) {
            Tile tile1 = new Tile(i*(400f / getTilesAmount()));  
            addTile(tile1, i-1);
        }
        
    }
    
    public void addTile(Tile t, int column) {
        ensureCapacity(column);
        
        t.rect.setHeight(t.height);
        t.rect.setWidth(TILE_WIDTH);
        
        // Создаем контейнер с фиксированными размерами
        StackPane container = new StackPane();
        container.setAlignment(Pos.BOTTOM_LEFT);
        container.setPrefSize(TILE_WIDTH, 300);
        container.getChildren().add(t.rect);
        
        // Добавляем контейнер в GridPane
        this.grid.add(container, column, 0);
        
        // Обновляем констрейнты колонок
        ensureColumnConstraints(column + 1);
        
        // Сохраняем плитку в список
        this.Tiles.set(column, t);
    }

    public void setTiles(List<Tile> LT) {

        int col = 0;

        for (Tile tile : LT) {
            addTile(tile, col++);
        }

    }

    public void clearTiles() {
        grid.getChildren().clear();

        Tiles.clear();
                
        grid.getColumnConstraints().clear();
    }

    public void swapTiles(int i, int i1) {
        Collections.swap(Tiles, i, i1);

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, event -> {
                Tiles.get(i1).select(true);
                Tiles.get(i).select(true);
            }),
            new KeyFrame(Duration.seconds(DELAY), event -> {
                // Через 1 секунду
                setTiles(Tiles);
            }),
            new KeyFrame(Duration.seconds(2 * DELAY), event -> {
                // Через 2 секунды
                Tiles.get(i1).select(false);
                Tiles.get(i).select(false);
                setTiles(Tiles);
            })
        );

        timeline.play();

        
    }

    

    private void ensureCapacity(int index) {
        while (this.Tiles.size() <= index) {
            this.Tiles.add(null);
        }
    }
    
    private void ensureColumnConstraints(int numColumns) {
        // Убедимся, что есть констрейнты для всех колонок
        while (grid.getColumnConstraints().size() < numColumns) {
            ColumnConstraints col = new ColumnConstraints();
            col.setMinWidth(TILE_WIDTH);
            col.setPrefWidth(TILE_WIDTH);
            col.setMaxWidth(TILE_WIDTH);
            col.setHgrow(Priority.NEVER); // запрещаем растягивание
            grid.getColumnConstraints().add(col);
        }
    }

    public void fillRandomTiles(){
        Random rand = new Random();

        Collections.shuffle(Tiles, rand);

        setTiles(Tiles);   
    }

    // Геттеры

    public GridPane getGrid() {
        return grid;
    }

    public static int getTilesAmount(){
        return TILES_AMOUNT;
    }

    public static double getFieldWidth(){
        return FIELD_WIDTH;
    }
    public static double getFieldHeight(){
        return FIELD_HEIGHT;
    }

    public List<Tile> getTiles() {
        return Tiles;
    }
}