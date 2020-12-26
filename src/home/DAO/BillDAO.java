package home.DAO;

import home.DTO.Bill;
import home.SQLConnect.ReturnConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

// Details View

public class BillDAO {
    final static private ReturnConnect dbC = new ReturnConnect();

    public static ObservableList<Bill> showDetails (String bookingID) throws SQLException {
        Connection con = dbC.getConnection();
        ObservableList<Bill> bills = FXCollections.observableArrayList();
        String SQL = "SELECT [DichVu].TenDichVu, [DichVu].GiaTien FROM DichVu  WHERE [DichVu].MaDV IN (SELECT DonDatChoChiTiet.MaDV FROM DonDatChoChiTiet WHERE DonDatChoChiTiet.MaDatCho = ?)";
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1,bookingID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Bill bill = new Bill();
            String temp = rs.getString("TenDichVu");
            String need =  temp.replaceAll("\\+", "\n");
            System.out.println(need);
            String price = rs.getString("GiaTien");
            bill.setProductName(need);
            bill.setUnitPrice(price);
            bills.add(bill);
        }
        con.close();
        return bills;
    }

    public static Bill getBill (String bookingID) throws SQLException {
        Connection con = dbC.getConnection();
        String SQL = "SELECT [DichVu].GiaTien FROM dbo.DichVu WHERE [DichVu].MaDV IN (SELECT DonDatChoChiTiet.MaDV FROM DonDatChoChiTiet WHERE DonDatChoChiTiet.MaDatCho = ?)";
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1,bookingID);
        ResultSet rs = stm.executeQuery();
        Bill bill = null;
        while (rs.next()) {
            bill = new Bill();
            String price = rs.getString("GiaTien");
            bill.setUnitPrice(price);
        }
        con.close();
        return bill;
    }

    public static void addRecord(String MaDatCho, String MaThoCat, String TongTien) throws SQLException {
        String SQL = "INSERT INTO HoaDonThanhToan(MaDatCho, MaThoCat, NgayXuatHoaDon, TongTien) VALUES(?,?,Getdate(),?)";
        String SQL1 = "UPDATE DonDatCho set TrangThaiDatCho=1 where MaDatCho=?";
        Connection con = dbC.getConnection();
        PreparedStatement stm1 = con.prepareStatement(SQL1);
        PreparedStatement stm = con.prepareStatement(SQL);
        stm1.setString(1, MaDatCho);
        stm.setString(1, MaDatCho);
        stm.setString(2, MaThoCat);
        double temp = Double.parseDouble(TongTien);
        int tongTien = (int)temp;
        stm.setInt(3, tongTien);
        stm1.execute();
        stm.execute();
        con.close();
    }

}
