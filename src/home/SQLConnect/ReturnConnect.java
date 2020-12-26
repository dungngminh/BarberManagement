package home.SQLConnect;

import java.sql.*;


public class ReturnConnect {
    public static final String url = "jdbc:sqlserver://LEQUOCTHINH\\MSSQLSERVER01;databaseName=TiemCatTocTest";
    public static final String user = "guest";
    public static final String password = "sa";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
