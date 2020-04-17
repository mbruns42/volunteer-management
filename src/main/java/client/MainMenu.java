package client;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class MainMenu extends JMenuBar {

    private final static Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    public JMenu viewMenu;
    public JMenu editMenu;
    public JMenu optionMenu;

    public MainMenu()
    {
        initViewMenu();
        initEditMenu();
        initOptionMenu();
    }

    private void initViewMenu()
    {
        LOGGER.info("Initializing Main Menu");

        viewMenu = new JMenu("Ansicht");
        viewMenu.setMnemonic(KeyEvent.VK_A);

        JMenuItem exitMenuItem = new JMenuItem("Beenden");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
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
