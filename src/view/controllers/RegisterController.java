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

public class RegisterController {


    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField cPassword;

    @FXML
    private Button loginBtn;


    @FXML
    void backToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Login.fxml"));
        Parent root1 = about.load();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root1, 577, 548));
        window.show();
    }

    private static final UsernameDAO userDAO = new UsernameDAO();

    @FXML
    void signUpAction() throws SQLException {
        // Create a new account in here.
        String username = userName.getText();
        String pass = password.getText();
        String cpass = cPassword.getText();
        if (pass.equals(cpass)) {
            int i = userDAO.addAcount(username, pass);
            if (i!=0) {
                Dialog dialog = new Dialog();
                DialogPane dialogPane = dialog.getDialogPane();
                ButtonType cancelBtn = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Sucessful!");
                dialogPane.getButtonTypes().addAll(cancelBtn);
                dialog.initOwner(loginBtn.getScene().getWindow());
                dialog.show();
            }
            else {
                Dialog dialog = new Dialog();
                DialogPane dialogPane = dialog.getDialogPane();
                ButtonType cancelBtn = new ButtonType("Yes", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.setContentText("A problem has occurred!");
                dialogPane.getButtonTypes().addAll(cancelBtn);
                dialog.initOwner(loginBtn.getScene().getWindow());
                dialog.show();
            }
        }
        else {
            Dialog dialog = new Dialog();
            DialogPane dialogPane = dialog.getDialogPane();
            ButtonType cancelBtn = new ButtonType("Yes", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.setContentText("Password in two fields is not match!");
            dialogPane.getButtonTypes().addAll(cancelBtn);
            dialog.initOwner(loginBtn.getScene().getWindow());
            dialog.show();
        }
        
    }

}
