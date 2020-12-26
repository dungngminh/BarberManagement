package home.BUS;

import home.DAO.BillDAO;
import home.DTO.Bill;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BillBUS {
    public static void addRecord(String MaDatCho, String MaThoCat, String TongTien) throws SQLException {
        BillDAO.addRecord(MaDatCho, MaThoCat, TongTien);
    }
    public static Bill getBill (String bookingID) throws SQLException {
        return BillDAO.getBill(bookingID);
    }
    public static ObservableList<Bill> showDetails (String bookingID) throws SQLException {
        return BillDAO.showDetails(bookingID);
    }
}
