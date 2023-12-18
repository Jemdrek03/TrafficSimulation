package Traffic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Kanwa extends JPanel implements ActionListener {

    private Image image;
    private Graphics2D buffer;
    private Graphics2D device;
    private ArrayList<Pedestrian> pedestrians = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Sidewalk> sidewalks;
    private ArrayList<Crosswalk> crosswalks;
    private Pedestrian pedestrian;
    private Car car;
    private int counter = 0;

    private int x, y, height, width;
    private Timer timer ;
    private Intersection i1;
    public Kanwa(int x, int y, int width, int height)
    {
        setBounds(x, y, width, height);
        setBackground(Color.WHITE);
        setLayout(null);
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
  //     timer = new Timer(100, this);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//        i1.draw(buffer);

//        for( Pedestrian p : pedestrians)
//        {
//            timer.addActionListener(p);
//            new Thread(pedestrian).start();
//
//        }
        device.drawImage(image,0,0,null);
        buffer.clearRect(0,0,900,900);
    }
    public void initialize()
    {
        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        timer = new Timer(30, this);
        i1 = new Intersection(width/3, height/3, width/3, height/3, buffer);
//        pedestrian = new Pedestrian(300-37, 40, 0, 0, buffer, pedestrians, i1.getSidewalkList(), i1.getCrosswalkList() );
//        pedestrians.add(pedestrian);
//        timer.addActionListener(pedestrian);
//        new Thread(pedestrian).start();
//        car = new Car(300+37, 50, 0, 0, buffer, i1.getLaneList(), cars, pedestrians, i1.crosswalkList);
//        cars.add(car);
//        timer.addActionListener(car);
//        new Thread(car).start();
//        car = new Car(300+37+38+15, 50, 0, 0, buffer, i1.getLaneList(), cars, pedestrians, i1.crosswalkList);
//        cars.add(car);
//        timer.addActionListener(car);
//        new Thread(car).start();
//        pedestrian = new Pedestrian(750, 300-37, 0, 0, buffer, pedestrians, i1.getSidewalkList(), i1.getCrosswalkList() );
//        pedestrians.add(pedestrian);
//        timer.addActionListener(pedestrian);
//        new Thread(pedestrian).start();
        timer.addActionListener(i1);
        timer.start();

        Timer lights = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for( int i = 0; i < i1.getLaneList().size(); i++)
                {
                    Lane l = i1.getLaneList().get(i);
                    if( i == counter || i == counter+1)
                    {
                        l.getLight().setOn(true);

                    }
                    else
                    {
                        l.getLight().setOn(false);
                    }
                }
                boolean turn = false;
                if(counter == 0 || counter == 6)
                    turn = false;
                else if (counter == 3 || counter == 9)
                    turn = true;
                i1.crosswalkList.get(0).getLight1().setOn(!turn);
                i1.crosswalkList.get(0).getLight2().setOn(!turn);
                i1.crosswalkList.get(2).getLight1().setOn(!turn);
                i1.crosswalkList.get(2).getLight2().setOn(!turn);
                i1.crosswalkList.get(1).getLight1().setOn(!turn);
                i1.crosswalkList.get(1).getLight2().setOn(!turn);
                i1.crosswalkList.get(3).getLight1().setOn(!turn);
                i1.crosswalkList.get(3).getLight2().setOn(!turn);
                counter = (counter + 3) % 12;
            }
        });
        lights.start();
        Timer timer2 = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timer.removeActionListener(i1);
                Spawn car = new Spawn(2);
                Spawn pedestrian = new Spawn(1);
                Car newCar = new Car(car.getX(),car.getY(),0, 0, buffer, i1.getLaneList(), cars, pedestrians, i1.crosswalkList);
                cars.add(newCar);
                timer.addActionListener(newCar);
                new Thread(newCar).start();
                Pedestrian newPedestrian = new Pedestrian(pedestrian.getX(),pedestrian.getY(),0, 0, buffer, pedestrians, i1.getSidewalkList(), i1.getCrosswalkList() );
                pedestrians.add(newPedestrian);
                timer.addActionListener(newPedestrian);
                new Thread(newPedestrian).start();
                timer.addActionListener(i1);
            }
        });
        timer2.start();
    }
}
