package src;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Stack;

public class TransportTruck extends TransportVehicles{
    // Assumption: TransportVehicles are too large to go on the car transporter
    Stack<PrivateCars> carTransport = new Stack<>();
    private final int capacity;
    private final static int LOAD_RANGE = 10;


    public TransportTruck() {
        super(2, Color.gray, 100, "Truck");
        capacity = 10;
    }

    public TransportTruck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
        capacity = 10;
    }

    public TransportTruck(int nrDoors, Color color, double enginePower, String modelName, Point pos) {
        super(nrDoors, color, enginePower, modelName, pos);
        capacity = 10;
    }

    @Override
    public void liftRamp() {
        ramp.liftRamp();
    }

    /**Lowers the ramp if standing still
     *@return   true    if successful, false otherwise.*/
    @Override
    public boolean lowerRamp() {
        if (currentSpeed == 0) ramp.lowerRamp();
        return ramp.isLoadable();
    }

    /**Load a PrivateCars onto the TransportTruck.
     *@param pCar The PrivateCars to be loaded onto the TransportTruck.
     *@return   true    if successful, otherwise false.*/
    public boolean load(PrivateCars pCar) {
        if(isLoadable() && inRange(pCar) && carTransport.size() < capacity) {
            pCar.position = position;
            carTransport.push(pCar);
            pCar.stopEngine();
            return true;
        } else return false;
    }

    /**Creates a new position for the car depending on current speed and direction.
     * Updates the position of transported cars accordingly.*/
    @Override
    public void move() {
        super.move();
        for (PrivateCars car : carTransport) {
            car.position = position;
        }
    }

    /**Checks if the car is close enough to be loaded onto the transporter.
     *@param vehicle The PrivateCars to be loaded onto the transporter.
     *@return   true    if in range, otherwise false.*/
    private boolean inRange(PrivateCars vehicle) {
        double xDiff = getPosition().getX() - vehicle.getPosition().getX();
        double yDiff = getPosition().getY() - vehicle.getPosition().getY();
        return Math.abs(xDiff) <= LOAD_RANGE && Math.abs(yDiff) <= LOAD_RANGE;
    }

    /**Unloads the most recently loaded PrivateCars (LIFO).
     *@return   The unloaded PrivateCars.*/
    public PrivateCars deLoad() {
        if(isLoadable() && !carTransport.isEmpty()) {
            PrivateCars unloaded = carTransport.pop();
            unloaded.position.setLocation(position.getX() - 10, position.getY() - 10);
            return unloaded;
        } else return null;
    }

    /**Unloads cars in reverse order of loading (LIFO).
     *@param count The number of PrivateCars to be unloaded.
     *@return   true    if successful, false otherwise.*/
    protected boolean deLoad(int count) {
        int x = 0;
        if (count < carTransport.size() && isLoadable()) {
            for (int i = 0; i < count; i++) {
                carTransport.pop().position.setLocation(position.getX() + 10,
                        position.getY() + 10);
                x++;
            }
        }
        return x == count;
    }

    /**@return  How many PrivateCars are currently on the TransportTruck.*/
    public int getLoad() {
        return carTransport.size();
    }

    /**@return  How many PrivateCars this TransportTruck can hold.*/
    public int getCapacity() {
        return capacity;
    }
}
