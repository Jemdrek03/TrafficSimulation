package immovable;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Intersection implements ActionListener{

    private int width, height;
    private int correction = 300/8;
    private int x, y;
    private Graphics2D buffer;
    ArrayList<Lane> laneList = new ArrayList<>();
    ArrayList<Sidewalk> sidewalkList = new ArrayList<>();

    public ArrayList<Crosswalk> crosswalkList = new ArrayList<>();

    public ArrayList<Crosswalk> getCrosswalkList() {
        return crosswalkList;
    }

    public ArrayList<Sidewalk> getSidewalkList() {
        return sidewalkList;
    }

    public ArrayList<Lane> getLaneList() {
        return laneList;
    }

    private Timer timer;

    public Intersection(int x, int y, int width, int height, Graphics2D buffer) {
        this.buffer = buffer;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        int carSpeed = 10;
        //Adding Lanes
        // Above the Intersection
        laneList.add(new Lane(x, y - height, width / 4, height, 0, carSpeed, new Lights(x+(width/8), y, 10, false),1));
        laneList.add(new Lane(x + (width / 4), y - height, width / 4, height, 0, carSpeed,new Lights(x+(width*3/8), y, 10, false),2));
        laneList.add(new Lane(x + width / 2, y - height, width / 2, height, 0, -carSpeed,new Lights(x+(width*3/4), y, 10, false),3));

        //On the right
        laneList.add(new Lane(x + width, y, width, height / 4, -carSpeed, 0,new Lights(x+width-10, y + (height/8), 10, false),1));
        laneList.add(new Lane(x + width, y + (height / 4), width, height / 4, -carSpeed, 0,new Lights(x+width-10, y + (height*3/8), 10, false),2));
        laneList.add(new Lane(x + width, y +(height / 2), width, height / 2, carSpeed, 0,new Lights(x+width-10, y + (height*3/4), 10, false),3));

        // Below the intersection
        laneList.add(new Lane(x + (height * 3 / 4), y + height, width / 4, height, 0, -carSpeed,new Lights(x + (width*7/8), y+ height-10, 10, false),1));
        laneList.add(new Lane(x + (width / 2), y + height, width / 4, height, 0, -carSpeed,new Lights(x + (width*5/8), y + height-10, 10, false),2));
        laneList.add(new Lane(x, y + height, width / 2, height, 0, +carSpeed,new Lights(x+(width/4), y + height-10, 10, false),3));

        //On the left of the intersection
        laneList.add(new Lane(x - width, y + (height * 3 / 4), width, height / 4, carSpeed, 0,new Lights(x, y + (height*7/8), 10, false),1));
        laneList.add(new Lane(x - width, y + height / 2, width, height / 4, carSpeed, 0,new Lights(x, y + (height*5/8), 10, false),2));
        laneList.add(new Lane(x - width, y, width, height / 2, -carSpeed, 0,new Lights(x, y + (height/4), 10, false),3));


        //Adding Sidewalks
        int pedestrians = 3;
        //Upper left
        sidewalkList.add(new Sidewalk(x - width, y - height / 4, width * 3 / 4, height / 4, -pedestrians, 0));
        sidewalkList.add(new Sidewalk(x - width / 4, y - height / 4, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x - width / 4, y - height, width / 4, height * 3 / 4, 0, pedestrians));

        //Upper right
        sidewalkList.add(new Sidewalk(x + width, y - height, width / 4, height * 3 / 4, 0, -pedestrians));
        sidewalkList.add(new Sidewalk(x + width, y - height / 4, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x + width * 5 / 4, y - height / 4, width * 3 / 4, height / 4, -pedestrians, 0));

        //Down right
        sidewalkList.add(new Sidewalk(x + width * 5 / 4, y + height, width * 3 / 4, height / 4, pedestrians, 0));
        sidewalkList.add(new Sidewalk(x + width, y + height, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x + width, y + height * 5 / 4, width / 4, height * 3 / 4, 0, -pedestrians));

        //Down left
        sidewalkList.add(new Sidewalk(x - width / 4, y + height * 5 / 4, width / 4, height * 3 / 4, 0, pedestrians));
        sidewalkList.add(new Sidewalk(x - width / 4, y + height, width / 4, height / 4, 0, 0));
        sidewalkList.add(new Sidewalk(x - width, y + height, width * 3 / 4, height / 4, pedestrians, 0));


        //Adding Crosswalks and initializing needed variables
        crosswalkList.add(new Crosswalk(x, y, width, height, 1, new Lights(x-correction, y-10, 10, false), new Lights(x-correction, (y+height) + 5, 10, false)));
        crosswalkList.add(new Crosswalk(x, y, width, height, 2, new Lights(x-10, y - correction, 10, false), new Lights(x+ width + 5, y-correction, 10, false)));
        crosswalkList.add(new Crosswalk(x, y, width, height, 3, new Lights(x+ width + correction, y - 10, 10, false), new Lights(x+ width + correction, y + height + 5, 10, false)));
        crosswalkList.add(new Crosswalk(x, y, width, height, 4, new Lights(x+ width + 5, y + height + correction, 10, false), new Lights(x-10, y + height + correction, 10, false)));

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.buffer.setColor(Color.BLACK);
        this.buffer.fillRect(x, y, this.width, this.height);
        for( Lane i : laneList)
        {
            i.draw(this.buffer);
        }
        for(Sidewalk i : sidewalkList)
        {
            i.draw(this.buffer);
        }
        for(Crosswalk i : crosswalkList)
        {
            i.draw(this.buffer);
        }
    }
}

