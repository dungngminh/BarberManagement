package view.controllers;

import com.calendarfx.view.TimeField;
import home.DAO.CustomerDAO;
import home.DAO.EmployeeDAO;
import home.DTO.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddController {

    @FXML
    private ComboBox<Service> selectService;

    @FXML
    private Label setCustomerName;

    @FXML
    private DatePicker selectDate;

    @FXML
    private Label setCusID;

    @FXML
    private TimeField selectTime;

    @FXML
    private Button btnCancel;

    @FXML
    void cancelAppointment() {
        Stage window = (Stage) btnCancel.getScene().getWindow();
        window.close();
    }

    @FXML
    void saveAppointment() throws SQLException {
        LocalDate date = selectDate.getValue();
        String serviceID = selectService.getValue().getMaDV();
        LocalTime time = selectTime.getValue();
        String cusID = setCusID.getText();
        customerDAO.addAppointment(cusID, date, time, serviceID);
        Stage window = (Stage) setCusID.getScene().getWindow();
        window.close();
        //
    }



    public void setName (String name, String id) {
        setCustomerName.setText(name); setCusID.setText(id);
    }

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    @FXML
    void initialize() throws SQLException {
        selectService.getItems().addAll(employeeDAO.getService());

    }

}