package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection
{
    public static Connection con;
    public static final String DB_URL = "jdbc:mariadb://localhost/volunteers";

    public static Connection getDbConnection()
    {
        if (con == null ) {
            try
            {
                con = DriverManager.getConnection(DB_URL, "test", "testtest");
            }
            catch (SQLException e)
            {
                //TODO Log + Display error:: Connector loaded, but could not connect to database
                System.out.println("Connecting: " + e.getMessage());
            }
        }
        return con;
    }
}
