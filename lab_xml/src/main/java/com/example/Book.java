package com.example;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final IntegerProperty year;
    private final StringProperty category;
    private final DoubleProperty price;
    private final StringProperty isbn;
    private final IntegerProperty totalCopies;
    private final IntegerProperty availableCopies;
    
    public Book(int id, String title, String author, int year, 
                String category, double price, String isbn, 
                int totalCopies, int availableCopies) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.year = new SimpleIntegerProperty(year);
        this.category = new SimpleStringProperty(category);
        this.price = new SimpleDoubleProperty(price);
        this.isbn = new SimpleStringProperty(isbn);
        this.totalCopies = new SimpleIntegerProperty(totalCopies);
        this.availableCopies = new SimpleIntegerProperty(availableCopies);
    }
    
    // Геттеры и сеттеры для id
    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }
    
    // Геттеры и сеттеры для title
    public String getTitle() { return title.get(); }
    public void setTitle(String value) { title.set(value); }
    public StringProperty titleProperty() { return title; }
    
    // Геттеры и сеттеры для author
    public String getAuthor() { return author.get(); }
    public void setAuthor(String value) { author.set(value); }
    public StringProperty authorProperty() { return author; }
    
    // Геттеры и сеттеры для year
    public int getYear() { return year.get(); }
    public void setYear(int value) { year.set(value); }
    public IntegerProperty yearProperty() { return year; }
    
    // Геттеры и сеттеры для category
    public String getCategory() { return category.get(); }
    public void setCategory(String value) { category.set(value); }
    public StringProperty categoryProperty() { return category; }
    
    // Геттеры и сеттеры для price
    public double getPrice() { return price.get(); }
    public void setPrice(double value) { price.set(value); }
    public DoubleProperty priceProperty() { return price; }
    
    // Геттеры и сеттеры для isbn
    public String getIsbn() { return isbn.get(); }
    public void setIsbn(String value) { isbn.set(value); }
    public StringProperty isbnProperty() { return isbn; }
    
    // Геттеры и сеттеры для totalCopies
    public int getTotalCopies() { return totalCopies.get(); }
    public void setTotalCopies(int value) { totalCopies.set(value); }
    public IntegerProperty totalCopiesProperty() { return totalCopies; }
    
    // Геттеры и сеттеры для availableCopies
    public int getAvailableCopies() { return availableCopies.get(); }
    public void setAvailableCopies(int value) { availableCopies.set(value); }
    public IntegerProperty availableCopiesProperty() { return availableCopies; }
    
    // Дополнительные полезные методы
    
    /**
     * Проверяет, доступна ли книга для выдачи
     */
    public boolean isAvailable() {
        return availableCopies.get() > 0;
    }
    
    /**
     * Выдает книгу (уменьшает количество доступных экземпляров)
     * @return true если книга была успешно выдана, false если нет доступных экземпляров
     */
    public boolean borrow() {
        if (availableCopies.get() > 0) {
            availableCopies.set(availableCopies.get() - 1);
            return true;
        }
        return false;
    }
    
    /**
     * Возвращает книгу (увеличивает количество доступных экземпляров)
     * @return true если книга была успешно возвращена, false если превышено общее количество
     */
    public boolean returnBook() {
        if (availableCopies.get() < totalCopies.get()) {
            availableCopies.set(availableCopies.get() + 1);
            return true;
        }
        return false;
    }
    
    /**
     * Проверяет, все ли экземпляры книги выданы
     */
    public boolean isAllBorrowed() {
        return availableCopies.get() == 0;
    }
    
    /**
     * Проверяет, все ли экземпляры книги в наличии
     */
    public boolean isAllAvailable() {
        return availableCopies.get() == totalCopies.get();
    }
    
    /**
     * Возвращает количество выданных экземпляров
     */
    public int getBorrowedCopies() {
        return totalCopies.get() - availableCopies.get();
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%d)", title.get(), author.get(), year.get());
    }
    
    /**
     * Создает копию объекта Book
     */
    public Book copy() {
        return new Book(
            id.get(),
            title.get(),
            author.get(),
            year.get(),
            category.get(),
            price.get(),
            isbn.get(),
            totalCopies.get(),
            availableCopies.get()
        );
    }
}