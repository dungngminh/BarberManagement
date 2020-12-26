package home.BUS;

import home.DAO.BillTableDAO;
import home.DTO.BillTable;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BillTableBUS {
    public static ObservableList<BillTable> getBillTable() throws SQLException {
        return BillTableDAO.getBillTable();
    }
}
