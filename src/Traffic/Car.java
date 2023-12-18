package Traffic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Car extends MovingObjects implements ActionListener, Runnable {

    private int carsize = 300/8;
    private Graphics2D buffer;
    private ArrayList<Lane> lanes;
    private ArrayList<Car> cars;
    private ArrayList<Pedestrian> pedestrians;
    private ArrayList<Crosswalk> crosswalks;
    private Lane lane;
    private boolean onLane = false;
    private int bounds = 900 + carsize;
    private int delay = 70;
    private int previousX, previousY;
    private int Caser = 0;
    private int rs = 0;
    private int distanceForRightTurn = 30;
    private int distancedrivenr = 0;
    private int distanceForLeftTurn = 150 + 80;
    private int distancedrivenl = 0;


    public Car(int x, int y, int xSpeed, int ySpeed, Graphics2D buffer, ArrayList<Lane> lanes, ArrayList<Car> cars, ArrayList<Pedestrian> pedestrians, ArrayList<Crosswalk> crosswalks)
    {
        super(x, y, xSpeed, ySpeed);
        this.buffer = buffer;
        this.lanes = lanes;
        this.cars = cars;
        this.pedestrians = pedestrians;
        this.crosswalks = crosswalks;


    }


    public boolean Collision(ArrayList<Car> cars, int x, int y, int xSpeed, int ySpeed)
    {
        for( Car p : cars)
        {
            if(p != this && x + 2*xSpeed >= p.getX() && x <= p.getX() + carsize&& y +  2*ySpeed>= p.getY() && y <= p.getY() + carsize)
            {
                return true;
            }
        }
        return false;
    }

    public void run()
    {
        while(x <= bounds && y <= bounds && x >= 0 - carsize && y >= 0 - carsize)
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
        cars.remove(this);
    }

    public void move()
    {
        for( Pedestrian p : pedestrians)
        {
            if(p.Collision(this.x, this.y, this.getxSpeed(), this.getySpeed()) == true)
            {
                return;
            }
        }
        if( Collision(this.cars, this.x, this.y, getxSpeed(), getySpeed() ) == true)
        {
            return;
        }
        onLane = false;
        for( Lane l : this.lanes)
        {
            if(l.whichLane(lanes,this.x,this.y) != null)
            {
                lane = l;
                onLane = true;
                break;
            }
        }
        if( onLane == true)
        {
            if(lane.getLight().isOn == false)
            {

                    if(containsS(this.x, this.y, lane.getxDirection(), lane.getyDirection()) == true)
                    {
                        return;
                    }
                    else {
                        previousX = lane.getxDirection();
                        previousY = lane.getyDirection();
                        this.x += previousX;
                        this.y += previousY;
                    }

            }
            else
            {
                previousX = lane.getxDirection();
                previousY = lane.getyDirection();
                this.x += previousX;
                this.y += previousY;
            }


        }
        else
        {
            if(Caser == 0)
            {
                Caser = lane.getDirection();
            }
            else
            {
                if(Caser == 1 && rs == 0)
                {
                    rs = new Random().nextInt(2) + 1;
                }
                //zabawa
                if(Caser == 1 && rs == 1)
                {
                    this.x += previousX;
                    this.y += previousY;
                }
                else if( Caser == 1 && rs == 2)
                {
                    if(distancedrivenr <= distanceForRightTurn)
                    {
                        this.x += previousX;
                        this.y += previousY;
                        distancedrivenr += 10;
                    }
                    else
                    {
                        if(previousX > 0)
                        {
                            this.y += 10;
                        }
                        else if( previousX < 0 )
                        {
                            this.y -= 10;
                        }
                        else if( previousY > 0)
                        {
                            this.x -= 10;
                        }
                        else
                        {
                            this.x += 10;
                        }
                    }
                }
                else if( Caser == 2)
                {
                    if(distancedrivenl <= distanceForLeftTurn)
                    {
                        this.x += previousX;
                        this.y += previousY;
                        distancedrivenl += 10;
                    }
                    else
                    {
                        if( previousX > 0 )
                        {
                            this.y -= 10;
                        }
                        else if( previousX < 0 )
                        {
                            this.y += 10;
                        }
                        else if( previousY > 0 )
                        {
                            this.x += 10;
                        }
                        else
                        {
                            this.x -= 10;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.buffer.setColor(Color.BLUE);
        this.buffer.fillRect(this.x, this.y, carsize, carsize);
    }

    private boolean containsS(int x, int y, int previousX, int previousY)
    {
        if(previousX > 0)
        {
            if( x + 37 >= 300 - 300/4 && x <= 300 )
            {
                return true;
            }
        }
        else if( previousX < 0)
        {
            if( x >= 600 && x - 37 <= 600 + 300/4)
            {
                return true;
            }
        }
        else if( previousY > 0)
        {
            if( y + 37>= 300 - 300/4 && y <= 300)
            {
                return true;
            }
        }
        else if( previousY < 0)
        {
            if( y >= 600 && y- 37 <=600 + 300/4 )
            {
                return true;
            }
        }
        return false;

    }
}
