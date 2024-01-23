import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;


    public Saab95(int nrDoors, double enginePower, Color clr, String modelName) {
        super(nrDoors, enginePower, clr, modelName);
        turboOn = false;
    }

    /**set turbo mode on*/
    public void setTurboOn() {
        turboOn = true;
    }

    /**set turbo mode off*/
    public void setTurboOff() {
        turboOn = false;
    }

    /**check if turbo mode is on
     * @return  true if turbo mode is on
     */
    public boolean isTurboOn() {
        return turboOn;
    }

    /**calculates impact of engine power on speed
     * @return  a low percentage of engine power. takes into account turbo mode
     */
    private double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    /**increase the speed of the car; speed cannot go higher than engine power. percentage applies to engine power.
     * takes into account turbo mode
     * @param amount    percentage value between 0 and 1 to increase the car's speed by relative to engine power
     */
    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    /**decrease the speed of the car; speed cannot go below 0. percentage applies to engine power.
     * takes into account turbo mode
     * @param amount    percentage value between 0 and 1 to decrease the car's speed by relative to engine power
     */
    @Override
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}

