package src;
import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 extends PrivateCars {

    private final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, Color.black, 100, "Volvo240");
    }

    public Volvo240(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public Volvo240(int nrDoors, Color color, double enginePower, String modelName, Point pos) {
        super(nrDoors, color, enginePower, modelName, pos);
    }

    @Override
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}