package home.BUS;

import home.DAO.CustomerDAO;
import home.DTO.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerBUS {
    public static void deletePerson(String id) throws SQLException {
        CustomerDAO.deletePerson(id);
    }
    public static void addCustomer (String name, String phone) throws SQLException {
        CustomerDAO.addCustomer(name, phone);
    }
    public static void changeInfo(String id, String newName ,String parameter) throws SQLException {
        CustomerDAO.changeInfo(id, newName, parameter);
    }
    public static ObservableList<Customer> getCustomer() throws SQLException {
        return CustomerDAO.getCustomer();
    }
    public static void addAppointment(String MaKH, LocalDate date, LocalTime time, String serviceID) throws SQLException {
        CustomerDAO.addAppointment(MaKH, date, time, serviceID);
    }
}
