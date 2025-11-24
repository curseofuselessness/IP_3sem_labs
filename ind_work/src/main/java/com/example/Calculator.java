package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {
    
    private static boolean launched = false;

    private TextField display;
    private double num1 = 0;
    private String operator = "";
    private boolean startNewNumber = true;
    private boolean calculated = false;
    
    public static void main(String[] args) {
         if (!launched) {
            launched = true;
            launch(args);
        }

       // mvn javafx:run // for RUN

    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор");
        
        // display
        display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setStyle("-fx-font-size: 24; -fx-pref-height: 60; -fx-background-color: #2b2b2b; -fx-text-fill: white; -fx-border-color: #444;");
        
        // Buttons
        String[][] buttonLayout = {
            {"C", "<-", "%", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"±", "0", ".", "="}
        };
        
        // Grid
        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(15));
        
        for (int row = 0; row < buttonLayout.length; row++) {
            for (int col = 0; col < buttonLayout[row].length; col++) {
                Button button = createButton(buttonLayout[row][col]);
                grid.add(button, col, row);
            }
        }

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1e1e1e;");
        root.getChildren().addAll(display, grid);
        
        Scene scene = new Scene(root, 350, 500);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private Button createButton(String text) {
    Button button = new Button(text);
    button.setPrefSize(70, 70);
    
    // Styles
    if (text.matches("[0-9]")) {
        // Nums
        button.setStyle("-fx-font-size: 18; -fx-background-color: #4a4a4a; -fx-text-fill: white; -fx-border-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 18; -fx-background-color: #5a5a5a; -fx-text-fill: white; -fx-border-radius: 5; -fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 18; -fx-background-color: #4a4a4a; -fx-text-fill: white; -fx-border-radius: 5; -fx-scale-x: 1.0; -fx-scale-y: 1.0;"));
    } else if (text.matches("[+\\-*/=]")) {
        // Operations
        button.setStyle("-fx-font-size: 18; -fx-background-color: #ff9500; -fx-text-fill: white; -fx-border-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 18; -fx-background-color: #ffb143; -fx-text-fill: white; -fx-border-radius: 5; -fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 18; -fx-background-color: #ff9500; -fx-text-fill: white; -fx-border-radius: 5; -fx-scale-x: 1.0; -fx-scale-y: 1.0;"));
    } else {
        // Special
        button.setStyle("-fx-font-size: 16; -fx-background-color: #a6a6a6; -fx-text-fill: black; -fx-border-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 16; -fx-background-color: #b6b6b6; -fx-text-fill: black; -fx-border-radius: 5; -fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 16; -fx-background-color: #a6a6a6; -fx-text-fill: black; -fx-border-radius: 5; -fx-scale-x: 1.0; -fx-scale-y: 1.0;"));
    }
    
    button.setOnAction(e -> handleButtonClick(text));
    return button;
}
    
    private void handleButtonClick(String value) {
        switch (value) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                handleNumber(value);
                break;
            case ".":
                handleDecimal();
                break;
            case "+": case "-": case "*": case "/":
                handleOperator(value);
                break;
            case "=":
                handleEquals();
                break;
            case "C":
                handleClear();
                break;
            case "<-":
                handleBackspace();
                break;
            case "%":
                handlePercent();
                break;
            case "±":
                handlePlusMinus();
                break;
        }
    }
    
    private void handleNumber(String number) {
        if (startNewNumber || display.getText().equals("0") || calculated) {
            display.setText(number);
            startNewNumber = false;
            calculated = false;
        } else {
            display.setText(display.getText() + number);
        }
    }
    
    private void handleDecimal() {
        if (startNewNumber || calculated) {
            display.setText("0.");
            startNewNumber = false;
            calculated = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }
    
    private void handleOperator(String op) {
        if (!operator.isEmpty() && !startNewNumber) {
            handleEquals();
        }
        
        try {
            num1 = Double.parseDouble(display.getText());
            operator = op;
            startNewNumber = true;
            calculated = false;
        } catch (NumberFormatException e) {
            display.setText("Error");
            startNewNumber = true;
        }
    }
    
    private void handleEquals() {
        if (operator.isEmpty()) return;
        
        try {
            double num2 = Double.parseDouble(display.getText());
            double result = calculate(num1, num2, operator);
            
            if (result == (long) result) {
                display.setText(String.format("%d", (long) result));
            } else {
                display.setText(String.format("%s", result));
            }
            
            operator = "";
            startNewNumber = true;
            calculated = true;
        } catch (NumberFormatException | ArithmeticException e) {
            display.setText("Error");
            startNewNumber = true;
            operator = "";
        }
    }
    
    private void handleClear() {
        display.setText("0");
        num1 = 0;
        operator = "";
        startNewNumber = true;
        calculated = false;
    }
    
    private void handleBackspace() {
        String currentText = display.getText();
        if (currentText.length() > 1 && !currentText.equals("0")) {
            display.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            display.setText("0");
            startNewNumber = true;
        }
    }
    
    private void handlePercent() {
        try {
            double value = Double.parseDouble(display.getText());
            double result = value / 100;
            
            if (result == (long) result) {
                display.setText(String.format("%d", (long) result));
            } else {
                display.setText(String.format("%s", result));
            }
            
            startNewNumber = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
            startNewNumber = true;
        }
    }
    
    private void handlePlusMinus() {
        try {
            double value = Double.parseDouble(display.getText());
            value = -value;
            
            if (value == (long) value) {
                display.setText(String.format("%d", (long) value));
            } else {
                display.setText(String.format("%s", value));
            }
        } catch (NumberFormatException e) {
            display.setText("Error");
            startNewNumber = true;
        }
    }
    
    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+": 
                return num1 + num2;
            case "-": 
                return num1 - num2;
            case "*": 
                return num1 * num2;
            case "/": 
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default: 
                return num2;
        }
    }
}