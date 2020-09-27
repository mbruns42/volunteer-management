package csv;


import java.io.File;
import java.util.logging.Logger;

public final class CsvImporter {

    private final static Logger LOGGER = Logger.getLogger(CsvImporter.class.getName());

    public static final void tryImportIntoDb(File csvFile)
    {
        LOGGER.info("Trying import for file "+ csvFile);
    }

}
