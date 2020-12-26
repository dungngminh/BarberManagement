package home.DTO;


import java.sql.Timestamp;


public class BillTable {
    private String bookingID;
    private String cusName;
    private String cusID;
    private String time;
    private String date;

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getBillID() {
        return bookingID;
    }
    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate1() {
        return date;
    }

    public Timestamp getDate() {
        String datetime = date + " " + time;
        Timestamp r = Timestamp.valueOf(datetime);
        return r;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
