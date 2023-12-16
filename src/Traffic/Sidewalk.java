package Traffic;

import java.awt.*;

public class Sidewalk {

    private int X, Y, height, width;
    public Sidewalk(int x, int y, int width, int height, int xDirection, int YDirection)
    {
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;
    }

    protected void draw(Graphics g2d)
    {
        g2d.setColor(Color.GRAY);
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
}
