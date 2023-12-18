package Traffic;

import java.awt.*;
import java.util.ArrayList;

public class Car extends MovingObjects{

    private int carsize = 300/8;
    Pedestrian pedestrian;
    Graphics2D buffer;
    ArrayList<Lane> lanes;
    ArrayList<Car> cars;

    public Car(int x, int y, int xSpeed, int ySpeed, Graphics2D buffer, ArrayList<Lane> lanes, ArrayList<Car> cars)
    {
        super(x, y, xSpeed, ySpeed);
        this.buffer = buffer;
        this.lanes = lanes;
        this.cars = cars;


    }

    public boolean Collision(ArrayList<Car> cars, int x, int y, int xSpeed, int ySpeed)
    {
        for( Car p : cars)
        {
            if(x + 5 * xSpeed >= p.getX() && x <= p.getX() + carsize && y + 5 * ySpeed>= p.getY() && y <= p.getY() + carsize)
            {
                return true;
            }
        }
        return false;
    }
}
