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
    private ArrayList<Crosswalk> crosswalks;
    private boolean onSidewalk = false;
    private Sidewalk sidewalk;
    private Crosswalk crosswalk;
    private int previousX, previousY;

    public Pedestrian(int x, int y, int xSpeed, int ySpeed, Graphics2D buffer, ArrayList<Pedestrian> pedestrians, ArrayList<Sidewalk> sidewalks, ArrayList<Crosswalk> crosswalks)
    {
        super(x, y, xSpeed, ySpeed);
//        this.x = x;
//        this.y = y;
//        this.xSpeed = xSpeed;
//        this.ySpeed = ySpeed;
        this.buffer = buffer;
        this.pedestrians = pedestrians;
        this.sidewalks = sidewalks;
        this.crosswalks = crosswalks;

    }

    public void move()
    {
        //check if collision == false
        if(Collision(pedestrians,x,y, xSpeed, ySpeed) == true)
        {
            //sleep
        }
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
            if(sidewalk.getxDirection() != 0 && sidewalk.getyDirection() != 0)
            {
                previousX = sidewalk.getxDirection();
                previousY = sidewalk.getyDirection();
                this.x += sidewalk.getxDirection();
                this.y += sidewalk.getyDirection();
            }
            else
            {
                if(crosswalk.contains(this.x, this.y, this.crosswalks) != null)
                {
                    //swiatla
                    crosswalk = crosswalk.contains(this.x, this.y, this.crosswalks);
                    if(crosswalk.getLight1().isOn() == false)
                    {

                    }
                    else
                    {
                        this.x += previousX;
                        this.y += previousY;
                    }
                    //??
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
        while(x <= 900 & y <= 900 )
        {
            move();
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


    public boolean Collision(ArrayList<Pedestrian> pedestrians, int x, int y, int xSpeed, int ySpeed)
    {
        for( Pedestrian p : pedestrians)
        {
            if(x + xSpeed >= p.getX() && x <= p.getX() + pedestrianSize && y  + ySpeed>= p.getY() && y <= p.getY() + pedestrianSize)
            {
                return true;
            }
        }
        return false;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.buffer.setColor(Color.PINK);
        this.buffer.fillRect(this.x, this.y, pedestrianSize, pedestrianSize);

    }

}
