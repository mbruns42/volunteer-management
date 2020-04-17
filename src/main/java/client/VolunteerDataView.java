package client;

import database.DbConnection;
import database.DbTables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import java.util.logging.Logger;

public class VolunteerDataView
{
    private final static Logger LOGGER = Logger.getLogger(VolunteerDataView.class.getName());

    DbTables dbTables = new DbTables();
    DefaultTableModel model = dbTables.getVolunteerDbModel();
    JTable volunteers = new JTable(model);

    public void refresh()
    {
        try
        {
            Connection con = DbConnection.getDbConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Person");
            ResultSet Rs = pstm.executeQuery();
            LOGGER.info("Executed database query for volunteers");
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)});
                LOGGER.info("Found data row for volunteers");
            }
        } catch (SQLException e)
        {
            LOGGER.info("Error during database query for volunteers. Error: " + e.getMessage());
            //TODO Show info to user
            //TODO Do we need to shut down here?
        }
    }

    public JScrollPane getView()
    {
        refresh();
        return new JScrollPane(volunteers);
    }
}
