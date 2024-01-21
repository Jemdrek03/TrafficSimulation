package immovable;

import java.awt.*;

public class Lights {

    private int radius, x, y;
    public boolean isOn;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isOn() {
        return isOn;
    }

    public Lights(int x, int y, int radius, boolean isOn)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isOn = isOn;
    }

    public void draw(Graphics2D g2d)
    {
        if( this.isOn == true)
        {
            g2d.setColor(Color.GREEN);
        }
        else
        {
            g2d.setColor(Color.RED);
        }
        g2d.fillOval(this.x, this.y, this.radius, this.radius);
    }
}
