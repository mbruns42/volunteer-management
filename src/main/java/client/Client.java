package client;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame
{
    public JPanel dataPanel;
    private JScrollBar scrollBar;
    JTable volunteers;

    public Client()
    {
        initUI();
    }

    private void initUI()
    {
        JMenuBar menuBar = new MainMenu();
        setJMenuBar(menuBar);

        dataPanel  = new VolunteerDataPanel(volunteers);

        setTitle("Freiwilligen Verwaltung");
        setSize(500, 300);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Client();
            ex.setVisible(true);
        });
    }
}
