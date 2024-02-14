package src;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;


public class WorkShop<T extends Car> {

    private Point pos;
    private final int maxCapacity;
    private Set<T> cars;

    /** Variables */
    /**Constructor
     *@param capacity The maximum amount of cars this WorkShop can hold.*/
    public WorkShop(int capacity) {
        this.cars = new HashSet<>();
        this.maxCapacity = capacity;
        pos = new Point();
    }

    public WorkShop(int capacity, Point pos) {
        this.cars = new HashSet<>();
        this.maxCapacity = capacity;
        this.pos = pos;
    }

    /**Adds a Car to the WorkShop.
     *@param car The Car to be added to the WorkShop.
     *@return   true    if successful, false otherwise.*/
    public boolean addCar(T car){
       if (cars.size() < maxCapacity) {
           cars.add(car);
           return true;
       }
       return false;
    }

    /**Remove the Car with the given serial number from the WorkShop.
     *@param serial The serial number of the car that is to be removed.
     *@return   The Car that was removed.*/
    public T removeCar(int serial){
        for (T car : cars){
            if (car.getSerial() == serial){
                cars.remove(car);
                return car;
            }
        }
        return null;
    }

    /**The number of cars currently at the WorkShop.
     *@return   The number of cars maintained by the WorkShop.*/
    public int getCars() {
        return cars.size();
    }

    /**The maximum amount of cars this WorkShop can hold.*/
    public int getMaxCapacity(){
        return maxCapacity;
    }

    public Point getPosition() {
        return new Point((int) pos.getX(), (int) pos.getY());
    }
}
