package Traffic;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel = new JPanel();
    private int height = 950;
    private int width = 900;


    public MainFrame(String title) throws HeadlessException{
        setTitle(title);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setSize(width, height);
        mainPanel.setLayout(null);
        mainPanel.setSize(width, height);



        Kanwa kanwa = new Kanwa(0, 0, width, height-50);
        mainPanel.add(kanwa);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                kanwa.initialize();
            }
        });
        setResizable(false);
    }



}
