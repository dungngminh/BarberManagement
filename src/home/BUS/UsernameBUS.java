package home.BUS;

import home.DAO.UsernameDAO;

import java.sql.SQLException;

public class UsernameBUS {
    public static boolean isAdmin (String username, String password) throws SQLException {
        return UsernameDAO.isAdmin(username, password);
    }
    public static int addAcount (String username, String password) throws SQLException {
        return UsernameDAO.addAcount(username, password);
    }
    public static boolean checkLogin(String username, String password) throws SQLException {
        return UsernameDAO.checkLogin(username, password);
    }
}
