package client.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection
{
    public static Connection con;
    public static final String DB_URL = "jdbc:mariadb://localhost:3306/volunteers";

    public static Connection getDbConnection()
    {
        if (con == null ) {
            try
            {
                Class.forName("com.mariadb.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, "test", "testtest");
            }
            catch (ClassNotFoundException e)
            {
                //TODO Log + Display error: Database connector could not be found
                System.out.println("Db Connector: " + e.getMessage());
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
