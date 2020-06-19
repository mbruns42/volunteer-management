package client;

import database.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Client extends JFrame
{
    public static final int MAX_LOGIN_TRIES=3;
    public static String username = "unknown";

    public Client()
    {
        login();
        initUI();
    }

    private void login() {

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Benutzername", SwingConstants.RIGHT));
        label.add(new JLabel("Passwort", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField usernameField = new JTextField();
        controls.add(usernameField);
        JPasswordField passwordField = new JPasswordField();
        controls.add(passwordField);
        panel.add(controls, BorderLayout.CENTER);

        int loginTries=0;
        while ( true )
        {
            JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
            username = usernameField.getText();

            try
            {
                loginTries++;
                DbConnection.initDbConnection(username, passwordField.getPassword());
                // Falls erfolgreich (Keine Excepiton, Login beenden und Dialog initialisieren)
                break;
            }
            catch (SQLException e)
            {
                //Dem Nutzer zeigen dass der Login nicht erfolgreich war
                JOptionPane.showMessageDialog(new JFrame(),
                        "Login fehlgeschlagen",
                        "Fehler beim Login",
                        JOptionPane.ERROR_MESSAGE);

                if (loginTries >= MAX_LOGIN_TRIES)
                {
                    System.exit(0);
                }
            }
        }
    }

    private void initUI()
    {
        setTitle("Freiwilligen Verwaltung");
        setSize(1000, 500);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        add(cardPanel);

        //Add view for persons
        VolunteerDataView volunteerDataView = new VolunteerDataView();
        cardPanel.add(volunteerDataView.getView(), "Personen");

        //Add view for blue panel
        JPanel startPageView = new JPanel();
        startPageView.setBackground(Color.BLUE);
        cardPanel.add(startPageView, "Startseite");

        //Allow Main Menu to switch between menus
        MainMenu menuBar = new MainMenu(cardLayout, cardPanel);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Client();
            ex.setVisible(true);
        });
    }
}
