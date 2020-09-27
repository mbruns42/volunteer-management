package csv;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;


public class CsvImportActionListener implements ActionListener {
    final JFileChooser fc = new JFileChooser();
    private final JFrame parent;
    FileFilter csvFilter = new FileNameExtensionFilter("CSV-Dateien", "csv");

    private final static Logger LOGGER = Logger.getLogger(CsvImportActionListener.class.getName());

    public CsvImportActionListener(JFrame parent){
        this.parent = parent;
        fc.addChoosableFileFilter(csvFilter);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //In response to a button click:
        int returnVal = fc.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            LOGGER.info("Opening: " + file.getName() );
            CsvImporter.tryImportIntoDb(file);
        } else {
            LOGGER.info("Open command cancelled by user.");
        }
    }
}
