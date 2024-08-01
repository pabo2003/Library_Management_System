package lk.ijse.gdse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.BO.BookCategoriesBO;
import lk.ijse.gdse.BO.Impl.BookCategoryBOImpl;
import lk.ijse.gdse.DTO.BookCategoriesDTO;
import lk.ijse.gdse.DTO.MembersDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookCategoryController {

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
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookCategoriesDTO> tblBookCategory;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtId;

    BookCategoriesBO bookCategoriesBO = new BookCategoryBOImpl();


    public void initialize(){
        setCellValueFactory();
        LoadAll();
        getCurrentId();

        tblBookCategory.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(String.valueOf(newSelection.getCategory_id()));
                txtCategory.setText(newSelection.getCategory_name());

            }
        });
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();
        LoadAll();
        getCurrentId();

    }
    private int generateNextrId(int currentId) {
        if (currentId != 0) {
            int idNum = (currentId) + 1;
            return idNum;
        }
        return 001;
    }
    private void setCellValueFactory() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("category_name"));

    }



    private void clearField() {
        LoadAll();
        getCurrentId();
        //txtId.clear();
        txtCategory.clear();
    }

    private void LoadAll() {
        ObservableList<BookCategoriesDTO> objList = FXCollections.observableArrayList();
        try{
            List<BookCategoriesDTO> Blist = bookCategoriesBO.getAllBookCategories();
            for (BookCategoriesDTO B : Blist) {
                BookCategoriesDTO bookCategoriesDTO = new BookCategoriesDTO(
                        B.getCategory_id(),
                        B.getCategory_name());
                objList.add(bookCategoriesDTO);
            }
            tblBookCategory.setItems(objList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void getCurrentId() {
        try {
            int currentId =(bookCategoriesBO.getCurrentId());
            int nextId = generateNextrId(currentId);
            System.out.println(nextId);
            txtId.setText(String.valueOf(nextId));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_form.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = bookCategoriesBO.deleteBookCategories(Integer.parseInt(id));
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION," Deleted!");
                clearField();

            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int BookCategoryId = Integer.parseInt(txtId.getText());
        String BookCategoryName = txtCategory.getText();

        BookCategoriesDTO BookCategory = new BookCategoriesDTO(BookCategoryId, BookCategoryName);
        try {
            boolean isSave = bookCategoriesBO.saveBookCategories(BookCategory);
            System.out.println(BookCategoryId);
            System.out.println(BookCategoryName);


            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "BookCategory saved!").show();
                clearField();

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save BookCategory.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            e.printStackTrace();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        int BookCategoryId = Integer.parseInt(txtId.getText());
        String BookCategoryName = txtCategory.getText();

        BookCategoriesDTO BookCategory = new BookCategoriesDTO(BookCategoryId, BookCategoryName);
        boolean isSave = bookCategoriesBO.updateBookCategories(BookCategory);
        if(isSave){
            new Alert(Alert.AlertType.CONFIRMATION, "BookCategory Update !").show();
            clearField();


        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update BookCategory.").show();
        }
    }

    @FXML
    void txtStockIDOnKeyReleased(KeyEvent event) {

    }

}