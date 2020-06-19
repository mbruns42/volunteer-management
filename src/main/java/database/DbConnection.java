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

    public static void initDbConnection(String username, char[] password) throws SQLException
    {

        LOGGER.info("Trying to connect to database");
        con = DriverManager.getConnection(DB_URL, username, new String(password));
        LOGGER.info("Successfully connected to database");

    }

    public static Connection getDbConnection()
    {
        return con;
    }

}