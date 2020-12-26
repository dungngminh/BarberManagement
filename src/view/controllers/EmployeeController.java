package view.controllers;

import home.DAO.EmployeeDAO;
import home.DTO.Employee;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeController {


    @FXML
    private Button btnHE;

    @FXML
    private Button btnAE;

    @FXML
    private Button btnCE;

    @FXML
    private Button btnPE;

    @FXML
    private Button btnAbE;


    @FXML
    private TextField searchField;

    @FXML
    private TableView<Employee> EmployeeTable;

    @FXML
    private TableColumn<Employee, String> id;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, Integer> age;

    @FXML
    private TableColumn<Employee, String> phone;

    @FXML
    private TableColumn<Employee, Float> exp;


    @FXML
    private void clicksFromEmployee(ActionEvent event) throws IOException {
        if (event.getSource()==btnHE) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource()==btnAE) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Appointment.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource()==btnCE) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Customer.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource()==btnPE) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Profit.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource()==btnAbE) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/About.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
    }


    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private ObservableList<Employee> tableItems;


    @FXML
    void initialize() throws SQLException {

        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        exp.setCellValueFactory(new PropertyValueFactory<>("exp"));
        EmployeeTable.setItems(employeeDAO.getEmployee());

        FilteredList<Employee> filteredList = new FilteredList<>(employeeDAO.getEmployee(), b->true);
        searchField.textProperty().addListener((observable, oldValue, newValue)->{
            filteredList.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (employee.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (employee.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else return false;
            });
        });
        SortedList<Employee> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(EmployeeTable.comparatorProperty());
        EmployeeTable.setItems(sortedList);

        EmployeeTable.setRowFactory(tableView -> {
            final TableRow<Employee> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeMenuItem = new MenuItem("Remove");

            removeMenuItem.setOnAction(event -> {
                try {
                    filteredList.getSource().remove(EmployeeTable.getSelectionModel().getSelectedItem());
                    employeeDAO.deletePerson(row.getItem().getId(), "employee");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            contextMenu.getItems().add(removeMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(contextMenu)
            );
            return row ;
        });

    }

    private void showDialog() throws SQLException {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 20, 10, 10));
        TextField textField1 = new TextField();
        Label label1 = new Label("Name: ");
        Label label3 = new Label("Age: ");
        Label label4 = new Label("Phone: ");
        Label label5 = new Label("Id: ");
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        vbox.getChildren().addAll(label1, textField1, label3, textField3, label4, textField4, label5, textField5);
        Dialog dialog = new Dialog();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(vbox);
        dialogPane.setPrefSize(600, 600);
        dialog.setTitle("Add new Employee");
        ButtonType okBtn = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogPane.getButtonTypes().addAll(okBtn, cancelBtn);
        dialog.initOwner(btnHE.getScene().getWindow());
        Optional optionalResult = dialog.showAndWait();

        if (optionalResult.get() == okBtn) {
            String firstName = textField1.getText();
            String lastName = textField2.getText();
            String ages = textField3.getText();
            Float age = Float.parseFloat(ages);
            String phone = textField4.getText();
            String id = textField5.getText();
            employeeDAO.addEmployee(firstName+lastName, id, phone, age);
            EmployeeTable.setItems(employeeDAO.getEmployee());
        }
    }
    @FXML
    void AddEmployee(ActionEvent event) throws SQLException {
        showDialog();
    }

}
