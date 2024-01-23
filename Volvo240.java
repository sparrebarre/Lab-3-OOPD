import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    public Volvo240(int nrDoors, double enginePower, Color clr, String modelName){
        super(nrDoors, enginePower, clr, modelName);
    }

    /**calculates impact of engine power on speed
     * @return  a low percentage of engine power. takes into account trim factor
     */
    private double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    /**increase the speed of the car; speed cannot go higher than engine power. percentage applies to engine power.
     * takes into account trim factor
     * @param amount    percentage value between 0 and 1 to increase the car's speed by relative to engine power
     */
    @Override
    protected void incrementSpeed(double amount){
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**decrease the speed of the car; speed cannot go below 0. percentage applies to engine power.
     * takes into account trim factor
     * @param amount    percentage value between 0 and 1 to decrease the car's speed by relative to engine power
     */
    @Override
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
}
