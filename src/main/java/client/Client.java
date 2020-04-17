package client;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame
{

    private CardLayout cardLayout;

    public Client()
    {
        initUI();
    }

    private void initUI()
    {
        setTitle("Freiwilligen Verwaltung");
        setSize(1000, 500);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
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
