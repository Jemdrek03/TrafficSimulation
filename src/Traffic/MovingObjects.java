package Traffic;

public abstract class MovingObjects {


    private int x, y;
    private int xSpeed, ySpeed;

    public MovingObjects() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public MovingObjects(int x, int y, int xSpeed, int ySpeed )
    {
        super();
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

}
