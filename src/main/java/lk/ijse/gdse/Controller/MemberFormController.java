package lk.ijse.gdse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.BO.BOFactory;
import lk.ijse.gdse.BO.MemberBO;
import lk.ijse.gdse.DTO.MembersDTO;
import lk.ijse.gdse.Entity.Members;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.Label;

public class MemberFormController {

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
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDateofBirth;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colJoinDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<MembersDTO> tblMembers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDateOfBirth;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private Label lblJoinDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtType;
    MemberBO memberBO  = (MemberBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.MEMBER);

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, SQLException {
        String tel = txtId.getText();

        Members members = memberBO.searchByTel(tel);

        if (members != null) {
            txtId.setText(String.valueOf(members.getMemberId()));
            txtName.setText(members.getName());
            txtEmail.setText(members.getEmail());
            txtTel.setText(members.getPhoneNumber());
            txtAddress.setText(members.getAddress());
            txtDateOfBirth.setText(String.valueOf(members.getDateOfBirth()));
            lblJoinDate.setText(String.valueOf(members.getJoinDate()));
            txtType.setText(members.getMembershipType());
        }else {
            new Alert(Alert.AlertType.INFORMATION,"memberDTO is not found!");
        }
    }
    public void initialize(){
        setCellValueFactory();
        loadAllMembers();
        getCurrentMemberId();
        setDate();

        txtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmail.requestFocus();
            }
        });

        txtEmail.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtTel.requestFocus();
            }
        });
        txtTel.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });
        txtAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDateOfBirth.requestFocus();
            }
        });
        txtDateOfBirth.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                lblJoinDate.requestFocus();
            }
        });
        txtType.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtType.requestFocus();
            }
        });

        tblMembers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(String.valueOf(newSelection.getMember_id()));
                txtName.setText(newSelection.getName());
                txtEmail.setText(newSelection.getEmail());
                txtTel.setText(newSelection.getPhone_number());
                txtAddress.setText(newSelection.getAddress());
                txtDateOfBirth.setText(String.valueOf(newSelection.getDate_of_birth()));
                lblJoinDate.setText(String.valueOf(newSelection.getJoin_date()));
                txtType.setText(newSelection.getMembership_type());
            }
        });
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblJoinDate.setText(String.valueOf(now));
    }
    private void getCurrentMemberId() {
        try {
            int currentId =(memberBO.getCurrentId());
            int nextMemberId = generateNextOrderId(currentId);
            System.out.println(nextMemberId);
            txtId.setText(String.valueOf(nextMemberId));

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

    private void loadAllMembers() {
        ObservableList<MembersDTO> objList = FXCollections.observableArrayList();
        try {
            List<MembersDTO> memberList = memberBO.getAll();
            for (MembersDTO membersDTO : memberList) {
                MembersDTO memberDTO = new MembersDTO(
                        membersDTO.getMember_id(),
                        membersDTO.getName(),
                        membersDTO.getEmail(),
                        membersDTO.getPhone_number(),
                        membersDTO.getAddress(),
                        membersDTO.getDate_of_birth(),
                        membersDTO.getJoin_date(),
                        membersDTO.getMembership_type()
                );
                objList.add(memberDTO);
            }
            tblMembers.setItems(objList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colTel.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        this.colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.colDateofBirth.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        this.colJoinDate.setCellValueFactory(new PropertyValueFactory<>("join_date"));
        this.colType.setCellValueFactory(new PropertyValueFactory<>("membership_type"));
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        loadAllMembers();
        getCurrentMemberId();
        txtType.clear();
        txtName.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtDateOfBirth.clear();
        txtTel.clear();
    }
    @FXML
    void btnDashBoardOnAction(ActionEvent event) {
        try {
            AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/View/DashBoard_form.fxml"));
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
            boolean isDeleted = memberBO.delete(Integer.parseInt(id));
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Member Deleted!");
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadAllMembers();
        clearFields();
        getCurrentMemberId();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtTel.getText();
        String address = txtAddress.getText();
        Date dateOfBirthStr = Date.valueOf(txtDateOfBirth.getText());
        Date joinDateStr = Date.valueOf(lblJoinDate.getText());
        String membershipType = txtType.getText();

        try {

            MembersDTO memberDTO = new MembersDTO(id, name, email, phoneNumber, address,dateOfBirthStr, joinDateStr, membershipType);


            boolean isSaved = memberBO.save(memberDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Member saved successfully!").show();

                loadAllMembers();
                getCurrentMemberId();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save member.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            e.printStackTrace();
        }

        // clearFields(); // Ensure fields are cleared after the operation
    }




    @FXML
    void btnUpdateOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtTel.getText();
        String address = txtAddress.getText();
        Date dateOfBirthStr = Date.valueOf(txtDateOfBirth.getText()); // Assuming txtDateOfBirth returns a string in the correct format
        Date joinDateStr = Date.valueOf(lblJoinDate.getText());
        String membershipType = txtType.getText();



        try {


            MembersDTO memberDTO = new MembersDTO(id, name, email, phoneNumber, address,dateOfBirthStr,  joinDateStr, membershipType);

            boolean isUpdate = memberBO.update(memberDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Member update successfully!").show();
                clearFields(); // Clear input fields
                loadAllMembers(); // Refresh member list
                getCurrentMemberId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update member.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            e.printStackTrace(); // Log or handle the exception appropriately
        }

        // clearFields(); // Ensure fields are cleared after the operation
    }

}