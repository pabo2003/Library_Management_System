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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.BO.BarrowTransBO;
import lk.ijse.gdse.BO.BooksBO;
import lk.ijse.gdse.BO.Impl.BarrowTransBOImpl;
import lk.ijse.gdse.BO.Impl.BooksBOImpl;
import lk.ijse.gdse.BO.Impl.MemberBOImpl;
import lk.ijse.gdse.BO.MemberBO;
import lk.ijse.gdse.BO.PlaceBook;
import lk.ijse.gdse.DTO.BorrowingTransactionsDTO;
import lk.ijse.gdse.Entity.Books;
import lk.ijse.gdse.Entity.Members;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BorrowingTransactionsFormController {

    public TableColumn colDueDate;
    public JFXButton btnAddBook;
    @FXML
    private JFXButton btnAddCustomer;


    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colBorrowId;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colMemberName;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private ComboBox<String> comBookId;

    @FXML
    private ComboBox<String> comMemberTel;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBorrowDate;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblMemberId;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblPayId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblBorrowDetails;

    BarrowTransBO borrowTransBO = new BarrowTransBOImpl();
    MemberBO memberBO = new MemberBOImpl();
    BooksBO booksBO = new BooksBOImpl();



    public void initialize() {
        { LocalDate now = LocalDate.now();  // this is Local date
            lblBorrowDate.setText(String.valueOf(now));}

        {LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusDays(30);  // this is local date  + 30 next days
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDueDate = dueDate.format(formatter);
            String DueDate = formattedDueDate;
            lblDueDate.setText(DueDate);}

        setCellValueFactory();
        loadAll();
        getCurrentId();
        getIds();
        getIds2();



    }

    private void getCurrentId() {
        try {
            int currentId =(borrowTransBO.getCurrentId());
            int nextId = generateNextOrderId(currentId);
            System.out.println(nextId);
            lblPayId.setText(String.valueOf(nextId));

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

    private void loadAll() {
    }

    private void setCellValueFactory() {
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

    }



    @FXML
    void btnAddToCartOnAction(ActionEvent event) throws SQLException {
        int BookID       = Integer.parseInt(comBookId.getValue());
        //  String BookName     = String.valueOf(Integer.parseInt(lblBookName.getText()));
        int MemberID     = Integer.parseInt(lblMemberId.getText());
        // String MemberName   = lblMemberName.getText();
        int BorrowID     = Integer.parseInt(lblPayId.getText());
        Date BorrowDate   = Date.valueOf(lblBorrowDate.getText());
        Date DueDate      = Date.valueOf(lblDueDate.getText());

        BorrowingTransactionsDTO borrowingTransactionsDTO = new BorrowingTransactionsDTO(BorrowID,BookID, MemberID, BorrowDate, DueDate);

        boolean isAdd = PlaceBook.placeBookBorrow(borrowingTransactionsDTO);

        if (isAdd){
      /*     lblMemberId.setText("");
           lblMemberName.setText("");
           lblPayId.setText("");
           lblBookName.setText("");
           comMemberTel.getSelectionModel().clearSelection();
           comBookId.getSelectionModel().clearSelection();*/
            loadAll();
            getCurrentId();
            clearField();

            new Alert(Alert.AlertType.CONFIRMATION, " Placed!").show();

        } else {
            new Alert(Alert.AlertType.WARNING, " Placed Unsuccessfully!").show();
        }


    }

    private void clearField(){
        lblMemberId.setText("");
        lblMemberName.setText("");
        lblPayId.setText("");
        lblBookName.setText("");
        comMemberTel.getSelectionModel().clearSelection();
        comBookId.getSelectionModel().clearSelection();
        loadAll();
        getCurrentId();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();
    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashBoard_form.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void comBookIdOnAction(ActionEvent event) {
        String IdValue = comBookId.getValue();

        try{
            Books book = booksBO.searchById(IdValue);
            if (book != null){
                lblBookName.setText(book.getTitle());
            }else{
                lblBookName.setText("Book Not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void comMemberTelOnAction(ActionEvent event) {
        String categoryIdValue = comMemberTel.getValue();

        try{
            Members members = memberBO.searchByTel(categoryIdValue);
            if (members != null){
                lblMemberName.setText(members.getName());
                lblMemberId.setText(String.valueOf(members.getMemberId()));
            }else{
                lblMemberName.setText("Member Not found");
                lblMemberId.setText("Member not found'");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = booksBO.getId();
            obList.addAll(idList);
            comBookId.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching stock IDs: " + e.getMessage());
        }
    }
    private void getIds2() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = memberBO.getTel();
            obList.addAll(idList);
            comMemberTel.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching stock IDs: " + e.getMessage());
        }
    }
    private void showAlert(Alert.AlertType alertType, String s) {
    }

    public void btnAddBookOnAction(ActionEvent actionEvent) {
    }
}