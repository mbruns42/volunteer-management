package logic;

import client.Client;
import csv.CsvMappingConstants;
import database.DbConnection;

import java.sql.*;
import java.text.ParseException;
import java.util.logging.Logger;

public class Volunteer {

    private final static Logger LOGGER = Logger.getLogger(Volunteer.class.getName());
    private final static String INSERT_VOLUNTEER = buildInsertStmt();


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
            pstm.setString(1, state);
            pstm.setString(2, salutation);
            pstm.setString(3, firstName);
            pstm.setString(4, lastName);
            pstm.setDate(5, birthday);
            pstm.setString(6, address);
            pstm.setInt(7, postCode);
            pstm.setString(8, city);
            pstm.setString(9, phoneNumber);
            pstm.setString(10, phoneNumberMobile);
            pstm.setString(11, eMail);

            pstm.setBoolean(12, HYIC);
            pstm.setBoolean(13, HYB);
            pstm.setBoolean(14, HYW);

            pstm.setBoolean(15, MoV);
            pstm.setBoolean(16, MoN);
            pstm.setBoolean(17, DiVo);
            pstm.setBoolean(18, DiN);
            pstm.setBoolean(19, MiV);
            pstm.setBoolean(20, MiN);
            pstm.setBoolean(21, DoV);
            pstm.setBoolean(22, DoN);
            pstm.setBoolean(23, FrV);
            pstm.setBoolean(24, FrN);
            pstm.setBoolean(25, SaV);
            pstm.setBoolean(26, SaN);
            pstm.setBoolean(27, SoV);
            pstm.setBoolean(28, SoN);

            pstm.setString(29, workArea);
            pstm.setString(30, workArea2);
            pstm.setString(31, workArea3);
            pstm.setString(32, workType);
            pstm.setString(33, SPR);
            pstm.setString(34, notes);
            pstm.setString(35, qualification);

            pstm.setDate(36, entryDate);
            pstm.setString(37, Client.username);
            pstm.setString(38, Client.username);


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

    private static String buildInsertStmt()
    {
        StringBuilder stmt;
        stmt = new StringBuilder("INSERT INTO volunteers.Person ( \n");
        stmt.append(CsvMappingConstants.getAllCsvCols());
        stmt.append(",InsDate,InsBy,UpdDate,UpdBy) VALUES (\n");
        for (int i = 0; i< CsvMappingConstants.getColNum(); i++)
        {
            stmt.append("?,");
        }
        stmt.append("CURRENT_DATE,CONCAT('CSVimport/',?),CURRENT_DATE,CONCAT('CSVimport/',?))");
        return stmt.toString();
    }
}


