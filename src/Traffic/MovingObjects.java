package Traffic;

public abstract class MovingObjects {

    private int x, y;
    private int xSpeed, ySpeed;

    public MovingObjects(int x, int y, int xSpeed, int ySpeed )
    {
//        super(x, y);

    }

    public void move()
    {
        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }
}
