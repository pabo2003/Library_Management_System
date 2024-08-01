package lk.ijse.gdse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.BO.BOFactory;
import lk.ijse.gdse.BO.BookCategoriesBO;
import lk.ijse.gdse.BO.BooksBO;
import lk.ijse.gdse.DTO.BooksDTO;
import lk.ijse.gdse.DTO.MembersDTO;
import lk.ijse.gdse.Entity.BookCategories;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class booksFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAvailable;

    @FXML
    private TableColumn<?, ?> colCategoryId;

    @FXML
    private TableColumn<?, ?> colEdition;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLanguage;

    @FXML
    private TableColumn<?, ?> colShelfLocation;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private ComboBox<String> comCategoryId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BooksDTO> tblBooks;

    @FXML
    private TextField txtAvailableCopies;

    @FXML
    private TextField txtEdition;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLanguage;

    @FXML
    private TextField txtShelfLocation;

    @FXML
    private TextField txtTitel;
    BooksBO booksBO  = (BooksBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.BOOKS);
    BookCategoriesBO bookCategoriesBO  = (BookCategoriesBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.BOOK_CATEGORIES);

    public void initialize(){
        setCellValueFactory();
        loadAllBooks();
        getCurrentBookId();
        getCategoryIds();

        txtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtId.requestFocus();
            }
        });

        txtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtTitel.requestFocus();
            }
        });

        txtTitel.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEdition.requestFocus();
            }
        });
        txtEdition.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLanguage.requestFocus();
            }
        });
        txtLanguage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAvailableCopies.requestFocus();
            }
        });
        txtAvailableCopies.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtShelfLocation.requestFocus();
            }
        });
        txtShelfLocation.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                comCategoryId.requestFocus();
            }
        });

        tblBooks.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(String.valueOf(newSelection.getBook_id()));
                txtTitel.setText(newSelection.getTitle());
                txtEdition.setText(newSelection.getEdition());
                txtLanguage.setText(newSelection.getLanguage());
                txtAvailableCopies.setText(String.valueOf(newSelection.getAvailable_copies()));
                txtShelfLocation.setText(String.valueOf(newSelection.getShelf_location()));
                comCategoryId.setValue(String.valueOf(newSelection.getCategory_id()));
            }
        });
    }

    private void getCurrentBookId() {
        try {
            int currentId =(booksBO.getCurrentId());
            int nextBookId = generateNextOrderId(currentId);
            System.out.println(nextBookId);
            txtId.setText(String.valueOf(nextBookId));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private int generateNextOrderId(int currentId) {
        if (currentId != 0) {
            int idNum = (currentId) + 1;
            return idNum;
        }
        return 001;
    }

    private void loadAllBooks() {
        ObservableList<BooksDTO> objList = FXCollections.observableArrayList();
        try {
            List<BooksDTO> bookList = booksBO.getAllBooks();
            for (BooksDTO booksDTO : bookList) {
                BooksDTO bookDTO = new BooksDTO(
                        booksDTO.getBook_id(),
                        booksDTO.getTitle(),
                        booksDTO.getEdition(),
                        booksDTO.getLanguage(),
                        booksDTO.getAvailable_copies(),
                        booksDTO.getShelf_location(),
                        booksDTO.getCategory_id()
                );
                objList.add(bookDTO);
            }
            tblBooks.setItems(objList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        this.colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.colEdition.setCellValueFactory(new PropertyValueFactory<>("edition"));
        this.colLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        this.colAvailable.setCellValueFactory(new PropertyValueFactory<>("available_copies"));
        this.colShelfLocation.setCellValueFactory(new PropertyValueFactory<>("shelf_location"));
        this.colCategoryId.setCellValueFactory(new PropertyValueFactory<>("category_id"));
    }

    private void clearFields() {
        txtTitel.clear();
        txtEdition.clear();
        txtLanguage.clear();
        txtAvailableCopies.clear();
        txtShelfLocation.clear();
        comCategoryId.getSelectionModel().clearSelection();
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/resources/View/DashBoard_form.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(rootNode));
            stage.setTitle("Dashboard Form");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = booksBO.deleteBooks(Integer.parseInt(id));
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Book Deleted!");
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadAllBooks();
        clearFields();
        getCurrentBookId();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String title = txtTitel.getText();
        String edition = txtEdition.getText();
        String language = txtLanguage.getText();
        int available_copies = Integer.parseInt(txtAvailableCopies.getText());
        String shelf_location = txtShelfLocation.getText();
        int category_id = Integer.parseInt(comCategoryId.getValue());


        try {
            BooksDTO booksDTO = new BooksDTO(id, title, edition, language, available_copies,shelf_location, category_id);

            boolean isSaved = booksBO.saveBooks(booksDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book saved successfully!").show();

                loadAllBooks();
                getCurrentBookId();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Book.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            e.printStackTrace();
        }

        clearFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String title = txtTitel.getText();
        String edition = txtEdition.getText();
        String language = txtLanguage.getText();
        int available_copies = Integer.parseInt(txtAvailableCopies.getText());
        String shelf_location = txtShelfLocation.getText();
        int category_id = Integer.parseInt(comCategoryId.getValue());



        try {
            BooksDTO booksDTO = new BooksDTO(id, title, edition, language, available_copies,shelf_location, category_id);

            boolean isUpdate = booksBO.updateBooks(booksDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book update successfully!").show();
                clearFields();
                loadAllBooks();
                getCurrentBookId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Book.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            e.printStackTrace();
        }

       clearFields();
    }

    @FXML
    void comCategoryIdOnAction(ActionEvent event) {
        int id = Integer.parseInt(comCategoryId.getValue());
        try {
            BookCategories bookCategories = bookCategoriesBO.searchById(String.valueOf(id));
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while searching for stock: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String s) {
    }

    private void getCategoryIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = bookCategoriesBO.getId();
            obList.addAll(idList);
            comCategoryId.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching stock IDs: " + e.getMessage());
        }
    }
}
