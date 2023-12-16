package Traffic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kanwa extends JPanel implements ActionListener {

    Image image;
    Graphics2D buffer;
    Graphics2D device;

    private int x, y, height, width;
    private Timer timer ;
    private Intersection i1;
    public Kanwa(int x, int y, int width, int height)
    {
        setBounds(x, y, width, height);
        setBackground(Color.WHITE);
        setLayout(null);
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        i1.draw(buffer);
        device.drawImage(image,0,0,null);
        buffer.clearRect(0,0,900,900);
    }
    public void initialize()
    {
        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        timer = new Timer(100, this);
        i1 = new Intersection(width/3, height/3, width/3, height/3, buffer);
        timer.start();
    }
}
