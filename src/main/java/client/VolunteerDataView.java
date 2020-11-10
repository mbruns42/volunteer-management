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

    final DbTables dbTables = new DbTables();
    final DefaultTableModel model = dbTables.getVolunteerDbModel();
    final JTable volunteers = new JTable(model);

    public void refresh()
    {
        try
        {
            Connection con = DbConnection.getDbConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT " + dbTables.getAllPersonCols() + " FROM Person");
            ResultSet resultSet = pstm.executeQuery();
            LOGGER.info("Executed database query for volunteers");

            ResultSetMetaData md = resultSet.getMetaData();
            int columns = md.getColumnCount();

            while(resultSet.next())
            {
                Object[] dataRow = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {
                    dataRow[i-1] = resultSet.getObject(i) ;
                }
                model.addRow(dataRow);
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
