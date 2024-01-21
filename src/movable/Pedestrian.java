package movable;

import immovable.Crosswalk;
import immovable.Sidewalk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pedestrian extends MovingObjects implements ActionListener, Runnable {

    private int pedestrianSize = 10;
    private Graphics2D buffer;
    private int delay = 70;
    private ArrayList<Pedestrian> pedestrians;
    private ArrayList<Sidewalk> sidewalks;
    private ArrayList<Crosswalk> crosswalks;
    private boolean onSidewalk = false;
    private boolean onCrosswalk = false;
    private Sidewalk sidewalk;
    private Crosswalk crosswalk;
    private int previousX, previousY;
    private int bounds = 900 + pedestrianSize;

    public Pedestrian(int x, int y, int xSpeed, int ySpeed, Graphics2D buffer, ArrayList<Pedestrian> pedestrians, ArrayList<Sidewalk> sidewalks, ArrayList<Crosswalk> crosswalks)
    {
        super(x, y, xSpeed, ySpeed);
        this.buffer = buffer;
        this.pedestrians = pedestrians;
        this.sidewalks = sidewalks;
        this.crosswalks = crosswalks;
    }

    public void move()
    {
        onSidewalk = false;
        for( Sidewalk i : this.sidewalks)
        {
            if(i.whichSidewalk(this.sidewalks, this.x, this.y) == true)
            {
                sidewalk = i;
                onSidewalk = true;
            }
        }
        if( onSidewalk == true)
        {
            if(sidewalk.getxDirection() != 0 || sidewalk.getyDirection() != 0)
            {
                previousX = sidewalk.getxDirection();
                previousY = sidewalk.getyDirection();
                this.x += sidewalk.getxDirection();
                this.y += sidewalk.getyDirection();
            }
            else
            {
                crosswalk = null;
                for( Crosswalk c : crosswalks)
                {
                    if(c.contains(this.x, this.y) )
                    {
                        crosswalk = c;
                    }
                }
                if(crosswalk != null && onCrosswalk == false)
                {
                    //swiatla
                    if(!crosswalk.getLight1().isOn() )
                    {
                        return;
                    }
                    else
                    {
                        onCrosswalk = true;
                        this.x += previousX;
                        this.y += previousY;
                    }
                }
                else
                {
                    this.x += previousX;
                    this.y += previousY;
                }

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
        while(this.x <= bounds && this.y <= bounds && this.x >= 0 - pedestrianSize && this.y >= 0 - pedestrianSize )
        {
            move();
            try{
                Thread.sleep(delay);
            }
            catch (InterruptedException e)
            {
                System.out.println(Thread.currentThread().isInterrupted());
            }
        }
        System.out.println("out of bounds");
        pedestrians.remove(this);

    }


    public boolean Collision( int x, int y, int xSpeed, int ySpeed)
    {
        if(x + xSpeed >= this.x && x <= this.x + pedestrianSize && y  + ySpeed>= this.y && y <= this.y + pedestrianSize)
            {
                return true;
            }

        return false;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.buffer.setColor(Color.GREEN);
        this.buffer.fillRect(this.x, this.y, pedestrianSize, pedestrianSize);

    }

}
