package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TileField {

    private final List<Tile> tiles = new ArrayList<>();
    // Используем Pane вместо GridPane для свободного движения
    private final Pane root = new Pane();
    
    private SequentialTransition currentAnimation;

    // Настройки
    private static final int TILE_COUNT = 50; 
    private static final double FIELD_WIDTH = 800;
    private static final double FIELD_HEIGHT = 450;
    // Вычисляем точную ширину плитки
    private static final double TILE_WIDTH = FIELD_WIDTH / TILE_COUNT;
    
    // Скорости анимации (чем меньше, тем быстрее)
    private static final double DELAY_COMPARE = 0.02; 
    private static final double DELAY_SWAP = 0.05;

    public TileField() {
        root.setStyle("-fx-background-color: #333333; -fx-border-color: black; -fx-border-width: 2;");
        root.setPrefSize(FIELD_WIDTH, FIELD_HEIGHT);
        
        // Начальное заполнение
        fillRandomTiles();
    }

    //----------------------------------------------------------------------
    // Управление состоянием
    //----------------------------------------------------------------------
    
    public void fillRandomTiles() {
        stopAllAnimations(); 
        
        root.getChildren().clear();
        tiles.clear();
        
        Random r = new Random();
        for (int i = 0; i < TILE_COUNT; i++) {
            double h = 20 + (FIELD_HEIGHT - 30) * r.nextDouble();
            
            // Создаем плитку с точной шириной
            Tile t = new Tile(h, TILE_WIDTH);
            
            // СТАВИМ ПЛИТКУ НА НУЖНУЮ КООРДИНАТУ X
            t.container.setTranslateX(i * TILE_WIDTH);
            // Прижимаем к низу (высота поля - высота плитки)
            t.container.setTranslateY(FIELD_HEIGHT - h);
            
            root.getChildren().add(t.container);
            tiles.add(t);
        }
    }
    
    private void stopAllAnimations() {
        if (currentAnimation != null) {
            currentAnimation.stop();
        }
        for(Tile t : tiles) t.setColor(Tile.COLOR_DEFAULT);
    }

    //----------------------------------------------------------------------
    // 1. Selection Sort
    //----------------------------------------------------------------------
    public void startSelectionSort() {
        stopAllAnimations();
        SequentialTransition seq = new SequentialTransition();
        List<Tile> simList = new ArrayList<>(tiles);

        for (int i = 0; i < simList.size(); i++) {
            int minIndex = i;
            Tile tI = simList.get(i);
            
            seq.getChildren().add(highlight(tI, Tile.COLOR_ACTIVE));

            for (int j = i + 1; j < simList.size(); j++) {
                Tile tJ = simList.get(j);
                Tile tMin = simList.get(minIndex);
                
                seq.getChildren().add(compareEffect(tJ, tMin)); 

                if (tJ.height < tMin.height) {
                    minIndex = j;
                }
            }
            
            if (minIndex != i) {
                Tile tA = simList.get(i);
                Tile tB = simList.get(minIndex);
                Collections.swap(simList, i, minIndex);
                // Передаем новые индексы для движения
                seq.getChildren().add(swapAnim(tA, tB, i, minIndex));
            }
            seq.getChildren().add(highlight(tI, Tile.COLOR_DEFAULT));
        }
        playAnimation(seq, simList);
    }

    //----------------------------------------------------------------------
    // 2. Bubble Sort
    //----------------------------------------------------------------------
    public void startBubbleSort() {
        stopAllAnimations();
        SequentialTransition seq = new SequentialTransition();
        List<Tile> simList = new ArrayList<>(tiles);
        int n = simList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Tile t1 = simList.get(j);
                Tile t2 = simList.get(j + 1);

                seq.getChildren().add(compareEffect(t1, t2));

                if (t1.height > t2.height) {
                    Collections.swap(simList, j, j + 1);
                    seq.getChildren().add(swapAnim(t1, t2, j, j + 1));
                }
            }
        }
        playAnimation(seq, simList);
    }

    //----------------------------------------------------------------------
    // 3. Insertion Sort
    //----------------------------------------------------------------------
    public void startInsertionSort() {
        stopAllAnimations();
        SequentialTransition seq = new SequentialTransition();
        List<Tile> simList = new ArrayList<>(tiles);
        int n = simList.size();

        for (int i = 1; i < n; i++) {
            int j = i;
            while (j > 0) {
                Tile current = simList.get(j);
                Tile left = simList.get(j - 1);

                seq.getChildren().add(compareEffect(current, left));

                if (current.height < left.height) {
                    Collections.swap(simList, j, j - 1);
                    seq.getChildren().add(swapAnim(current, left, j, j - 1));
                    j--;
                } else {
                    break;
                }
            }
        }
        playAnimation(seq, simList);
    }

    //----------------------------------------------------------------------
    // Анимации
    //----------------------------------------------------------------------
    private void playAnimation(SequentialTransition seq, List<Tile> finalState) {
        currentAnimation = seq;
        currentAnimation.setOnFinished(e -> {
            tiles.clear();
            tiles.addAll(finalState);
            for(Tile t : tiles) t.setColor(Tile.COLOR_SORTED);
        });
        currentAnimation.play();
    }

    // ИСПРАВЛЕННЫЙ SWAP: Двигаем TranslateX
    private ParallelTransition swapAnim(Tile t1, Tile t2, int index1, int index2) {
        ParallelTransition pt = new ParallelTransition();
        
        // Вычисляем новые координаты X
        double x1 = index1 * TILE_WIDTH; // Куда должен встать t2 (на место index1)
        double x2 = index2 * TILE_WIDTH; // Куда должен встать t1 (на место index2)
        
        // t1 едет на место index2
        Timeline move1 = new Timeline(
            new KeyFrame(Duration.ZERO, e -> t1.setColor(Tile.COLOR_SWAP)),
            new KeyFrame(Duration.seconds(DELAY_SWAP), e -> {
                t1.container.setTranslateX(x2);
            }),
             new KeyFrame(Duration.seconds(DELAY_SWAP + 0.05), e -> t1.setColor(Tile.COLOR_DEFAULT))
        );
        
        // t2 едет на место index1
        Timeline move2 = new Timeline(
            new KeyFrame(Duration.ZERO, e -> t2.setColor(Tile.COLOR_SWAP)),
            new KeyFrame(Duration.seconds(DELAY_SWAP), e -> {
                t2.container.setTranslateX(x1);
            }),
            new KeyFrame(Duration.seconds(DELAY_SWAP + 0.05), e -> t2.setColor(Tile.COLOR_DEFAULT))
        );

        pt.getChildren().addAll(move1, move2);
        return pt;
    }

    private Timeline compareEffect(Tile t1, Tile t2) {
        return new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                t1.setColor(Tile.COLOR_COMPARE);
                t2.setColor(Tile.COLOR_COMPARE);
            }),
            new KeyFrame(Duration.seconds(DELAY_COMPARE), e -> {
                t1.setColor(Tile.COLOR_DEFAULT);
                t2.setColor(Tile.COLOR_DEFAULT);
            })
        );
    }

    private Timeline highlight(Tile t, Color c) {
        return new Timeline(new KeyFrame(Duration.ZERO, e -> t.setColor(c)));
    }

    public Pane getRoot() { return root; }
}