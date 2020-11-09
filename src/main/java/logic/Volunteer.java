package logic;

import database.DbConnection;

import java.sql.*;
import java.util.logging.Logger;

public class Volunteer {

    private final static Logger LOGGER = Logger.getLogger(Volunteer.class.getName());
    private final static String INSERT_VOLUNTEER = "INSERT INTO `volunteers`.`Person`\n" +
            "(`LastName`,`FirstName`,`Address`,`City`,\n" +
            "`EMail`,`Birthday`,`EntryDate`,\n" +
            "`InsDate`,`InsBy`,`UpdDate`,`UpdBy`) \n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?,\n" +
            "CURRENT_DATE,'CSVimport',CURRENT_DATE,'CSVimport')";
    private String lastName = "LastName";
    private String firstName= "FirstName";
    private String address = "Address";
    private String city = "City";
    private String email = "EMail";
    private Date birthday = new Date(0);
    private Date entryDate = new Date(System.currentTimeMillis());

    public Volunteer(String[] attributes) {
        LOGGER.info("Volunteer intialization");
        String volunteerInfo = "";
        int i = 0;
        for (String attribute : attributes)
        {
            i++;
            volunteerInfo += i + attribute;
        }
        LOGGER.info("Volunteer " + volunteerInfo);
        insertIntoDatabase();
    }

    private boolean insertIntoDatabase() {
        try {
            Connection con = DbConnection.getDbConnection();
            PreparedStatement pstm = con.prepareStatement(INSERT_VOLUNTEER);
            pstm.setString(1, lastName);
            pstm.setString(2, firstName);
            pstm.setString(3, address);
            pstm.setString(4, city);
            pstm.setString(5, email);
            pstm.setDate(6, birthday);
            pstm.setDate(7, entryDate);


            LOGGER.info("Executing insert for volunteer: " +pstm.toString());
            int result = pstm.executeUpdate();
            LOGGER.info("Inserted " + result +"rows");
            return  true;
        } catch (
                SQLException e) {
            LOGGER.info("Error during database query for volunteers. Error: " + e.getMessage());
            //TODO Show info to user
        }
        return false;
    }
}
