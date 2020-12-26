package home.DAO;

import home.SQLConnect.ReturnConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class DataDAO {
    final private static ReturnConnect dbC = new ReturnConnect();

    public ObservableList<Double> getAvgCus () throws SQLException {
        Connection con = dbC.getConnection();
        ObservableList<Double> results = FXCollections.observableArrayList();
        String SQL = "select count(MaHoaDon) as 'TongRecord' from HoaDonThanhToan where datename(dw,NgayXuatHoaDon) = ?";
        String totalCount = "select count(distinct(NgayXuatHoaDon)) as 'TongThuNgay' from HoaDonThanhToan where datename(dw, NgayXuatHoaDon) = ?";
        String[] day = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i=0; i<7; i++) {
            int totalRecord = 0;
            int totalDay = 1;
            PreparedStatement stmt = con.prepareStatement(SQL);
            PreparedStatement count = con.prepareStatement(totalCount);
            stmt.setString(1, day[i]);
            count.setString(1, day[i]);
            ResultSet rs = stmt.executeQuery();
            ResultSet rs1 = count.executeQuery();
            if (rs.next()) { totalRecord = rs.getInt("TongRecord"); }
            if (rs1.next()) { totalDay = rs1.getInt("TongThuNgay"); }
            double avg = totalRecord/totalDay;
            results.add(avg);
        }
        return results;
    }

    public static ObservableList<Pair<Double, Double>> getPricePerCustomer() throws SQLException {
        Connection con = dbC.getConnection();
        ObservableList<Pair<Double, Double>> results = FXCollections.observableArrayList();
        String SQL = "Select count(MaHoaDon) as 'SoKhach', sum(TongTien) as 'TongTienTrongNgay' from HoaDonThanhToan Group by (NgayXuatHoaDon)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            double key = rs.getInt("SoKhach");
            double value = rs.getInt("TongTienTrongNgay");
            Pair<Double, Double> pair = new Pair<>(key,value);
            results.add(pair);
        }
        return results;
    }

    public static ObservableList<Double> getLast2months () throws SQLException {
        Connection con = dbC.getConnection();
        ObservableList<Double> results = FXCollections.observableArrayList();
        int currentValue = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String SQL = "Select sum(TongTien) as 'TT' from HoaDonThanhToan where Month(NgayXuatHoaDon)=?\n" +
                "union\n" +
                "Select sum(TongTien) as 'TT' from HoaDonThanhToan where Month(NgayXuatHoaDon)=?\n" +
                "union\n" +
                "Select sum(TongTien) as 'TT' from HoaDonThanhToan where Month(NgayXuatHoaDon)=?";
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setInt(1,currentValue-2);
        stm.setInt(2,currentValue-1);
        stm.setInt(3, currentValue);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            double t = rs.getFloat("TT");
            results.add(t);
        }
        return results;
    }
}
