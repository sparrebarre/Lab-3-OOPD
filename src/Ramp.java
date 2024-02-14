package src;

public class Ramp {
    private boolean ramp;
    private int rampDegree;

    private static final int maxDegree = 70;

    public void lowerRamp(){
        ramp = true;
    }

    /**Decrease the angle of the ramp incrementally until fully folded up.*/

    public void liftRamp(){
        ramp = false;
    }

    public boolean isLoadable(){
        return ramp;
    }


    /**@return  the current angle of the ramp*/
    public int getRampDegree(){
        return rampDegree;
    }

    public boolean incDegree(){
        if(rampDegree >= maxDegree) return false;
        else rampDegree = Math.min(rampDegree += 10, maxDegree);
        // if implementing a load method in scania that depends on ramp being fully down:
        // need to redefine when ramp is considered down
        if (rampDegree > 0) ramp = true;
        return true;
    }
    public boolean decDegree(){
        rampDegree  = Math.max(rampDegree -= 10, 0);
        if (rampDegree == 0) ramp = false;
        return true;
    }
}
