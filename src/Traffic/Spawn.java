package Traffic;

import java.util.Random;

public class Spawn {
    private int x, y, object;
    private int xtmp;
    public Spawn( int object)
    {
        this.object = object;
        if(this.object == 1)
        {
            this.xtmp = new Random().nextInt(4);
            switch (this.xtmp)
            {
                case 0:
                    this.x = 300-37;
                    this.y = 15;
                    break;
                case 1:
                    this.x = 900-15;
                    this.y = 300-37;
                    break;
                case 2:
                    this.x = 600+37;
                    this.y = 900-20;
                    break;
                case 3:
                    this.x = 15;
                    this.y = 600+37;
                    break;

            }
        }
        else
        {
            this.xtmp = new Random().nextInt(8);
            switch (this.xtmp)
            {
                case 0:
                    this.x = 300+5;
                    this.y = 15;
                    break;
                case 1:
                    this.x = 300+80;
                    this.y = 15;
                    break;
                case 2:
                    this.x = 900-50;
                    this.y = 300+20;
                    break;
                case 3:
                    this.x = 900-50;
                    this.y = 300+85;
                    break;
                case 4:
                    this.x = 600-40- 20;
                    this.y = 900-50;
                    break;
                case 5:
                    this.x = 600-150 + 20;
                    this.y = 900-50;
                    break;
                case 6:
                    this.x = 20;
                    this.y = 600-40;
                    break;
                case 7:
                    this.x = 20;
                    this.y = 600-140;
                    break;

            }
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
