package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class TileField {

    private final List<Tile> tiles = new ArrayList<>();
    private final GridPane grid = new GridPane();

    // Настройки
    private static final int TILE_COUNT = 60;
    private static final double TILE_WIDTH = 10;
    private static final double FIELD_WIDTH = 600;
    private static final double FIELD_HEIGHT = 400;
    private static final double DELAY_COMPARE = 0.05; // Быстрое сравнение
    private static final double DELAY_SWAP = 0.2;     // Медленный обмен

    public TileField() {
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setStyle("-fx-background-color: #333333;");
        grid.setMinSize(FIELD_WIDTH, FIELD_HEIGHT);
        grid.setMaxSize(FIELD_WIDTH, FIELD_HEIGHT);

        RowConstraints row = new RowConstraints();
        row.setValignment(VPos.BOTTOM);
        row.setVgrow(Priority.ALWAYS); // Changed to ALWAYS for better alignment
        grid.getRowConstraints().add(row);

        for (int i = 0; i < TILE_COUNT; i++) {
            Tile t = new Tile((i + 1) * (FIELD_HEIGHT / TILE_COUNT));
            addTile(t, i);
        }
    }

    private void addTile(Tile t, int column) {
        ensureColumnExists(column);
        GridPane.setColumnIndex(t.container, column);
        GridPane.setRowIndex(t.container, 0);
        grid.getChildren().add(t.container);

        if (tiles.size() <= column) {
            tiles.add(t);
        } else {
            tiles.set(column, t);
        }
    }

    private void ensureColumnExists(int index) {
        while (grid.getColumnConstraints().size() <= index) {
            ColumnConstraints col = new ColumnConstraints();
            col.setMinWidth(TILE_WIDTH);
            col.setPrefWidth(TILE_WIDTH);
            col.setMaxWidth(TILE_WIDTH);
            col.setHgrow(Priority.NEVER);
            grid.getColumnConstraints().add(col);
        }
    }

    //----------------------------------------------------------------------
    // Selection Sort с предварительной симуляцией
    //----------------------------------------------------------------------
    public void startSelectionSort() {
        SequentialTransition sequence = new SequentialTransition();
        
        // 1. Создаем временную копию списка для логической сортировки
        List<Tile> simulationList = new ArrayList<>(tiles);

        // 2. Проходим алгоритмом по копии и записываем анимации
        for (int i = 0; i < simulationList.size(); i++) {
            int minIndex = i;
            Tile tileI = simulationList.get(i);

            // Анимация: подсвечиваем текущую позицию, которую ищем
            sequence.getChildren().add(createHighlight(tileI, Tile.COLOR_CURRENT));

            for (int j = i + 1; j < simulationList.size(); j++) {
                Tile tileJ = simulationList.get(j);
                Tile tileMin = simulationList.get(minIndex);

                // Анимация: сравнение (кратковременная подсветка)
                sequence.getChildren().add(createCompareEffect(tileJ, tileMin));

                if (tileJ.height < tileMin.height) {
                    minIndex = j;
                    // Если нашли новый минимум - можно добавить спец. подсветку (опционально)
                }
            }

            // Анимация: убираем подсветку "текущего"
            sequence.getChildren().add(createHighlight(tileI, Tile.COLOR_DEFAULT));

            // Если минимум не на своем месте - меняем
            if (minIndex != i) {
                Tile tileA = simulationList.get(i);
                Tile tileB = simulationList.get(minIndex);

                // Логический обмен в симуляции
                Collections.swap(simulationList, i, minIndex);

                // Добавляем анимацию обмена. Важно передать целевые колонки (i и minIndex)
                sequence.getChildren().add(createSwapAnimation(tileA, tileB, i, minIndex));
            }
        }

        // 3. Когда анимация закончится, обновляем реальный список tiles, 
        // чтобы он соответствовал визуальному состоянию
        sequence.setOnFinished(e -> {
            tiles.clear();
            tiles.addAll(simulationList);
            System.out.println("Sorting finished!");
        });

        sequence.play();
    }

    //--- Анимационные хелперы ---

    // Анимация обмена местами (визуальная)
    private ParallelTransition createSwapAnimation(Tile t1, Tile t2, int col1, int col2) {
        ParallelTransition pt = new ParallelTransition();

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                t1.setColor(Tile.COLOR_SWAP);
                t2.setColor(Tile.COLOR_SWAP);
            }),
            new KeyFrame(Duration.seconds(DELAY_SWAP), e -> {
                // Меняем колонки в GridPane
                GridPane.setColumnIndex(t1.container, col2); // t1 идет на место col2
                GridPane.setColumnIndex(t2.container, col1); // t2 идет на место col1
            }),
            new KeyFrame(Duration.seconds(DELAY_SWAP * 1.5), e -> {
                t1.setColor(Tile.COLOR_DEFAULT);
                t2.setColor(Tile.COLOR_DEFAULT);
            })
        );
        pt.getChildren().add(timeline);
        return pt;
    }

    // Эффект сравнения (быстро мигнуть)
    private Timeline createCompareEffect(Tile t1, Tile t2) {
        return new Timeline(
            new KeyFrame(Duration.ZERO, e -> t2.setColor(Tile.COLOR_COMPARE)),
            new KeyFrame(Duration.seconds(DELAY_COMPARE), e -> t2.setColor(Tile.COLOR_DEFAULT))
        );
    }

    // Просто переключить цвет (надолго)
    private Timeline createHighlight(Tile t, javafx.scene.paint.Color color) {
        return new Timeline(
            new KeyFrame(Duration.ZERO, e -> t.setColor(color))
        );
    }

    public void fillRandomTiles() {
        Collections.shuffle(tiles, new Random());
        for (int i = 0; i < tiles.size(); i++) {
            GridPane.setColumnIndex(tiles.get(i).container, i);
        }
    }
    
    public GridPane getGrid() { return grid; }
}