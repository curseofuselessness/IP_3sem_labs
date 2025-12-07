package com.example;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile {
    
    public Rectangle rect;
    public StackPane container;
    public double height;

    // Цветовые константы для разных состояний
    public static final Color COLOR_DEFAULT = Color.WHITE;
    public static final Color COLOR_CURRENT = Color.YELLOW; // Тот элемент, для которого ищем пару
    public static final Color COLOR_COMPARE = Color.RED;    // Тот, с которым сравниваем
    public static final Color COLOR_SWAP = Color.LIME;      // Момент обмена

    public Tile(double height) {
        this.height = height;
        this.rect = new Rectangle(10, height);
        this.rect.setFill(COLOR_DEFAULT);
        this.rect.setStroke(Color.BLACK);
        this.rect.setStrokeWidth(1);

        this.container = new StackPane(rect);
        this.container.setAlignment(Pos.BOTTOM_LEFT);
        
        // ВАЖНО: Не ставьте жесткую высоту PrefSize, иначе StackPane
        // может "отталкивать" другие элементы или обрезаться.
        // Достаточно ширины.
        this.container.setMinWidth(10);
        this.container.setMaxWidth(10);
    }
    
    public void setColor(Color color) {
        this.rect.setFill(color);
    }
}