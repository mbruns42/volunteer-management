package client;

import database.DbConnection;
import database.DbTables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VolunteerDataPanel extends JPanel
{

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
            //TODO Add logger
            System.out.println("Select ausgeführt");
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)});
                System.out.println("Daten gefunden");
            }
        } catch (Exception e) {
            //TODO Add logger
            System.out.println(e.getMessage());
        }
    }

    public JTable getVolunteers() {
        return volunteers;
    }
}
