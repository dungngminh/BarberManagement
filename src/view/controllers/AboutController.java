package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutController {

    @FXML
    private ImageView setIconImg;

    @FXML
    private ImageView setGitImage;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAppointment;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnProfit;

    @FXML
    private Button btnAbout;

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnAppointment) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Appointment.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnCustomer) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Customer.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnEmployee) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Employee.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)btnEmployee.getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();

        }
        if (event.getSource() == btnProfit) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Profit.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnAbout) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/About.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnHome) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage) btnHome.getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
    }



    private void setImage() {
        Image img1 = new Image("/view/fxml/img/github.png");
        Image img2 = new Image("/view/fxml/img/setImg.png");
        setIconImg.setImage(img2);
        setGitImage.setImage(img1);
    }

    @FXML
    void initialize() {
        setImage();
    }
}