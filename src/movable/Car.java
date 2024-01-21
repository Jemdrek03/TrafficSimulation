package movable;

import immovable.Crosswalk;
import immovable.Lane;

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
        for(Car p : cars)
        {
            if(p != this)
            {
                if(xSpeed > 0)
                {
                    if( x  + carsize +xSpeed >= p.getX() && x + xSpeed + carsize <= p.getX() + carsize && y == p.getY())
                    {
                        return true;
                    }
                }
                else if( xSpeed < 0)
                {
                    if(x + xSpeed >= p.getX() && x + xSpeed <= p.getX() + carsize && y == p.getY())
                    {
                        return true;
                    }
                }
                else if( ySpeed > 0)
                {
                    if(y + ySpeed + carsize >= p.getY() && y + ySpeed + carsize <= p.getY() + carsize && x == p.getX())
                    {
                        return true;
                    }
                }
                else
                {
                    if(y + ySpeed >= p.getY() && y + ySpeed <= p.getY() + carsize && x == p.getX())
                    {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    public boolean CollisionP(ArrayList<Pedestrian> pedestrians, int x, int y, int xSpeed, int ySpeed)
    {
        for(Pedestrian p : pedestrians)
        {
                if(xSpeed > 0)
                {
                    if( x  + carsize + carsize >= p.getX() && x  + carsize <= p.getX()  && y <= p.getY() && y + carsize >= p.getY())
                    {
                        return true;
                    }
                }
                else if( xSpeed < 0)
                {
                    if(x - carsize <= p.getX() && x >= p.getX()  && y <= p.getY() && y + carsize >= p.getY())
                    {
                        return true;
                    }
                }
                else if( ySpeed > 0)
                {
                    if(y + carsize <= p.getY() && y + carsize + carsize >= p.getY()  && x <= p.getX() && x + carsize >= p.getX())
                    {
                        return true;
                    }
                }
                else
                {
                    if(y >= p.getY() && y - carsize <= p.getY() && x <= p.getX() && x + carsize >= p.getX())
                    {
                        return true;
                    }
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
        //collision for some reason doesnt seem to work
        //it takes the opposite direction for some reason too
        //i know actually
        // i have to clear Xspeed/Yspeed after it completes a move in this direction
        //tomorrow tho
        //XDD
        //It took the X/Y coordinates even tho it shoudlnt have, easy x == p.getX() solved the whole problem
        if(Collision(this.cars, this.x, this.y, getxSpeed(), getySpeed()))
        {
            return;
        }
        if(CollisionP(this.pedestrians, this.x, this.y, getxSpeed(), getySpeed()))
        {
            return;
        }
        onLane = false;
        for(Lane l : this.lanes)
        {
            if(l.whichLane(lanes,this.x,this.y) != null)
            {
                lane = l;
                onLane = true;
                break;
            }
        }
        if(onLane)
        {
            Caser = 0;
            if(!lane.getLight().isOn)
            {

                if(containsS(this.x, this.y, lane.getxDirection(), lane.getyDirection()))
                {
                    return;
                }
                else {
                    previousX = lane.getxDirection();
                    previousY = lane.getyDirection();
                    setxSpeed(lane.getxDirection());
                    setySpeed(lane.getyDirection());
                    this.x += previousX;
                    this.y += previousY;
                }

            }
            else
            {
                setxSpeed(lane.getxDirection());
                setySpeed(lane.getyDirection());
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
                    setxSpeed(previousX);
                    setySpeed(previousY);
                    this.x += previousX;
                    this.y += previousY;
                }
                else if( Caser == 1 && rs == 2)
                {
                    if(distancedrivenr <= distanceForRightTurn)
                    {
                        setxSpeed(previousX);
                        setySpeed(previousY);
                        this.x += previousX;
                        this.y += previousY;
                        distancedrivenr += 10;
                    }
                    else
                    {
                        if(previousX > 0)
                        {
                            setySpeed(10);
                            setxSpeed(0);
                            this.y += 10;
                        }
                        else if( previousX < 0 )
                        {
                            setySpeed(-10);
                            setxSpeed(0);
                            this.y -= 10;
                        }
                        else if( previousY > 0)
                        {
                            setxSpeed(-10);
                            setySpeed(0);
                            this.x -= 10;
                        }
                        else
                        {
                            setxSpeed(10);
                            setySpeed(0);
                            this.x += 10;
                        }
                    }
                }
                else if( Caser == 2)
                {
                    if(distancedrivenl <= distanceForLeftTurn)
                    {
                        setxSpeed(previousX);
                        setySpeed(previousY);
                        this.x += previousX;
                        this.y += previousY;
                        distancedrivenl += 10;
                    }
                    else
                    {
                        if( previousX > 0 )
                        {
                            setxSpeed(0);
                            setySpeed(-10);
                            this.y -= 10;
                        }
                        else if( previousX < 0 )
                        {
                            setySpeed(10);
                            setxSpeed(0);
                            this.y += 10;
                        }
                        else if( previousY > 0 )
                        {
                            setxSpeed(10);
                            setySpeed(0);
                            this.x += 10;
                        }
                        else
                        {
                            setxSpeed(-10);
                            setySpeed(0);
                            this.x -= 10;
                        }
                    }
                }
                else
                {
                    setxSpeed(previousX);
                    setySpeed(previousY);
                    this.x += previousX;
                    this.y += previousY;
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