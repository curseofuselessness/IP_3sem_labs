package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class TileField {
    private List<Tile> Tiles = new ArrayList<>();
    private GridPane grid = new GridPane();
    public int TilesAmount = 0;
    private final double TILE_WIDTH = 50;  // Фиксированная ширина плитки

    public TileField() {
        this.grid.getChildren().clear();
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();

        this.grid.setHgap(0);
        this.grid.setVgap(0);
        this.grid.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
        
        // КРИТИЧЕСКИ ВАЖНО: задаем размеры для строки
        RowConstraints row = new RowConstraints();
      
        row.setValignment(VPos.BOTTOM); // выравнивание по нижнему краю
        row.setVgrow(Priority.NEVER);   // запрещаем растягивание
        this.grid.getRowConstraints().add(row);
        
        // Устанавливаем размеры GridPane
        grid.setMinSize(400, 300);
        grid.setPrefSize(600, 350);
        grid.setMaxSize(800, 400);
    }
    
    public void addTile(Tile t, int column) {
        ensureCapacity(column);
        
        t.rect.setHeight(t.height);
        t.rect.setWidth(TILE_WIDTH);
        
        // Создаем контейнер с фиксированными размерами
        StackPane container = new StackPane();
        container.setAlignment(Pos.BOTTOM_LEFT);
        container.setMinSize(TILE_WIDTH, 300);   // Высота = высоте строки
        container.setPrefSize(TILE_WIDTH, 300);
        container.getChildren().add(t.rect);
        
        // Добавляем контейнер в GridPane
        this.grid.add(container, column, 0);
        
        // Обновляем констрейнты колонок
        ensureColumnConstraints(column + 1);
        
        // Сохраняем плитку в список
        this.Tiles.set(column, t);
        this.TilesAmount = (int) Tiles.stream().filter(tile -> tile != null).count();
    }

    public void setTiles(List<Tile> LT) {

       // clearTiles();

        int col = 0;

        for (Tile tile : LT) {
            addTile(tile, col++);
        }

    }

    public void clearTiles() {
        grid.getChildren().clear();

        Tiles.clear();

        TilesAmount = 0;
                
        grid.getColumnConstraints().clear();
    }

    public void swapTiles(int i, int i1) {
        Collections.swap(Tiles, i, i1);
        setTiles(Tiles);
    }

    public GridPane getGrid() {
        return grid;
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
}