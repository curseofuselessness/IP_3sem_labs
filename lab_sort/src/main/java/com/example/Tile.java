package com.example;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile {
    
    public Rectangle rect;
    public double height;

    private boolean isSelected = false;

    Tile(double h) { // height
        this.rect = new Rectangle(10, height); 
        this.height = h;
        this.rect.setFill(Color.WHITE); // Заливка
        this.rect.setStroke(Color.BLACK); // Граница
        this.rect.setStrokeWidth(2); // Толщина границы

        this.rect.minWidth(30);
        this.rect.minHeight(height);
        this.rect.prefWidth(30);
        this.rect.prefHeight(height);
    }
    
    public void select(boolean b) {
        this.isSelected = b;

        if(isSelected) {
            this.rect.setFill(Color.RED);
        } else {
            this.rect.setFill(Color.WHITE);
        }
    }

}
