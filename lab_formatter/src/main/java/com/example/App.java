package com.example;

public class App {
    public static void main(String[] args) {
    TextRedactor text = new TextRedactor();
    text.readText();
    text.deletePunct();
    text.display();
    text.deleteK();
    text.display();
    }
}