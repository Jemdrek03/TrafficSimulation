package Traffic;

import javax.swing.*;
import java.awt.*;

public class Crosswalk extends JPanel {
    private int X, Y, width, height, Case ;
    public Crosswalk(int x, int y, int width, int height, int Case){
        this.X = x;
        this.Y = y;
        this.height = height;
        this.width = width;
        this.Case = Case;
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
    }
}
