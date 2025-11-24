import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Создаем элементы интерфейса
        Label label = new Label("Hello JavaFX!");
        Button button = new Button("Click me!");
        
        // Обработчик нажатия кнопки
        button.setOnAction(e -> {
            label.setText("Button clicked!");
        });
        
        // Создаем layout
        VBox root = new VBox(10);
        root.getChildren().addAll(label, button);
        
        // Создаем сцену
        Scene scene = new Scene(root, 300, 200);
        
        // Настраиваем stage
        primaryStage.setTitle("JavaFX in VS Code");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}