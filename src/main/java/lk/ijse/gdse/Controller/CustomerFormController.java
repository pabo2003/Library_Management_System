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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

    public class CustomerFormController {

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
        private TableView<Members> tblMembers;

        @FXML
        private TextField txtAddress;

        @FXML
        private TextField txtDateOfBirth;

        @FXML
        private TextField txtEmail;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtJoinDate;

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
                txtJoinDate.setText(String.valueOf(members.getJoinDate()));
                txtType.setText(members.getMembershipType());
            }else {
                new Alert(Alert.AlertType.INFORMATION,"CustomerDTO is not found!");
            }
        }
        public void initialize(){
            setCellValueFactory();
            loadAllMembers();
            getCurrentMemberId();

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
                    txtJoinDate.requestFocus();
                }
            });
            txtJoinDate.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    txtType.requestFocus();
                }
            });

            tblMembers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    txtId.setText(String.valueOf(newSelection.getMemberId()));
                    txtName.setText(newSelection.getName());
                    txtEmail.setText(newSelection.getEmail());
                    txtTel.setText(newSelection.getPhoneNumber());
                    txtAddress.setText(newSelection.getAddress());
                    txtDateOfBirth.setText(String.valueOf(newSelection.getDateOfBirth()));
                    txtJoinDate.setText(String.valueOf(newSelection.getJoinDate()));
                    txtType.setText(newSelection.getMembershipType());
                }
            });
        }

        private void getCurrentMemberId() {
            try {
                int currentId = Integer.parseInt(memberBO.getCurrentId());
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
            ObservableList<Members> objList = FXCollections.observableArrayList();
            try {
                List<MembersDTO> memberList = memberBO.getAll();
                for (MembersDTO membersDTO : memberList) {
                    Members members = new Members(
                            membersDTO.getMember_id(),
                            membersDTO.getName(),
                            membersDTO.getEmail(),
                            membersDTO.getPhone_number(),
                            membersDTO.getAddress(),
                            membersDTO.getDate_of_birth(),
                            membersDTO.getJoin_date(),
                            membersDTO.getMembership_type()
                    );
                    objList.add(members);
                }
                tblMembers.setItems(objList);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        private void setCellValueFactory() {
            this.colId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            this.colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            this.colTel.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            this.colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            this.colDateofBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            this.colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
            this.colType.setCellValueFactory(new PropertyValueFactory<>("membershipType"));
        }
        @FXML
        void btnClearOnAction(ActionEvent event) {
            clearFields();
        }

        private void clearFields() {
            txtName.clear();
            txtEmail.clear();
            txtTel.clear();
            txtAddress.clear();
            txtDateOfBirth.clear();
            txtJoinDate.clear();
            txtTel.clear();
        }
        @FXML
        void btnDashBoardOnAction(ActionEvent event) {
            try {
                AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/resources/view/dashboard_form.fxml"));
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
        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            String email = txtEmail.getText();
            String phoneNumber = txtTel.getText();
            String address = txtAddress.getText();
            String dateOfBirthStr = txtDateOfBirth.getText();
            String joinDateStr = txtJoinDate.getText();
            String membershipType = txtType.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                // Parse dates
                Date dateOfBirth = new Date(dateFormat.parse(dateOfBirthStr).getTime());
                Date joinDate = new Date(dateFormat.parse(joinDateStr).getTime());

                // Create MembersDTO object with Date objects
                MembersDTO memberDTO = new MembersDTO(id, name, email, phoneNumber, address, (java.sql.Date) dateOfBirth, (java.sql.Date) joinDate, membershipType);

                // Perform save operation
                boolean isSaved = memberBO.save(memberDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Member saved successfully!").show();
                    clearFields(); // Clear input fields
                    loadAllMembers(); // Refresh member list
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save member.").show();
                }
            } catch (ParseException e) {
                new Alert(Alert.AlertType.ERROR, "Error parsing date. Please enter date in format yyyy-MM-dd.").show();
                e.printStackTrace(); // Log or handle the exception appropriately
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
                e.printStackTrace(); // Log or handle the exception appropriately
            }

            clearFields(); // Ensure fields are cleared after the operation
        }




        @FXML
        void btnUpdateOnAction(ActionEvent actionEvent) {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            String email = txtEmail.getText();
            String phoneNumber = txtTel.getText();
            String address = txtAddress.getText();
            String dateOfBirthStr = txtDateOfBirth.getText();
            String joinDateStr = txtJoinDate.getText();
            String membershipType = txtType.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date dateOfBirth = new Date(dateFormat.parse(dateOfBirthStr).getTime());
                Date joinDate = new Date(dateFormat.parse(joinDateStr).getTime());

                MembersDTO memberDTO = new MembersDTO(id, name, email, phoneNumber, address, (java.sql.Date) dateOfBirth, (java.sql.Date) joinDate, membershipType);

                boolean isSaved = memberBO.update(memberDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Member saved successfully!").show();
                    clearFields(); // Clear input fields
                    loadAllMembers(); // Refresh member list
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save member.").show();
                }
            } catch (ParseException e) {
                new Alert(Alert.AlertType.ERROR, "Error parsing date. Please enter date in format yyyy-MM-dd.").show();
                e.printStackTrace(); // Log or handle the exception appropriately
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
                e.printStackTrace(); // Log or handle the exception appropriately
            }

            clearFields(); // Ensure fields are cleared after the operation
        }

    }

