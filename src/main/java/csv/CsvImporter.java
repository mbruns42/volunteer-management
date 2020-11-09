package csv;


import logic.Volunteer;

import java.io.*;
import java.util.logging.Logger;

public final class CsvImporter {

    private final static Logger LOGGER = Logger.getLogger(CsvImporter.class.getName());

    public static void tryImportIntoDb(File csvFile)
    {
        LOGGER.info("Trying import for file "+ csvFile);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of the file, using a semicolon as the delimiter
                String[] attributes = line.split(";");

                createVolunteer(attributes);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void createVolunteer(String[] attributes)
    {
        new Volunteer(attributes);
    }
}
