package client;

import csv.CsvImportActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class MainMenu extends JMenuBar {

    private final static Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    private final JFrame client;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    public JMenu viewMenu;
    public JMenu editMenu;
    public JMenu optionMenu;

    public MainMenu(CardLayout cardLayout, JPanel cardPanel, JFrame client)
    {
        this.client = client;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        initViewMenu();
        initEditMenu();
        initOptionMenu();
    }

    private void initViewMenu()
    {
        LOGGER.info("Initializing Main Menu");

        viewMenu = new JMenu("Ansicht");
        viewMenu.setMnemonic(KeyEvent.VK_A);

        JMenuItem personViewMenuItem = new JMenuItem("Personen");
        personViewMenuItem.setMnemonic(KeyEvent.VK_P);
        personViewMenuItem.setToolTipText("Personendaten anzeigen");
        personViewMenuItem.addActionListener((event) -> cardLayout.show(cardPanel, "Personen"));
        viewMenu.add(personViewMenuItem);

        JMenuItem startViewMenuItem = new JMenuItem("Startseite");
        startViewMenuItem.setMnemonic(KeyEvent.VK_P);
        startViewMenuItem.setToolTipText("Startseite anzeigen");
        startViewMenuItem.addActionListener((event) -> cardLayout.show(cardPanel, "Startseite"));
        viewMenu.add(startViewMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Beenden");
        exitMenuItem.setMnemonic(KeyEvent.VK_B);
        exitMenuItem.setToolTipText("Anwendung beenden");
        exitMenuItem.addActionListener((event) -> System.exit(0));
        viewMenu.add(exitMenuItem);

        this.add(viewMenu);
    }

    private void initEditMenu()
    {
        editMenu = new JMenu("Bearbeiten");
        editMenu.setMnemonic(KeyEvent.VK_B);

        JMenuItem csvImpMenuItem = new JMenuItem("CSV-Daten importieren");
        csvImpMenuItem.setMnemonic(KeyEvent.VK_P);
        csvImpMenuItem.setToolTipText("Daten aus einer CSV-Datei importieren");
        csvImpMenuItem.addActionListener((event) -> cardLayout.show(cardPanel, "Personen"));
        csvImpMenuItem.addActionListener(new CsvImportActionListener(client));
        editMenu.add(csvImpMenuItem);

        this.add(editMenu);
    }

    private void initOptionMenu()
    {
        optionMenu = new JMenu("Einstellungen");
        optionMenu.setMnemonic(KeyEvent.VK_E);
        this.add(optionMenu);
    }
}
