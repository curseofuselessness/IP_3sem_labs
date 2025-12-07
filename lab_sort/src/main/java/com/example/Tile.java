package com.example;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile {
    
    public Rectangle rect;
    public StackPane container;
    public double height;

    // Цвета
    public static final Color COLOR_DEFAULT = Color.WHITE;
    public static final Color COLOR_ACTIVE = Color.YELLOW; 
    public static final Color COLOR_COMPARE = Color.RED;   
    public static final Color COLOR_SWAP = Color.ORANGE; // Цвет при обмене
    public static final Color COLOR_SORTED = Color.LIGHTGREEN; 

    public Tile(double height, double tileWidth) {
        this.height = height;
        
        // Делаем прямоугольник чуть уже ширины слота, чтобы был зазор
        this.rect = new Rectangle(tileWidth - 2, height);
        this.rect.setFill(COLOR_DEFAULT);
        this.rect.setStroke(Color.BLACK);
        this.rect.setStrokeWidth(1);

        this.container = new StackPane(rect);
        // Выравнивание внутри контейнера - вниз
        this.container.setAlignment(Pos.BOTTOM_CENTER);
        // Размеры контейнера
        this.container.setPrefSize(tileWidth, height);
    }
    
    public void setColor(Color color) {
        this.rect.setFill(color);
    }
}