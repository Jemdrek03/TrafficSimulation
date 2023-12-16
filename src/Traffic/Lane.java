package Traffic;

import javax.swing.*;
import java.awt.*;

public class Lane extends JPanel {

    private int X, Y, width, height,xDirection, yDirection;
    public Lane(int x, int y, int width, int height, int xDirection, int yDirection)
    {
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;
        this.xDirection = xDirection;
        this.yDirection = yDirection;

    }

    protected void draw(Graphics g2d)
    {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(this.X, this.Y, this.width, this.height);
        g2d.setColor(Color.WHITE);
        if(this.width > this.height)
        {
            g2d.drawLine(this.X, this.Y, this.X + width, this.Y);
            g2d.drawLine(this.X, this.Y + this.height, this.X + width, this.Y + this.height);
        }
        else
        {
            g2d.drawLine(this.X, this.Y, this.X, this.Y + this.height);
            g2d.drawLine(this.X + width, this.Y, this.X + width, this.Y + this.height);
        }
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }


}
