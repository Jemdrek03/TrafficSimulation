package Traffic;

import java.awt.*;
import java.util.ArrayList;

public class Sidewalk {

    private int X, Y, height, width, xDirection, yDirection;
    public Sidewalk(int x, int y, int width, int height, int xDirection, int YDirection)
    {
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;
        this.xDirection = xDirection;
        this.yDirection = YDirection;
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

    public Sidewalk whichSidewalk(ArrayList<Sidewalk> sidewalks, int x, int y)
    {
        for(Sidewalk i : sidewalks)
        {
            if(x >= this.X && x <= this.X + this.width && y >= this.Y && y <= this.Y + this.height)
            {
                return i;//
            }
        }
        return null;
    }

}
