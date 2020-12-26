package home.DAO;

import home.DTO.Customer;
import home.SQLConnect.ReturnConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerDAO {
    private static final ReturnConnect dbC = new ReturnConnect();

    public static void deletePerson(String id) throws SQLException {
        Connection con = dbC.getConnection();
        String deleteQuery= "Update KhachHang SET status=1 where MaKH =?";
        PreparedStatement stm = con.prepareStatement(deleteQuery);
        stm.setString(1, id);
        stm.execute();
        con.close();
    }

    public static void addCustomer (String name, String phone) throws SQLException {
        Connection con = dbC.getConnection();
        String SQL = "INSERT INTO KhachHang(TenKH, Phone) VALUES(?,?)";
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1, name);
        stm.setString(2, phone);
        stm.execute();
        con.close();
    }

    public static void changeInfo(String id, String newName ,String parameter) throws SQLException {
        Connection con = dbC.getConnection();
        String query ="";
        if (parameter.equals("name")) {
            query = "Update KhachHang SET TenKH=? WHERE MaKH=?";
        }
        if (parameter.equals("phone")) {
            query = "Update KhachHang SET Phone=? WHERE MaKH=?";
        }
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,newName);
        stm.setString(2,id);
        stm.execute();
        con.close();
    }

    public static ObservableList<Customer> getCustomer() throws SQLException {
        Connection con = dbC.getConnection();
        String SQL = "SELECT * from KhachHang where status=0";
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        ResultSet rs = con.createStatement().executeQuery(SQL);
        try {
            while (rs.next()) {
                Customer temp = new Customer("a", "b", "c", 1, 2);
                temp.setId(rs.getString("MaKH"));
                temp.setName(rs.getString("TenKH"));
                temp.setPhone(rs.getString("Phone"));
                customers.add(temp);
            }
        } catch (Exception e) {
            System.out.println("Error on building data!");
            e.printStackTrace();
        }
        con.close();
        return customers;
    }

    public static void addAppointment(String MaKH, LocalDate date, LocalTime time, String serviceID) throws SQLException {
        Connection con = dbC.getConnection();
        String Ma = "";
        String SQL = "Insert into DonDatCho(MaKH, NgayCat, SuatCat) VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1, MaKH);
        stm.setDate(2, Date.valueOf(date));
        stm.setTime(3, Time.valueOf(time));
        stm.execute();
        String SQL1 = "Insert into DonDatChoChiTiet(MaDatCho, MaDV) VALUES(?,?)";
        String SQL3 = "Select MaDatCho from DonDatCho where MaDatCho NOT IN (SELECT MaDatCho from DonDatChoChiTiet)";
        PreparedStatement preSQL = con.prepareStatement(SQL3);
        ResultSet r = preSQL.executeQuery();
        while (r.next()) {
            Ma = r.getString("MaDatCho");
        }
        PreparedStatement stm1 = con.prepareStatement(SQL1);
        stm1.setString(1,Ma);
        stm1.setString(2,serviceID);
        stm1.execute();
        con.close();
    }
}
