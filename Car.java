import java.awt.*;

public class Car implements Drivable, Movable {

    private Direction facing;
    private final Position position;
    private final int nrDoors; // Number of doors on the car
    private Color color; // Color of the car
    private final String modelName; // The car model name
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car

    /**construct a new Car object
     * @param nrDoors       the number of doors the new car has
     * @param enginePower   the engine power of the new car
     * @param clr           the color of the new car
     * @param modelName     the (model) name of the new car
     */
    public Car(int nrDoors, double enginePower, Color clr, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        assert enginePower > 0 : "enginePower must be larger than 0.";
        this.color = clr;
        this.modelName = modelName;
        facing = Direction.N;
        position = new Position();
        stopEngine();
    }

    /**@return     the car's model name*/
    public String getModelName() {
        return modelName;
    }

    /**@return      the car's number of doors*/
    public int getNrDoors() {
        return nrDoors;
    }

    /**@return      the car's current Position*/
    public Position getPosition() {
        return new Position(position.getX(), position.getY());
    }

    /**@return      the car's current facing*/
    public Direction getFacing() {
        return facing;
    }

    /**@return      the car's engine power*/
    public double getEnginePower(){
        return enginePower;
    }

    /**@return      the car's current speed*/
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**@return      the car's color*/
    public Color getColor(){
        return color;
    }

    /**@param   clr the car's new color*/
    public void setColor(Color clr){
        color = clr;
    }

    /**"starts the engine" by setting a minimal currentSpeed*/
    @Override
    public void startEngine() {
            currentSpeed = 0.1;
    }

    /**stops the engine*/
    @Override
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**increase the speed of the car; speed cannot go higher than engine power. percentage applies to engine power
     * @param amount    percentage value between 0 and 1 to increase the car's speed by relative to engine power
     */
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(currentSpeed + amount * 0.01 * enginePower, enginePower);
    }

    /**decrease the speed of the car; speed cannot go below 0. percentage applies to engine power
     * @param amount    percentage value between 0 and 1 to decrease the car's speed by relative to engine power
     */
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(currentSpeed - amount * 0.01 * enginePower, 0);
    }

    /**accelerates the car
     * @param amount    percentage value between 0 and 1 to increase the car's speed relative to
     * @throws IllegalArgumentException if amount is less than 0 or more than 1
     */
    @Override
    public void gas(double amount) {
        if (amount > 1 || amount < 0) {
            throw new IllegalArgumentException("Accelerate by a percentage. Input between 0 and 1.");
        }
            incrementSpeed(amount);
    }

    /**decelerates the car
     * @param amount    percentage value between 0 and 1 to decrease the car's speed relative to
     * @throws IllegalArgumentException if amount is less than 0 or more than 1
     */
    @Override
    public void brake(double amount) {
        if (amount > 1 || amount < 0) {
            throw new IllegalArgumentException("Brake by a percentage. Input between 0 and 1.");
        }
        decrementSpeed(amount);
    }

    /**moves car based on car's current speed in direction of its facing*/
    @Override
    public void move() {
        double diagonal = Math.sqrt(currentSpeed);
        switch(facing) {
            case N -> position.move(0, currentSpeed);
            case NE -> position.move(diagonal, diagonal);
            case E -> position.move(currentSpeed, 0);
            case SE -> position.move(diagonal, -diagonal);
            case S -> position.move(0, -currentSpeed);
            case SW -> position.move(-diagonal, -diagonal);
            case W -> position.move(-currentSpeed, 0);
            case NW -> position.move(-diagonal, diagonal);
        }
    }

    /**changes car's facing to the next direction counter-clockwise*/
    @Override
    public void turnLeft() {
        facing = facing.left();
    }

    /**changes car's facing to the next direction clockwise*/
    @Override
    public void turnRight() {
        facing = facing.right();
    }
}
