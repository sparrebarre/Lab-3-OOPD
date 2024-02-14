package test;

import org.junit.Test;
import src.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestWorkShop {

    /** Init type specific workshops */

    WorkShop<Volvo240> VolvoWorkshop = new WorkShop<>(3);              // Bottom-most-class
    WorkShop<TransportVehicles> TpWorkshop = new WorkShop<>(3);        // Intermediate-class

    /** Init any-type workshop */
    WorkShop<Car> AnyWorkshop = new WorkShop<>(3);                     // Top-class

    /** Init cars */
    Volvo240 volvo = new Volvo240();
    Volvo240 volvo2 = new Volvo240();
    Volvo240 volvo3 = new Volvo240();
    Volvo240 volvo4 = new Volvo240();
    Scania scania = new Scania();
    Scania scania2 = new Scania();
    Scania scania3 = new Scania();
    Scania scania4 = new Scania();
    TransportTruck truck = new TransportTruck();


    /** TESTS */
    // Add/remove functionality on workshop size
    // Remove functionality comparing identities (mismatched serials)
    // Workshop capacities limits and boolean reaches


    /** Case 1 - Top-class*/

    @Test
    public void checkAddAny1(){
        AnyWorkshop.addCar(volvo);
        AnyWorkshop.addCar(scania);
        AnyWorkshop.addCar(truck);
        assertEquals(3, AnyWorkshop.getCars());
    }

    @Test
    public void checkAddRemoveIdentity1() {
        AnyWorkshop.addCar(volvo);
        assertEquals(AnyWorkshop.getCars(), 1);
        assertEquals(AnyWorkshop.removeCar(volvo.getSerial()), volvo);
        assertEquals(AnyWorkshop.getCars(), 0);
    }
    @Test
    public void checkDuplicate1(){
        AnyWorkshop.addCar(volvo);
        AnyWorkshop.addCar(volvo);
        assertEquals(AnyWorkshop.getCars(),1);
    }

    @Test
    public void checkCapacity1(){
        AnyWorkshop.addCar(volvo);
        AnyWorkshop.addCar(volvo2);
        AnyWorkshop.addCar(volvo3);
        assertFalse(AnyWorkshop.addCar(volvo4));
        assertEquals(AnyWorkshop.getCars(), AnyWorkshop.getMaxCapacity());
    }
    @Test
    public void checkNotSerial1(){
        AnyWorkshop.addCar(volvo);
        assertNull(AnyWorkshop.removeCar(123));
        assertEquals(AnyWorkshop.getCars(), 1);
    }

    /** Case 2 - Mid-class */
    @Test
    public void checkAddAny2(){
        TpWorkshop.addCar(scania);
        TpWorkshop.addCar(truck);
        assertEquals(2, TpWorkshop.getCars());
    }

    @Test
    public void checkDuplicate2(){
        TpWorkshop.addCar(scania);
        TpWorkshop.addCar(scania);
        assertEquals(TpWorkshop.getCars(),1);
    }
    @Test
    public void checkAddRemoveIdentity2() {
        TpWorkshop.addCar(scania);
        assertEquals(TpWorkshop.getCars(), 1);
        assertEquals(TpWorkshop.removeCar(scania.getSerial()), scania);
        assertEquals(TpWorkshop.getCars(), 0);
    }

    @Test
    public void checkCapacity2(){
        TpWorkshop.addCar(scania);
        TpWorkshop.addCar(scania2);
        TpWorkshop.addCar(scania3);
        assertFalse(TpWorkshop.addCar(scania4));
        assertEquals(TpWorkshop.getCars(), TpWorkshop.getMaxCapacity());
    }
    @Test
    public void checkNotSerial2(){
        TpWorkshop.addCar(scania);
        assertNull(TpWorkshop.removeCar(123));
        assertEquals(TpWorkshop.getCars(), 1);
    }

    /** Case 3 - Bottom-class*/

    @Test
    public void checkAddRemoveIdentity3() {
        VolvoWorkshop.addCar(volvo);
        assertEquals(VolvoWorkshop.getCars(), 1);
        assertEquals(VolvoWorkshop.removeCar(volvo.getSerial()), volvo);
        assertEquals(VolvoWorkshop.getCars(), 0);
    }

    @Test
    public void checkNotSerial3(){
        VolvoWorkshop.addCar(volvo);
        assertNull(VolvoWorkshop.removeCar(123));
        assertEquals(VolvoWorkshop.getCars(), 1);
    }

    @Test
    public void checkDuplicate3(){
        VolvoWorkshop.addCar(volvo);
        VolvoWorkshop.addCar(volvo);
        assertEquals(VolvoWorkshop.getCars(),1);
    }

    @Test
    public void checkCapacity3(){
        VolvoWorkshop.addCar(volvo);
        VolvoWorkshop.addCar(volvo2);
        VolvoWorkshop.addCar(volvo3);
        assertFalse(VolvoWorkshop.addCar(volvo4));
        assertEquals(VolvoWorkshop.getCars(), VolvoWorkshop.getMaxCapacity());
    }

}
