package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    private final int RANGE = 5;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    WorkShop<Volvo240> shop = new WorkShop<>(2, new Point(300, 300));

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240(4, Color.black, 100, "Volvo240", new Point(0, 100)));
        cc.cars.add(new Saab95(2, Color.red, 125, "Saab95", new Point(0, 200)));
        cc.cars.add(new Scania(2, Color.blue, 100, "Scania", new Point(0, 300)));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ArrayList<Car> removables = new ArrayList<>();
            for (Car car : cars) {
                Point oldPos = car.getPosition();
                car.move();
                Point pos = car.getPosition();
                int x = (int) pos.getX();
                int y = (int) pos.getY();
                if (inRange(car.getPosition(), shop.getPosition()) && car instanceof Volvo240) {
                    shop.addCar((Volvo240) car);
                    removables.add(car);
                }
                if (x < 0 || x > frame.getX()/2 + frame.getWidth()/2 || y < 0 || y > frame.getHeight() - 300) {
                    car.stopEngine();
                    car.turnAround();
                    rebound(car);
                    car.startEngine();
                    x = (int) car.getPosition().getX();
                    y = (int) car.getPosition().getY();
                }
                frame.drawPanel.moveit((int) oldPos.getX(), (int) oldPos.getY(), x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
            // removing outside for-each loop because inside would cause an exception
            for (Car removable : removables) cars.remove(removable);
        }
    }

    void rebound(Car car) {
        Point pos = car.getPosition();
        int x = (int) pos.getX();
        int y = (int) pos.getY();
        if (x < 0) car.setPosition(new Point(1, y));
        else if (x > frame.getX()/2 + frame.getWidth()/2) car.setPosition(new Point(frame.getX()/2 + frame.getWidth()/2, y));
        if (y < 0) car.setPosition(new Point(x, 1));
        else if (y > frame.getHeight() - 300) car.setPosition(new Point(x, frame.getHeight() - 300));
    }

    boolean inRange(Point carPos, Point shopPos) {
        return (carPos.getX() - shopPos.getX() >= -RANGE && carPos.getX() - shopPos.getX() <= RANGE) &&
                (carPos.getY() - shopPos.getY() >= -RANGE && carPos.getY() - shopPos.getY() <= RANGE);
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
            for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount / 100);
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOn();
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) ((Saab95)car).setTurboOff();
        }
    }

    void lowerRamp() {
        for (Car car : cars) {
            if (car instanceof TransportVehicles) ((TransportVehicles) car).lowerRamp();
        }
    }

    void liftRamp() {
        for (Car car : cars) {
            if (car instanceof TransportVehicles) ((TransportVehicles) car).liftRamp();
        }
    }

    void startEngine() {
        for (Car car : cars) car.startEngine();
    }

    void stopEngine() {
        for (Car car : cars) car.stopEngine();
    }
}
