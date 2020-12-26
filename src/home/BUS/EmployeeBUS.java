package home.BUS;

import home.DAO.EmployeeDAO;
import home.DTO.Employee;
import home.DTO.Service;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class EmployeeBUS {
    public static ObservableList<Employee> getSalary() throws SQLException {
        return EmployeeDAO.getSalary();
    }
    public static void addService(String TenDV, String price) throws SQLException {
        EmployeeDAO.addService(TenDV, price);
    }
    public static ObservableList<Service> getService () throws SQLException {
        return EmployeeDAO.getService();
    }
    public static ObservableList<Employee> getEmployee () throws SQLException {
        return EmployeeDAO.getEmployee();
    }
    public static void addEmployee (String name, String id, String phone, Float exp) throws SQLException {
        EmployeeDAO.addEmployee(name, id, phone, exp);
    }
    public static void deletePerson (String id, String role) throws SQLException {
        EmployeeDAO.deletePerson(id, role);
    }

}
