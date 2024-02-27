package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private static final int DELAY = 50;
    private static Timer timer = new Timer(DELAY, new TimerListener());
    private static ArrayList<Car> cars = new ArrayList<>();
    private static CarView frame;
    private static CarController cc;


    private static void init() {
        cc = new CarController();

        Car car1 = new Volvo240(4, Color.black, 100, "Volvo240", new Point(0, 300));
        car1.setImagePath("pics/Volvo240.jpg");
        Car car2 = new Saab95(2, Color.red, 125, "Saab95", new Point(0, 200));
        car2.setImagePath("pics/Saab95.jpg");
        Car car3 = new Scania(2, Color.blue, 100, "Scania", new Point(0, 100));
        car3.setImagePath("pics/Scania.jpg");

        WorkShop<Volvo240> shop = new WorkShop<>(2, new Point(300, 300));
        shop.setImagePath("pics/VolvoBrand.jpg");

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        // Start a new view and send a reference of self
        frame = new CarView("CarSim 1.0", cc);
        for (Car car : cars) {
            cc.addCar(car);
            frame.addCar(car);
        }

        cc.setShop(shop);
        frame.addShop(shop);
    }

    public static void main(String[] args) {

        init();
        // Start the timer
        timer.start();
    }

    private static boolean outOfBounds(Car car) {
        Point pos = car.getPosition();
        int x = (int) pos.getX();
        int y = (int) pos.getY();
        return x < 0 || x > frame.getX() / 2 + frame.getWidth() / 2 || y < 0 || y > frame.getHeight() - 300;
    }

    private static int reboundX(Car car) {
        return (int) Math.min((frame.getX()/2 + frame.getWidth()/2) - 2, Math.max(2, car.getPosition().getX()));
    }

    private static int reboundY(Car car) {
        return (int) Math.min(frame.getHeight() - 302, Math.max(2, car.getPosition().getY()));
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private static class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
                cc.update();
                for (Car car : cars) {
                    if (outOfBounds(car)) cc.rebound(car, new Point(reboundX(car), reboundY(car)));
                }
                // frame.drawPanel.repaint();
                frame.update();
            }
        }
}
