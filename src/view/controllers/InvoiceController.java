package view.controllers;

import home.DAO.BillDAO;
import home.DAO.EmployeeDAO;
import home.DTO.Bill;
import home.DTO.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Timestamp;

public class InvoiceController {

    @FXML
    private ImageView setImg;

    @FXML
    private Label setInvoiceID;

    @FXML
    private Label setInvoiceDate;

    @FXML
    private Label customerName;

    @FXML
    private Label customerPhone;

    @FXML
    private TextField discount;

    @FXML
    private Label subPrice;

    @FXML
    private Label grandTotal;

    @FXML
    private TableView<Bill> billTable;

    @FXML
    private ComboBox<Employee> comboBox;

    @FXML
    private TableColumn<Bill, String> serviceCol;

    @FXML
    private TableColumn<Bill, String> unitPriceCol;


    @FXML
    void deleteKey(ActionEvent event) {

    }

    @FXML
    void calGrandTotal(KeyEvent event) {
        String getDiscount ="";
        if (discount.getText()=="") {
            getDiscount = "0";
        }
        else {
            getDiscount = discount.getText();
        }
        double disc = Double.parseDouble(getDiscount);
        double money = (1-0.01*disc)*Double.parseDouble(subPrice.getText());
        String text = Double.toString(money);
        grandTotal.setText(text);
    }



    private BillDAO billDAO = new BillDAO();
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();



    @FXML
    void initialize() throws SQLException {
        comboBox.getItems().addAll(employeeDAO.getEmployee());
        javafx.scene.image.Image img;
        img = new Image("/view/fxml/img/This.png");
        setImg.setImage(img);

    }
    public void setTable (String bookID) throws SQLException {
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        ObservableList<Bill> bills = billDAO.showDetails(bookID);
        double price = 0.0;
        for (int i=0; i<bills.size(); i++)  {
            price += Double.parseDouble(bills.get(i).getUnitPrice());
            System.out.println(bills.get(i).getProductName());
        }
        subPrice.setText(String.valueOf(price));
        grandTotal.setText(String.valueOf(price));
        billTable.setItems(bills);
    }

    public void setAllText (String invoiceID, Timestamp InvoiceDate, String cusName, String cusPhone) {
        setInvoiceID.setText(invoiceID);
        System.out.println(invoiceID);
        setInvoiceDate.setText(InvoiceDate.toString());
        customerName.setText(cusName);
        customerPhone.setText(cusPhone);
    }

    @FXML
    void addRecord(ActionEvent event) throws SQLException {
        billDAO.addRecord(setInvoiceID.getText(), comboBox.getSelectionModel().getSelectedItem().getId(), grandTotal.getText());
        Stage window = (Stage) discount.getScene().getWindow();
        window.close();
    }
}