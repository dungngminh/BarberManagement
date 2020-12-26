package home.DAO;

import home.SQLConnect.ReturnConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsernameDAO {
    private static final ReturnConnect dbC = new ReturnConnect();

    public static boolean checkLogin(String username, String password) throws SQLException {
        String SQL = "SELECT * from dbo.[DanhSachAcount] where dbo.[DanhSachAcount].username =? and dbo.[DanhSachAcount].password=? ";
        Connection con = dbC.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1,username);
        stm.setString(2,password);
        ResultSet rs = stm.executeQuery();
        return rs.next();
    }

    public static int addAcount (String username, String password) throws SQLException {
        String SQL = "INSERT INTO dbo.[DanhSachAccount] (username, password) VALUES(?,?,?)";
        Connection con = dbC.getConnection();
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1, username);
        stm.setString(2, password);
        try {
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stm.executeUpdate();
    }


    public static boolean isAdmin (String username, String password) throws SQLException {
        Connection con = dbC.getConnection();
        String SQL = "SELECT TenQuyen FROM DanhSachAcount Inner join QuyenUser on [DanhSachAcount].id_user = [QuyenUser].id_user INNER JOin PhanQuyen on [QuyenUser].id_role = [PhanQuyen].id_role where [DanhSachAcount].username = ? and [DanhSachAcount].password =? ";
        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setString(1, username);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        String temp = "";
        while(rs.next()) {
            temp = rs.getString("TenQuyen");
            System.out.println(temp);
        }
        if (temp.equals("Admin")) return true;
        return false;
    }

}
