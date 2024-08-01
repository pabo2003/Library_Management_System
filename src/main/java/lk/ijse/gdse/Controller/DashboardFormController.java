package lk.ijse.gdse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class DashboardFormController {

    public JFXButton btnReturnDetails;
    @FXML
    private AnchorPane SpecialPane;

    @FXML
    private JFXButton btnBookCategories;

    @FXML
    private JFXButton btnBooks;

    @FXML
    private JFXButton btnBorrowing;

    @FXML
    private JFXButton btnMembers;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private void initialize() {
        addButtonHoverEffect(btnMembers);
        addButtonHoverEffect(btnBooks);
        addButtonHoverEffect(btnBookCategories);
        addButtonHoverEffect(btnBorrowing);
    }

    @FXML
    private void addButtonHoverEffect(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), button);
        button.setOnMouseEntered((MouseEvent event) -> {
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });
        button.setOnMouseExited((MouseEvent event) -> {
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
    }

    @FXML
    void btnBookCategoriesOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BookCategory_form.fxml"));
            Parent root = loader.load();
            SpecialPane.getChildren().clear();
            SpecialPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnBooksOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/books_form.fxml"));
            Parent root = loader.load();
            SpecialPane.getChildren().clear();
            SpecialPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnBorrowingOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BorrowingTranctions_form.fxml"));
            Parent root = loader.load();
            SpecialPane.getChildren().clear();
            SpecialPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/member_form.fxml"));
            Parent root = loader.load();
            SpecialPane.getChildren().clear();
            SpecialPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnReturnDetailsOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ReturnDetails_form.fxml"));
            Parent root = loader.load();
            SpecialPane.getChildren().clear();
            SpecialPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}