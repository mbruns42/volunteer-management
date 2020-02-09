package client;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame
{
    public JPanel dataPanel;
    public JTable volunteers;

    public Client()
    {
        initUI();
    }

    private void initUI()
    {
        var menuBar = new MainMenu();
        setJMenuBar(menuBar);

        setTitle("Freiwilligen Verwaltung");
        setSize(500, 300);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            var ex = new Client();
            ex.setVisible(true);
        });
    }
}
