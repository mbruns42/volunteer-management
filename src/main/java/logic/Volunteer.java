package logic;

import csv.CsvMappingConstants;
import database.DbConnection;

import java.sql.*;
import java.text.ParseException;
import java.util.logging.Logger;

public class Volunteer {

    private final static Logger LOGGER = Logger.getLogger(Volunteer.class.getName());
    private final static String INSERT_VOLUNTEER = "INSERT INTO `volunteers`.`Person`\n" +
            "(`LastName`,`FirstName`,`Address`,`City`,\n" +
            "`EMail`,`Birthday`,`EntryDate`,\n" +
            "`InsDate`,`InsBy`,`UpdDate`,`UpdBy`) \n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?,\n" +
            "CURRENT_DATE,'CSVimport',CURRENT_DATE,'CSVimport')";

    private String state = "undef";
    private String salutation = "";
    private String lastName = "undef";
    private String firstName= "undef";
    private String address = "undef";
    private int postCode=0;
    private String city = "undef";
    private String phoneNumber = "";
    private String phoneNumberMobile = "";
    private String eMail = "undef";

    private boolean HYIC = false; /* Hygieneinformation Corona neu seit 20.05.20 */
    private boolean HYB=false;  /* Hygienebelehrung Gesundheitsamt */
    private boolean HYW=false; /* Hygienewiederholung im Gast-Haus_Januar */
    private boolean MoV =false;
    private boolean MoN =false;
    private boolean DiVo =false;
    private boolean DiN =false;
    private boolean MiV =false;
    private boolean MiN =false;
    private boolean DoV =false;
    private boolean DoN =false;
    private boolean FrV =false;
    private boolean FrN =false;
    private boolean SaV =false;
    private boolean SaN =false;
    private boolean SoV =false;
    private boolean SoN =false;

    private String workArea = "undef";
    private String workArea2 = "";
    private String workArea3 = "";
    private String workType = "undef";
    private String SPR = "";
    private String notes = "undef";
    private String qualification;

    private Date birthday = new Date(0);
    private Date entryDate = new Date(System.currentTimeMillis());

    public Volunteer(String[] attributes) {
        LOGGER.info("Volunteer intialization");
        setFromCSVAttributes(attributes);
        if (!isInDatabase()) {
            insertIntoDatabase();
        }
    }

    private boolean isInDatabase() {
        //TODO Check db
        return false;
    }


    private boolean insertIntoDatabase() {
        try {
            Connection con = DbConnection.getDbConnection();
            PreparedStatement pstm = con.prepareStatement(INSERT_VOLUNTEER);
            pstm.setString(1, lastName);
            pstm.setString(2, firstName);
            pstm.setString(3, address);
            pstm.setString(4, city);
            pstm.setString(5, eMail);
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


    private void setFromCSVAttributes(String[] attributes)
    {

        try {
            for (int i=0; i < CsvMappingConstants.volunteerCsv.length; i++) {
                LOGGER.info(CsvMappingConstants.volunteerCsv[i] + "=" + attributes[i]);
                switch (CsvMappingConstants.volunteerCsv[i]) {
                    case "state":
                        state = attributes[i];
                        break;
                    case "salutation":
                        salutation = attributes[i];
                        break;
                    case "firstName":
                        firstName = attributes[i];
                        break;
                    case "lastName":
                        lastName = attributes[i];
                        break;
                    case "birthday":
                        java.util.Date date = CsvMappingConstants.dateFormat.parse(attributes[i]);
                        birthday = new java.sql.Date(date.getTime());
                        break;
                    case "address":
                        address = attributes[i];
                        break;
                    case "postCode":
                        postCode = Integer.parseInt(attributes[i]);
                        break;
                    case "city":
                        city = attributes[i];
                        break;
                    case "phoneNumber":
                        phoneNumber = attributes[i];
                        break;
                    case "phoneNumberMobile":
                        phoneNumberMobile = attributes[i];
                        break;
                    case "email":
                        eMail = attributes[i];
                        break;
                    case "HYIC":
                        HYIC = attributes[i].contains("x");
                        break;
                    case "HYB":
                        HYB = attributes[i].contains("x");
                        break;
                    case "HYW":
                        HYW = attributes[i].contains("x");
                        break;
                    case "MoV":
                        MoV = attributes[i].contains("x");
                        break;
                    case "MoN":
                        MoN = attributes[i].contains("x");
                        break;
                    case "DiVo":
                        DiVo = attributes[i].contains("x");
                        break;
                    case "DiN":
                        DiN = attributes[i].contains("x");
                        break;
                    case "MiV":
                        MiV = attributes[i].contains("x");
                        break;
                    case "DoV":
                        DoV = attributes[i].contains("x");
                        break;
                    case "DoN":
                        DoN = attributes[i].contains("x");
                        break;
                    case "FrV":
                        FrV = attributes[i].contains("x");
                        break;
                    case "FrN":
                        FrN = attributes[i].contains("x");
                        break;
                    case "SaV":
                        SaV = attributes[i].contains("x");
                        break;
                    case "SaN":
                        SaN = attributes[i].contains("x");
                        break;
                    case "SoV":
                        SoV = attributes[i].contains("x");
                        break;
                    case "SoN":
                        SoN = attributes[i].contains("x");
                        break;
                    case "workArea":
                        workArea = attributes[i];
                        break;
                    case "workArea2":
                        workArea2 = attributes[i];
                        break;
                    case "workArea3":
                        workArea3 = attributes[i];
                        break;
                    case "workType":
                        workType = attributes[i];
                        break;
                    case "SPR":
                        SPR = attributes[i];
                        break;
                    case "notes":
                        notes = attributes[i];
                        break;
                    case "qualification":
                        qualification = attributes[i];
                        break;
                    case "entryDate":
                        date = CsvMappingConstants.dateFormat.parse(attributes[i]);
                        entryDate = new java.sql.Date(date.getTime());
                        break;
                    default:
                        LOGGER.warning("Attribute konnte nicht zugeordnet werden:" + CsvMappingConstants.volunteerCsv[i]);
                }
            }
        } catch (ParseException e)
        {
            LOGGER.warning("Could not parse data for volunteer!");
            e.printStackTrace();
        }
    }
}
