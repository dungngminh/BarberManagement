package view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView setImg;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAppointment;

    @FXML
    private Button btnCustomer;

    @FXML
    private ImageView userAvt1;

    @FXML
    private ImageView userAvt3;

    @FXML
    private ImageView userAvt2;

    @FXML
    private ImageView userAvt4;

    @FXML
    private ImageView userCus1;
    @FXML
    private ImageView userCus2;
    @FXML
    private ImageView userCus3;
    @FXML
    private ImageView userCus4;
    @FXML
    private ImageView iconApp01;
    @FXML
    private ImageView iconApp02;
    @FXML
    private ImageView iconApp03;
    @FXML
    private ImageView iconApp04;

    @FXML
    private Button btnEmployee;

    @FXML
    private Label userName;

    @FXML
    private Button btnProfit;

    @FXML
    private Button btnAbout;

    @FXML
    void showAppointment(ActionEvent event) throws IOException {
        FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Appointment.fxml"));
        Parent root1 = about.load();
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root1, 1600, 900));
        window.show();
    }


    @FXML
    void showCustomer(ActionEvent event) throws IOException {
        FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Customer.fxml"));
        Parent root1 = about.load();
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root1, 1600, 900));
        window.show();
    }

    @FXML
    void showStaff(ActionEvent event)  {
        Image img;
        img = new Image("/view/fxml/img/icon.png");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/Service.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.getIcons().add(img);
        stage.setTitle("Details View:");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void LogOut() throws IOException {
        CheckAdmin.isAdmin = false;
        FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Login.fxml"));
        Parent root1 = about.load();
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root1, 577, 548));
        window.show();
    }

    @FXML
    void CloseApp() {
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.close();

    }

    public void setText (String text) {
        String display = "Hi, " + text;
        userName.setText(display);
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImage();
    }

    public void setImage () {
        Image img = new Image("/view/fxml/img/user.png");
        Image cus1 = new Image("/view/fxml/img/cus01.png");
        Image cus2 = new Image("/view/fxml/img/cus02.png");
        Image app01 = new Image("/view/fxml/img/app01.png");
        Image app02 = new Image("/view/fxml/img/app02.png");
        Image img2 = new Image("/view/fxml/img/userStaff.png");
        setImg.setImage(img);
        iconApp01.setImage(app01);
        iconApp02.setImage(app02);
        iconApp03.setImage(app01);
        iconApp04.setImage(app02);
        userCus1.setImage(img2);
        userCus2.setImage(img2);
        userCus3.setImage(img2);
        userCus4.setImage(img2);
        userAvt1.setImage(cus1);
        userAvt2.setImage(cus2);
        userAvt3.setImage(cus1);
        userAvt4.setImage(cus2);
    }

}
