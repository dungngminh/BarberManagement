package home.DAO;

import home.DTO.Employee;
import home.DTO.Service;
import home.SQLConnect.ReturnConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    private static final ReturnConnect dbC = new ReturnConnect();


    public static void deletePerson (String id, String role) throws SQLException {
        Connection con = dbC.getConnection();
        String deleteQuery="";
        if (role.equals("employee")) {
            deleteQuery = "Update ThoCat set status=1 where MaThoCat =?";
        }
        if (role.equals("customer")) {
            deleteQuery = "Delete from KhachHang where MaKhachHang =?";
        }
        PreparedStatement stm = con.prepareStatement(deleteQuery);
        stm.setString(1, id);
        stm.execute();
        con.close();
    }

    public static void addEmployee (String name, String id, String phone, Float exp) throws SQLException {
        Connection con =  dbC.getConnection();
        String query  = "INSERT INTO ThoCat(MaThoCat, phone, TenThoCat, NamKN)\n" + "VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, id);
        stm.setString(2, phone);
        stm.setString(3, name);
        stm.setFloat(4, exp);
        stm.execute();
        stm.close();
    }

    public static ObservableList<Employee> getEmployee () throws SQLException {
        Connection con = dbC.getConnection();
        String SQL = "SELECT * from ThoCat where status=0";
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        ResultSet rs = con.createStatement().executeQuery(SQL);
        try {
            while (rs.next()) {
                Employee temp = new Employee("a", "b", "c", 1, 2);
                temp.setId(rs.getString("MaThoCat"));
                temp.setExp(rs.getInt("NamKN"));
                temp.setAge(rs.getInt("age"));
                temp.setName(rs.getString("TenThoCat"));
                temp.setPhone(rs.getString("phone"));
                employees.add(temp);
            }
        } catch (Exception e) {
            System.out.println("Error on building data!");
            e.printStackTrace();
        }
        con.close();
        return employees;
    }

    public static ObservableList<Service> getService () throws SQLException {
        Connection con = dbC.getConnection();
        String SQL = "SELECT * from DichVu";
        ObservableList<Service> services = FXCollections.observableArrayList();
        ResultSet rs = con.createStatement().executeQuery(SQL);
        try {
            while (rs.next()) {
                Service service = new Service();
                service.setMaDV(rs.getString("MaDV"));
                service.setTenDV(rs.getString("TenDichVu"));
                service.setGiaTien(rs.getString("GiaTien"));
                services.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    public static void addService(String TenDV, String price) throws SQLException {
        String SQL = "INSERT INTO DichVu(TenDichVu, GiaTien) VALUES(?,?)";
        Connection con = dbC.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1, TenDV);
        stm.setString(2, price);
        stm.execute();
        stm.close();
    }

    public static ObservableList<Employee> getSalary() throws SQLException {
        ObservableList<Employee> salaries = FXCollections.observableArrayList();
        Connection con = dbC.getConnection();
        String SQL = "Select [HoaDonThanhToan].MaThoCat, TenThoCat, NamKN, count(MaHoaDon) as 'TT' From ThoCat Inner join HoaDonThanhToan on [ThoCat].MaThoCat = [HoaDonThanhToan].MaThoCat where (Month(NgayXuatHoaDon)=Month(GetDate())) Group by [HoaDonThanhToan].MaThoCat, [ThoCat].TenThoCat, [ThoCat].NamKN";
        PreparedStatement stm = con.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Employee temp = new Employee("a", "b", "c", 1, 2);
            temp.setExp(rs.getInt("NamKN"));
            temp.setName(rs.getString("TenThoCat"));
            temp.setId(rs.getString("MaThoCat"));
            temp.setTimes(rs.getInt("TT"));
            int Salary = 3000000 + rs.getInt("TT")*10000 + rs.getInt("NamKN")*500000;
            temp.setSalary(Salary);
            salaries.add(temp);
        }
        return salaries;
    }

}
