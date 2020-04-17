package client;

import database.DbConnection;
import database.DbTables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import java.util.logging.Logger;

//TODO not sure whether this still needs to be a JPanel
public class VolunteerDataPanel extends JPanel
{
    private final static Logger LOGGER = Logger.getLogger(VolunteerDataPanel.class.getName());

    DefaultTableModel model;
    JTable volunteers;

    public VolunteerDataPanel()
    {
        DbTables dbTables = new DbTables();
        model = dbTables.getVolunteerDbModel();
        volunteers = new JTable(model);

        try {
            Connection con = DbConnection.getDbConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Person");
            ResultSet Rs = pstm.executeQuery();
            LOGGER.info("Executed database query for volunteers");
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)});
                LOGGER.info("Found data row for volunteers");
            }
        } catch (SQLException e) {
            LOGGER.info("Error during database query for volunteers. Error: " + e.getMessage());
            //TODO Show info to user
            //TODO Do we need to shut down here?
        }
    }

    public JTable getVolunteers() {
        return volunteers;
    }
}
