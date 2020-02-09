package client;

import client.database.DbConnection;
import client.database.DbTables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VolunteerDataPanel extends JPanel
{

    DefaultTableModel model ;

    public VolunteerDataPanel(JTable volunteers)
    {
        DbTables dbTables = new DbTables();
        model = dbTables.getVolunteerDbModel();
        volunteers = new JTable(model);

        try {
            Connection con = DbConnection.getDbConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Person");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)});
            }
        } catch (Exception e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

}
