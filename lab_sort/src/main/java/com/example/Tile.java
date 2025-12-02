package com.example;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile {
    
    public Rectangle rect;
    public double height;

    Tile(double d, double d1, double d2, double d3) { // x, y, width, height
        rect = new Rectangle(d, d1, d2, d3); 
        height = d3;
        rect.setFill(Color.WHITE); // Заливка
        rect.setStroke(Color.BLACK); // Граница
        rect.setStrokeWidth(2); // Толщина границы
    }
    
        

}
