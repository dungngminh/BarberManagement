package view.controllers;

import home.DAO.UsernameDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {


    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord;

    @FXML
    private Button loginBtn;



    @FXML
    void createAccountAction(ActionEvent event) throws IOException {
        FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Register.fxml"));
        Parent root1 = about.load();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root1, 577, 548));
        window.show();
    }

    private final static UsernameDAO userDAO = new UsernameDAO();

    @FXML
    void loginAction(ActionEvent event) throws SQLException, IOException {
        String user = userName.getText();
        String password = passWord.getText();
        boolean checkLogin = userDAO.checkLogin(user, password);
        if (checkLogin) {
            boolean isAdmin = userDAO.isAdmin(user, password);
            if (isAdmin) {
                CheckAdmin.isAdmin = true;
                FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
                Parent root1 = about.load();
                Controller controller = about.getController();
                controller.setText(user);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root1, 1600, 900));
                window.show();
            }
            else {
                FXMLLoader about1 = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
                Parent root1 = about1.load();
                Controller controller = about1.getController();
                controller.setText(user);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root1, 1600, 900));
                window.show();
            }
        }
        else {
            Dialog dialog = new Dialog();
            dialog.setContentText("Invalid Username or Password!");
            dialog.setHeaderText("Warning");
            dialog.initOwner(loginBtn.getScene().getWindow());
            ButtonType cancelBtn = new ButtonType("Yes", ButtonBar.ButtonData.CANCEL_CLOSE);
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(cancelBtn);
            dialog.show();
        }
        // So we definetely need to check in database to see what's going on.
        // Select user -> Check pass -> Chuyen qua trang main.
        // Neu la tai khoan admin thi chuyen qua trang khac
    }

    void disableButton() {

    }

}
