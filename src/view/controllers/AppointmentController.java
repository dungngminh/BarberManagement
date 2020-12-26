package view.controllers;

import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import home.DAO.BookingDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AppointmentController {

    @FXML
    private Button btnHA;


    @FXML
    private Button btnCA;

    @FXML
    private Button btnEA;

    @FXML
    private Button btnPA;

    @FXML
    private Button btnAbA;

    @FXML
    private CalendarView calendarViewID;

    @FXML
    private void clicksfromAppointment(ActionEvent event) throws IOException {
        if (event.getSource() == btnHA) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Main.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnEA) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Employee.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnCA) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Customer.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnPA) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/Profit.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
        if (event.getSource() == btnAbA) {
            FXMLLoader about = new FXMLLoader(getClass().getResource("/view/fxml/About.fxml"));
            Parent root1 = about.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root1, 1600, 900));
            window.show();
        }
    }


    private final BookingDAO bookingDAO = new BookingDAO();


    @FXML
    void initialize() throws SQLException {
        CalendarSource employee = new CalendarSource("Employee");
        ObservableList<Calendar> calendars = bookingDAO.getTimeLine();
        for (Calendar calendar : calendars) {
            employee.getCalendars().add(calendar);
        }
        calendarViewID.getCalendarSources().setAll(employee);
    }
}
