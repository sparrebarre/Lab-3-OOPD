package src;
import java.awt.*;
import java.awt.geom.Point2D;

public abstract class PrivateCars extends Car{
    public PrivateCars(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    public PrivateCars(int nrDoors, Color color, double enginePower, String modelName, Point pos) {
        super(nrDoors, color, enginePower, modelName, pos);
    }
}
