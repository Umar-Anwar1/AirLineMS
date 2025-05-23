import java.sql.*;
public class DBconnection {

    public static Connection getConnection() {
        String URL = "jdbc:mysql:///AirLineMS";
        String DBusername = "root";
        String DBpassword = "Umar@1321";

        try {
            return DriverManager.getConnection(URL, DBusername, DBpassword);
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed!", e);
        }
    }
}

