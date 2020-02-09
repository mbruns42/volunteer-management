package client.database;

import javax.swing.table.DefaultTableModel;

public class DbTables {

    //TODO: Should the Model itself be static?

    DefaultTableModel volunteerDbModel = new DefaultTableModel();
    public static final String[] personTableCols =  {
            "PersonID",
            "LastName ",
            "FirstName",
            "Address",
            "City",
            "EMail",
            "Birthday",
            "EntryDate",
            "InsDate",
            "InsBy",
            "UpdDate",
            "UpdBy"};

    public DbTables()
    {
        for (String col : personTableCols){
            volunteerDbModel.addColumn(col);
        }
    }

    public DefaultTableModel getVolunteerDbModel() {
        return volunteerDbModel;
    }
}
