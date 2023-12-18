package Traffic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Crosswalk extends JPanel {
    private int X, Y, width, height, Case ;
    private ArrayList<Lights> lights;
    private Lights light1, light2;

    public Lights getLight1() {
        return light1;
    }

    public void setLight1(Lights light1) {
        this.light1 = light1;
    }

    public Lights getLight2() {
        return light2;
    }

    public void setLight2(Lights light2) {
        this.light2 = light2;
    }

    public Crosswalk(int x, int y, int width, int height, int Case, Lights light1, Lights light2){
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;
        this.Case = Case;
//        lights.add(light1);
//        lights.add(light2);
        this.light1 = light1;
        this.light2 = light2;
    }

    public boolean contains(int x, int y )
    {
            if( (x + 1 >= 300 && x <=450) || (x-1 <= 600 && x >= 450) || (y + 1 >= 300 && y <= 400 ) || (y - 1 <= 600 && y >= 450))
            {
                return true;
            }

        return false;
    }

    protected void draw(Graphics2D g2d)
    {
        int xTMP = this.X;
        int yTMP = this.Y;
        int CSHeight = 34;
        int CSWidth = this.width/4;
        int Correction = CSHeight + 4;
        g2d.setColor(Color.WHITE);
        if( Case == 1)
        {
            for( int i = 0; i < 8; i++)
            {
                g2d.fillRect(xTMP-CSWidth,yTMP,CSWidth,CSHeight);
                yTMP += Correction;

            }
        }
        else if( Case == 2)
        {
            for( int i = 0; i < 8; i++)
            {
                g2d.fillRect(xTMP,yTMP-CSWidth, CSHeight, CSWidth);
                xTMP += Correction;
            }
        }
        else if( Case == 3)
        {
            for( int i = 0; i < 8; i++)
            {
                g2d.fillRect(xTMP+this.width,yTMP,CSWidth,CSHeight);
                yTMP += Correction;
            }
        }
        else
        {
            for( int i = 0; i < 8; i++)
            {
                g2d.fillRect(xTMP,yTMP+this.height,CSHeight,CSWidth);
                xTMP += Correction;
            }
        }
        if(this.light1 != null)
        {
            this.light1.draw(g2d);
        }
        if(this.light2 != null)
        {
            this.light2.draw(g2d);
        }
    }
}
