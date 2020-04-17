package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnection
{
    private final static Logger LOGGER = Logger.getLogger(DbConnection.class.getName());

    public static Connection con;
    public static final String DB_URL = "jdbc:mariadb://localhost/volunteers";

    public static Connection getDbConnection()
    {
        if (con == null ) {
            try
            {
                LOGGER.info("Trying to connect to database");
                //TODO change storage of login data
                con = DriverManager.getConnection(DB_URL, "test", "testtest");
            }
            catch (SQLException e)
            {
                LOGGER.severe("Could not connect to database. Error:" + e.getMessage());
                //TODO Show exception to user before exciting
                System.exit(1);
            }
        }
        return con;
    }
}
