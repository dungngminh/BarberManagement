package view.controllers;

import home.DAO.CustomerDAO;
import home.DTO.Customer;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAppointment;


    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnProfit;

    @FXML
    private Button btnAbout;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, String> cusName;

    @FXML
    private TableColumn<Customer, String> cusPhone;

    @FXML
    private TableColumn<Customer, String> cusID;

    @FXML
    void addCus (ActionEvent event) throws SQLException {
        showDialog();
    }

    private void showDialog() throws SQLException {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 20, 10, 10));
        TextField textField1 = new TextField();
        Label label1 = new Label("Name: ");
        Label label2 = new Label("Phone: ");
        TextField textField2 = new TextField();
        vbox.getChildren().addAll(label1, textField1, label2, textField2);
        Dialog dialog = new Dialog();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(vbox);
        dialogPane.setPrefSize(600, 600);
        dialog.setTitle("Add new Customer");
        ButtonType okBtn = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogPane.getButtonTypes().addAll(okBtn, cancelBtn);
        dialog.initOwner(btnAbout.getScene().getWindow());
        Optional optionalResult = dialog.showAndWait();

        if (optionalResult.get() == okBtn) {
            String firstName = textField1.getText();
            String phone = textField2.getText();
            customerDAO.addCustomer(firstName, phone);
            tableView.setItems(customerDAO.getCustomer());
        }
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

    private static final CustomerDAO customerDAO = new CustomerDAO();

    public void changeName (TableColumn.CellEditEvent editEvent) throws SQLException {
            Customer customerSelected = tableView.getSelectionModel().getSelectedItem();
            customerSelected.setName(editEvent.getNewValue().toString());
            customerDAO.changeInfo(customerSelected.getId(), editEvent.getNewValue().toString(), "name");
            System.out.println(customerSelected.getId());
    }

    public void changePhone (TableColumn.CellEditEvent editEvent) throws SQLException {
        Customer customerSelected = tableView.getSelectionModel().getSelectedItem();
        customerSelected.setName(editEvent.getNewValue().toString());
        customerDAO.changeInfo(customerSelected.getId(), editEvent.getNewValue().toString(), "phone");
        System.out.println(customerSelected.getId());
    }



    @FXML
    void initialize() throws SQLException {

        tableView.setEditable(true);
        cusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cusPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cusID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cusName.setCellFactory(TextFieldTableCell.forTableColumn());
        cusPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.setItems(customerDAO.getCustomer());


        FilteredList<Customer> filteredList = new FilteredList<>(CustomerDAO.getCustomer(), b->true);
        searchField.textProperty().addListener((observable, oldValue, newValue)-> filteredList.setPredicate(employee -> {
            if (newValue == null || newValue.isEmpty()) return true;
            String lowerCaseFilter = newValue.toLowerCase();
            if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (employee.getId().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else return employee.getPhone().toLowerCase().contains(lowerCaseFilter);
        }));
        SortedList<Customer> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

        tableView.setRowFactory(tableView -> {
            final TableRow<Customer> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem addBooking = new MenuItem("Add Appointment");
            final MenuItem removeMenuItem = new MenuItem("Remove");
            removeMenuItem.setOnAction(event -> {
                try {
                    filteredList.getSource().remove(tableView.getSelectionModel().getSelectedItem());
                    CustomerDAO.deletePerson(row.getItem().getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });


            addBooking.setOnAction(actionEvent -> {
                try {
                    showAppointment(row.getItem().getName(), row.getItem().getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            contextMenu.getItems().addAll(removeMenuItem, addBooking);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(contextMenu)
            );
            return row;
        });

    }

    private void showBooked() throws IOException {
        Image img;
        img = new Image("/view/fxml/img/icon.png");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/Invoice.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.getIcons().add(img);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void showAppointment(String name, String id) throws IOException {
        Image img;
        img = new Image("/view/fxml/img/icon.png");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/AddPointment.fxml"));
        Parent root1 = fxmlLoader.load();
        AddController addController = fxmlLoader.getController();
        addController.setName(name, id);
        Stage stage = new Stage();
        stage.getIcons().add(img);
        stage.setTitle("Add new Appointment");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}