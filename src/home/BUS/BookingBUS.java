package home.BUS;

import com.calendarfx.model.Calendar;
import home.DAO.BookingDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;

public class BookingBUS {
    public static void addBooked (Timestamp time, String serviceID) throws SQLException {
        BookingDAO.addBooked(time, serviceID);
    }
    public static ObservableList<Calendar> getTimeLine () throws SQLException {
        return BookingDAO.getTimeLine();
    }
}
