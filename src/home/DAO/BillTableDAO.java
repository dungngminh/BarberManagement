package home.DAO;

import home.DTO.BillTable;
import home.SQLConnect.ReturnConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

// General View

public class BillTableDAO {
     final private static ReturnConnect dbC = new ReturnConnect();

     public static ObservableList<BillTable> getBillTable() throws SQLException {
         ObservableList<BillTable> billTables = FXCollections.observableArrayList();
         Connection con = dbC.getConnection();
         String SQL = "SELECT  DonDatCho.MaDatCho, KhachHang.TenKH, KhachHang.MaKH, DonDatCho.NgayCat, DonDatCho.SuatCat FROM (DonDatCho INNER JOIN KhachHang ON KhachHang.MaKH = DonDatCho.MaKH) Where TrangThaiDatCho=0";
         PreparedStatement stm = con.prepareStatement(SQL);
         ResultSet rs = stm.executeQuery();
         while (rs.next()) {
             BillTable temp = new BillTable();
             temp.setBookingID(rs.getString("MaDatCho"));
             temp.setCusName(rs.getString("TenKH"));
             temp.setCusID(rs.getString("MaKH"));
             temp.setDate(rs.getDate("NgayCat").toString());
             temp.setTime(rs.getTime("SuatCat").toString());
             billTables.add(temp);
         }
         return billTables;
    }
}
