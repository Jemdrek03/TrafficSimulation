package app;

import drawable.MainFrame;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame("Intersection");
                frame.setVisible(true);
            }
        });
    }
}

