package Traffic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Intersection {

    private int width, height;
    private int x, y;
    private Graphics2D buffer;
    ArrayList<Lane> laneList = new ArrayList<>();
    ArrayList<Sidewalk> sidewalkList = new ArrayList<>();

    ArrayList<Crosswalk> crosswalkList = new ArrayList<>();

    private Timer timer;

    public Intersection(int x, int y, int width, int height, Graphics2D buffer) {
        this.buffer = buffer;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        //Adding Lanes
        // Above the Intersection
        laneList.add(new Lane(x, y - height, width / 4, height, 0, -1));
        laneList.add(new Lane(x + (width / 4), y - height, width / 4, height, 0, -1));
        laneList.add(new Lane(x + width / 2, y - height, width / 2, height, 0, 1));

        //On the right
        laneList.add(new Lane(x + width, y, width, height / 4, -1, 0));
        laneList.add(new Lane(x + width, y + (height / 4), width, height / 4, -1, 0));
        laneList.add(new Lane(x + width, y +(height / 2), width, height / 2, 1, 0));

        // Below the intersection
        laneList.add(new Lane(x + (height * 3 / 4), y + height, width / 4, height, 0, 1));
        laneList.add(new Lane(x + (width / 2), y + height, width / 4, height, 0, 1));
        laneList.add(new Lane(x, y + height, width / 2, height, 0, -1));

        //On the left of the intersection
        laneList.add(new Lane(x - width, y + (height * 3 / 4), width, height / 4, 1, 0));
        laneList.add(new Lane(x - width, y + height / 2, width, height / 4, 1, 0));
        laneList.add(new Lane(x - width, y, width, height / 2, -1, 0));


        //Adding Sidewalks
        //Upper left
        sidewalkList.add(new Sidewalk(x - width, y - height / 4, width * 3 / 4, height / 4, -1, 0));
        sidewalkList.add(new Sidewalk(x - width / 4, y - height / 4, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x - width / 4, y - height, width / 4, height * 3 / 4, 0, -1));

        //Upper right
        sidewalkList.add(new Sidewalk(x + width, y - height, width / 4, height * 3 / 4, 0, 1));
        sidewalkList.add(new Sidewalk(x + width, y - height / 4, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x + width * 5 / 4, y - height / 4, width * 3 / 4, height / 4, -1, 0));

        //Down right
        sidewalkList.add(new Sidewalk(x + width * 5 / 4, y + height, width * 3 / 4, height / 4, -1, 0));
        sidewalkList.add(new Sidewalk(x + width, y + height, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x + width, y + height * 5 / 4, width / 4, height * 3 / 4, 0, -1));

        //Down left
        sidewalkList.add(new Sidewalk(x - width / 4, y + height * 5 / 4, width / 4, height * 3 / 4, 0, -1));
        sidewalkList.add(new Sidewalk(x - width / 4, y + height, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x - width, y + height, width * 3 / 4, height / 4, 1, 0));


        //Adding Crosswalks and initializing needed variables
        crosswalkList.add(new Crosswalk(x, y, width, height, 1));
        crosswalkList.add(new Crosswalk(x, y, width, height, 2));
        crosswalkList.add(new Crosswalk(x, y, width, height, 3));
        crosswalkList.add(new Crosswalk(x, y, width, height, 4));

    }

    protected void draw(Graphics g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y, this.width, this.height);
        for( Lane i : laneList)
        {
            i.draw(g2d);
        }
        for(Sidewalk i : sidewalkList)
        {
            i.draw(buffer);
        }
        for(Crosswalk i : crosswalkList)
        {
            i.draw(buffer);
        }

    }
}

