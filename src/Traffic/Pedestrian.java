package Traffic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Pedestrian extends MovingObjects implements ActionListener {

    private int pedestrianSize = 5;
    private Graphics2D buffer;
    private int x, y, xSpeed, ySpeed;
    private int maxSize = 900;

    private ArrayList<Pedestrian> pedestrians;
    private ArrayList<Sidewalk> sidewalks;
    private boolean onSidewalk = false;
    private Sidewalk sidewalk;
    private Crosswalk crosswalk;
    private int previousX, previousY;

    public Pedestrian(int x, int y, int xSpeed, int ySpeed, Graphics2D buffer, ArrayList<Pedestrian> pedestrians, ArrayList<Sidewalk> sidewalks)
    {
        super(x, y, xSpeed, ySpeed);
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.buffer = buffer;
        this.pedestrians = pedestrians;
        this.sidewalks = sidewalks;

    }

    public int nextStepX(int x,int previousXSpeed)
    {
        return x + previousXSpeed;
    }

    public int nextStepY( int y, int previousYSpeed)
    {
        return y + previousYSpeed;
    }


    public void move()
    {
        for( Sidewalk i : sidewalks)
        {
            if(i.whichSidewalk(sidewalks, this.x, this.y) == true)
            {
                sidewalk = i;
                onSidewalk = true;
            }
        }
        if( onSidewalk == true)
        {
            if(sidewalk.getxDirection() != 0 && sidewalk.getyDirection() != 0)
            {
                previousX = sidewalk.getxDirection();
                previousY = sidewalk.getyDirection();
                this.x += sidewalk.getxDirection();
                this.y += sidewalk.getyDirection();
            }
            else
            {
                if(crosswalk.contains() != null)
            }
        }
        else
        {
            this.x += previousX;
            this.y += previousY;
        }
    }

    public void run()
    {
        while(x <= 900 & y <= 900 )
        {
//            move();
//            try{
//                Thread.sleep(delay);
//            }
//            catch (InterruptedException e)
//            {
//                System.out.println(Thread.currentThread().isInterrupted());
//            }
        }
        pedestrians.remove(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        draw(buffer);
    }

    protected void draw(Graphics2D g2d)
    {
        g2d.setColor(Color.PINK);
        g2d.fillRect(this.x, this.y, pedestrianSize, pedestrianSize);

    }
}
