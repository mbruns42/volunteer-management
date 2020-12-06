package database;

import javax.swing.table.DefaultTableModel;

public final class DbTables {

    public static final DefaultTableModel volunteerDbModel = new DefaultTableModel();
    public static final String[] personTableCols =  {
            "PersonID",
            "LastName",
            "FirstName",
            "state",
            "salutation",
            "Birthday",
            "Address",
            "PostCode",
            "City",
            "phoneNumber",
            "phoneNumberMobile",
            "EMail",
            "HYIC", /* Hygieneinformation Corona neu seit 20.05.20 */
            "HYB", /* Hygienebelehrung Gesundheitsamt */
            "HYW", /* Hygienewiederholung im Gast-Haus_Januar */
            "MoV",
            "MoN",
            "DiVo",
            "DiN",
            "MiV",
            "MiN",
            "DoV",
            "DoN",
            "FrV",
            "FrN",
            "SaV",
            "SaN",
            "SoV",
            "SoN",
            "workArea",
            "workArea2",
            "workArea3",
            "workArea4",
            "workType",
            "SPR",
            "Notes",
            "Qualification",
            "EntryDate",
            "InsDate",
            "InsBy",
            "UpdDate",
            "UpdBy"
    };

    public DbTables()
    {
        for (String col : personTableCols){
            volunteerDbModel.addColumn(col);
        }
    }

    public DefaultTableModel getVolunteerDbModel()
    {
        return volunteerDbModel;
    }

    public String getAllPersonCols()
    {
        StringBuilder allCols = new StringBuilder();
        for (String col : personTableCols){
            allCols.append(col).append(",");
        }
        return allCols.substring(0, allCols.length()-1);
    }
}
