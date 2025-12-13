package com.example;

import com.example.Book;
import com.example.XMLValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Optional;

public class LibraryController {
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private TableView<Book> tableView;
    
    public LibraryController() {
        createTableView();
    }
    
    private void createTableView() {
        tableView = new TableView<>();
        
        // Колонки таблицы
        TableColumn<Book, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<Book, String> titleCol = new TableColumn<>("Название");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        TableColumn<Book, String> authorCol = new TableColumn<>("Автор");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        TableColumn<Book, Integer> yearCol = new TableColumn<>("Год");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        TableColumn<Book, String> categoryCol = new TableColumn<>("Категория");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        TableColumn<Book, Double> priceCol = new TableColumn<>("Цена");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        
        TableColumn<Book, Integer> totalCol = new TableColumn<>("Всего");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        
        TableColumn<Book, Integer> availableCol = new TableColumn<>("Доступно");
        availableCol.setCellValueFactory(new PropertyValueFactory<>("availableCopies"));
        
        tableView.getColumns().addAll(idCol, titleCol, authorCol, yearCol, 
                                     categoryCol, priceCol, isbnCol, totalCol, availableCol);
        tableView.setItems(books);
    }
    
    public void loadLibraryFromXML() {
        try {
            // Проверка XML по схеме
            if (!XMLValidator.validate("src/main/resources/library.xml", 
                                      "src/main/resources/library.xsd")) {
                showAlert("Ошибка", "XML не соответствует схеме!");
                return;
            }
            
            books.clear();
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("src/main/resources/library.xml"));
            
            NodeList bookNodes = doc.getElementsByTagName("book");
            
            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElement = (Element) bookNodes.item(i);
                
                int id = Integer.parseInt(bookElement.getAttribute("id"));
                int totalCopies = Integer.parseInt(bookElement.getAttribute("totalCopies"));
                int availableCopies = Integer.parseInt(bookElement.getAttribute("availableCopies"));
                
                String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                int year = Integer.parseInt(bookElement.getElementsByTagName("year").item(0).getTextContent());
                String category = bookElement.getElementsByTagName("category").item(0).getTextContent();
                double price = Double.parseDouble(bookElement.getElementsByTagName("price").item(0).getTextContent());
                String isbn = bookElement.getElementsByTagName("isbn").item(0).getTextContent();
                
                books.add(new Book(id, title, author, year, category, price, isbn, totalCopies, availableCopies));
            }
            
            showAlert("Успех", "Загружено " + books.size() + " книг");
            
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить XML: " + e.getMessage());
        }
    }
    
    public void saveLibraryToXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            // Создание корневого элемента
            Element library = doc.createElement("library");
            doc.appendChild(library);
            
            // Добавление книг
            for (Book book : books) {
                Element bookElement = doc.createElement("book");
                bookElement.setAttribute("id", String.valueOf(book.getId()));
                bookElement.setAttribute("totalCopies", String.valueOf(book.getTotalCopies()));
                bookElement.setAttribute("availableCopies", String.valueOf(book.getAvailableCopies()));
                
                addElement(doc, bookElement, "title", book.getTitle());
                addElement(doc, bookElement, "author", book.getAuthor());
                addElement(doc, bookElement, "year", String.valueOf(book.getYear()));
                addElement(doc, bookElement, "category", book.getCategory());
                addElement(doc, bookElement, "price", String.valueOf(book.getPrice()));
                addElement(doc, bookElement, "isbn", book.getIsbn());
                
                library.appendChild(bookElement);
            }
            
            // Сохранение в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/library.xml"));
            transformer.transform(source, result);
            
            showAlert("Успех", "Сохранено " + books.size() + " книг");
            
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось сохранить XML: " + e.getMessage());
        }
    }
    
    private void addElement(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }
    
    public void showAddBookDialog() {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Добавить книгу");
        dialog.setHeaderText("Введите данные о книге");
        
        // Кнопки
        ButtonType addButton = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
        
        // Поля формы
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField yearField = new TextField();
        TextField categoryField = new TextField();
        TextField priceField = new TextField();
        TextField isbnField = new TextField();
        TextField totalField = new TextField();
        TextField availableField = new TextField();
        
        grid.add(new Label("Название:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Автор:"), 0, 1);
        grid.add(authorField, 1, 1);
        grid.add(new Label("Год:"), 0, 2);
        grid.add(yearField, 1, 2);
        grid.add(new Label("Категория:"), 0, 3);
        grid.add(categoryField, 1, 3);
        grid.add(new Label("Цена:"), 0, 4);
        grid.add(priceField, 1, 4);
        grid.add(new Label("ISBN:"), 0, 5);
        grid.add(isbnField, 1, 5);
        grid.add(new Label("Всего экз.:"), 0, 6);
        grid.add(totalField, 1, 6);
        grid.add(new Label("Доступно:"), 0, 7);
        grid.add(availableField, 1, 7);
        
        dialog.getDialogPane().setContent(grid);
        
        // Преобразование результата
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    int newId = books.size() > 0 ? books.get(books.size() - 1).getId() + 1 : 1;
                    return new Book(
                        newId,
                        titleField.getText(),
                        authorField.getText(),
                        Integer.parseInt(yearField.getText()),
                        categoryField.getText(),
                        Double.parseDouble(priceField.getText()),
                        isbnField.getText(),
                        Integer.parseInt(totalField.getText()),
                        Integer.parseInt(availableField.getText())
                    );
                } catch (NumberFormatException e) {
                    showAlert("Ошибка", "Некорректные числовые значения");
                    return null;
                }
            }
            return null;
        });
        
        Optional<Book> result = dialog.showAndWait();
        result.ifPresent(book -> {
            books.add(book);
            tableView.refresh();
        });
    }
    
    public void showSearchByAuthorDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Поиск по автору");
        dialog.setHeaderText("Введите имя автора");
        dialog.setContentText("Автор:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(author -> {
            ObservableList<Book> filteredBooks = books.filtered(book -> 
                book.getAuthor().toLowerCase().contains(author.toLowerCase()));
            
            TableView<Book> searchTable = new TableView<>();
            // Копируем колонки из основной таблицы
            searchTable.getColumns().addAll(tableView.getColumns());
            searchTable.setItems(filteredBooks);
            
            Dialog<Void> resultDialog = new Dialog<>();
            resultDialog.setTitle("Результаты поиска");
            resultDialog.getDialogPane().setContent(searchTable);
            resultDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            resultDialog.show();
        });
    }
    
    public void showUpdatePriceDialog() {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Изменить цену");
        
        // Выбор книги
        ComboBox<Book> bookCombo = new ComboBox<>(books);
        bookCombo.setCellFactory(new Callback<ListView<Book>, ListCell<Book>>() {
            @Override
            public ListCell<Book> call(ListView<Book> param) {
                return new ListCell<Book>() {
                    @Override
                    protected void updateItem(Book item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getTitle() + " (" + item.getAuthor() + ")");
                        }
                    }
                };
            }
        });
        
        TextField priceField = new TextField();
        
        GridPane grid = new GridPane();
        grid.add(new Label("Книга:"), 0, 0);
        grid.add(bookCombo, 1, 0);
        grid.add(new Label("Новая цена:"), 0, 1);
        grid.add(priceField, 1, 1);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                Book selected = bookCombo.getValue();
                if (selected != null) {
                    try {
                        selected.setPrice(Double.parseDouble(priceField.getText()));
                        tableView.refresh();
                    } catch (NumberFormatException e) {
                        showAlert("Ошибка", "Некорректная цена");
                    }
                }
            }
            return null;
        });
        
        dialog.showAndWait();
    }
    
    public void showBorrowBookDialog() {
        // Аналогично updatePriceDialog, но уменьшаем availableCopies
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Выдать книгу");
        
        // Фильтр книг, которые есть в наличии
        ObservableList<Book> availableBooks = books.filtered(book -> book.getAvailableCopies() > 0);
        
        ComboBox<Book> bookCombo = new ComboBox<>(availableBooks);
        // ... аналогично предыдущему
        
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                Book selected = bookCombo.getValue();
                if (selected != null && selected.getAvailableCopies() > 0) {
                    selected.setAvailableCopies(selected.getAvailableCopies() - 1);
                    tableView.refresh();
                    showAlert("Успех", "Книга '" + selected.getTitle() + "' выдана");
                }
            }
            return null;
        });
        
        dialog.showAndWait();
    }
    
    public void showSearchByYearDialog() {
        // Аналогично поиску по автору
    }
    
    public void showSearchByCategoryDialog() {
        // Аналогично поиску по автору
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public TableView<Book> getTableView() {
        return tableView;
    }
}