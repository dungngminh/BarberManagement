package home.BUS;

import home.DAO.DataDAO;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.SQLException;

public class DataBUS {
    public static ObservableList<Double> getLast2months () throws SQLException {
        return DataDAO.getLast2months();
    }
    public static ObservableList<Pair<Double, Double>> getPricePerCustomer() throws SQLException {
        return DataDAO.getPricePerCustomer();
    }
}
