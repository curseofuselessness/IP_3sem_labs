package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private LibraryController controller;
    
    @Override
    public void start(Stage primaryStage) {
        controller = new LibraryController();
        
        // Создание меню
        MenuBar menuBar = createMenuBar();
        
        // Создание основного интерфейса
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(controller.getTableView());
        
        // Создание сцены
        Scene scene = new Scene(root, 1000, 600);
        // Если есть CSS файл:
        // scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        
        // Настройка окна
        primaryStage.setTitle("Библиотечная система");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Загрузка XML
        controller.loadLibraryFromXML();
    }
    
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        // Меню Файл
        Menu fileMenu = new Menu("Файл");
        MenuItem loadItem = new MenuItem("Загрузить XML");
        loadItem.setOnAction(e -> controller.loadLibraryFromXML());
        
        MenuItem saveItem = new MenuItem("Сохранить XML");
        saveItem.setOnAction(e -> controller.saveLibraryToXML());
        
        MenuItem exitItem = new MenuItem("Выход");
        exitItem.setOnAction(e -> System.exit(0));
        
        fileMenu.getItems().addAll(loadItem, saveItem, new SeparatorMenuItem(), exitItem);
        
        // Меню Книги
        Menu booksMenu = new Menu("Книги");
        
        MenuItem addBookItem = new MenuItem("Добавить книгу");
        addBookItem.setOnAction(e -> controller.showAddBookDialog());
        
        // Меню Поиск (это Menu, а не MenuItem!)
        Menu searchMenu = new Menu("Поиск");
        
        MenuItem searchByAuthor = new MenuItem("По автору");
        searchByAuthor.setOnAction(e -> controller.showSearchByAuthorDialog());
        
        MenuItem searchByYear = new MenuItem("По году");
        searchByYear.setOnAction(e -> controller.showSearchByYearDialog());
        
        MenuItem searchByCategory = new MenuItem("По категории");
        searchByCategory.setOnAction(e -> controller.showSearchByCategoryDialog());
        
        // Теперь правильно добавляем в searchMenu
        searchMenu.getItems().addAll(searchByAuthor, searchByYear, searchByCategory);
        
        MenuItem updatePriceItem = new MenuItem("Изменить цену");
        updatePriceItem.setOnAction(e -> controller.showUpdatePriceDialog());
        
        MenuItem borrowBookItem = new MenuItem("Выдать книгу");
        borrowBookItem.setOnAction(e -> controller.showBorrowBookDialog());
        
        // Добавляем все в меню Книги
        booksMenu.getItems().addAll(
            addBookItem, 
            searchMenu,          // Это Menu, у него есть getItems()
            updatePriceItem, 
            borrowBookItem
        );
        
        menuBar.getMenus().addAll(fileMenu, booksMenu);
        return menuBar;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}