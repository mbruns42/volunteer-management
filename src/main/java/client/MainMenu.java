package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class MainMenu extends JMenuBar {

    private final static Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    private CardLayout cardLayout;
    private JPanel cardPanel;
    public JMenu viewMenu;
    public JMenu editMenu;
    public JMenu optionMenu;

    public MainMenu(CardLayout cardLayout, JPanel cardPanel)
    {
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
        this.add(editMenu);
    }

    private void initOptionMenu()
    {
        optionMenu = new JMenu("Einstellungen");
        optionMenu.setMnemonic(KeyEvent.VK_E);
        this.add(optionMenu);
    }
}
