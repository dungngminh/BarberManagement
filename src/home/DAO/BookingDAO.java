package home.DAO;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import home.SQLConnect.ReturnConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class BookingDAO {
    final private static ReturnConnect dBC = new ReturnConnect();
    public static ObservableList<Calendar> getTimeLine () throws SQLException {
        Connection con = dBC.getConnection();
        String SQL = "SELECT DonDatCho.MaKH, DonDatCho.NgayCat, KhachHang.TenKH, DonDatCho.SuatCat, DonDatCho.MaDatCho FROM DonDatCho INNER JOIN KhachHang ON DonDatCho.MaKH = KhachHang.MaKH";
        String employees = "Select TenThoCat from ThoCat where status=0";
        ResultSet rs1 = con.createStatement().executeQuery(employees);
        ObservableList<Calendar> calendars = FXCollections.observableArrayList();
        Integer i=1;
        while (rs1.next()) {
            Calendar calendar = new Calendar(rs1.getString("TenThoCat"));
            if(i%7==1) calendar.setStyle(Calendar.Style.STYLE1);
            if(i%7==2) calendar.setStyle(Calendar.Style.STYLE2);
            if(i%7==3) calendar.setStyle(Calendar.Style.STYLE3);
            if(i%7==4) calendar.setStyle(Calendar.Style.STYLE4);
            if(i%7==5) calendar.setStyle(Calendar.Style.STYLE5);
            if(i%7==6) calendar.setStyle(Calendar.Style.STYLE6);
            if(i%7==0) calendar.setStyle(Calendar.Style.STYLE7);
            i += 1;
            calendars.add(calendar);
        }
        rs1.close();
        ResultSet rs = con.createStatement().executeQuery(SQL);
        try {
            while (rs.next()) {
                Entry entry = new Entry(rs.getString("TenKH"));
                Time timeTemp = rs.getTime("SuatCat");
                LocalTime time = timeTemp.toLocalTime();
                Date day = rs.getDate("NgayCat");
                LocalDate date = day.toLocalDate();
                LocalTime time_finish = time.plusMinutes(30);
                Interval interval = new Interval(date, time, date, time_finish);
                entry.setInterval(interval);
                Random gen = new Random();
                int value = gen.nextInt(calendars.size());
                calendars.get(value).addEntries(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.close();
        return calendars;
    }
    public static void addBooked (Timestamp time, String serviceID) throws SQLException {
        Connection con = dBC.getConnection();
        String SQL = "INSERT INTO booking(bookingID, customerID, time)\n" + "VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(SQL);
    };

}
