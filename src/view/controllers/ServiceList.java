package view.controllers;

import home.DAO.EmployeeDAO;
import home.DTO.Service;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class ServiceList {

    @FXML
    private TableView<Service> listService;

    @FXML
    private TableColumn<Service, String> serviceID;

    @FXML
    private TableColumn<Service, String> svName;

    @FXML
    private TableColumn<Service, String> svPrice;

    @FXML
    private Button addService;

    @FXML
    void addService() throws SQLException {
        showDialog();
    }

    @FXML
    void closeBtn() {
        Stage window = (Stage) addService.getScene().getWindow();
        window.close();
    }

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    @FXML
    void initialize() throws SQLException {
        serviceID.setCellValueFactory(new PropertyValueFactory("maDV"));
        svName.setCellValueFactory(new PropertyValueFactory<>("tenDV"));
        svPrice.setCellValueFactory(new PropertyValueFactory<>("giaTien"));
        listService.setItems(employeeDAO.getService());
    }

    private void showDialog() throws SQLException {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 20, 10, 10));
        Label label1 = new Label("Service Name: ");
        Label label2 = new Label("Price: ");
        TextField textField2 = new TextField();
        TextField textField4 = new TextField();
        vbox.getChildren().addAll(label1, textField2, label2, textField4);
        Dialog dialog = new Dialog();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(vbox);
        dialogPane.setPrefSize(600, 600);
        dialog.setTitle("Add new Service");
        ButtonType okBtn = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogPane.getButtonTypes().addAll(okBtn, cancelBtn);
        dialog.initOwner(addService.getScene().getWindow());
        Optional optionalResult = dialog.showAndWait();
        if (optionalResult.get() == okBtn) {
            String svName = textField2.getText();
            String price = textField4.getText();
            employeeDAO.addService(svName, price);
            listService.setItems(employeeDAO.getService());
        }
    }

}
