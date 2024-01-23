import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class Saab95Test {

    Saab95 s;

    @Before
    public void init() {
        s = new Saab95(2, 100, Color.red, "s95");
    }

    @Test
    public void doors() {
        assertEquals(2, s.getNrDoors());
    }

    @Test
    public void enginePower() {
        assertEquals(100, s.getEnginePower(), 0);
    }

    @Test
    public void color() {
        assertEquals(Color.red, s.getColor());
    }

    @Test
    public void facing() {
        assertEquals(Direction.N, s.getFacing());
    }

    @Test
    public void modelName() {
        assertEquals("s95", s.getModelName());
    }

    @Test
    public void position() {
        assertEquals(new Position(0, 0), s.getPosition());
    }

    @Test
    public void newColor() {
        s.setColor(Color.black);
        assertEquals(Color.black, s.getColor());
    }

    @Test
    public void turnLeft() {
        s.turnLeft();
        assertEquals(Direction.NW, s.getFacing());
    }

    @Test
    public void soMuchLeft() {
        s.turnLeft();
        assertEquals(Direction.NW, s.getFacing());
        s.turnLeft();
        assertEquals(Direction.W, s.getFacing());
        s.turnLeft();
        assertEquals(Direction.SW, s.getFacing());
        s.turnLeft();
        assertEquals(Direction.S, s.getFacing());
        s.turnLeft();
        assertEquals(Direction.SE, s.getFacing());
        s.turnLeft();
        assertEquals(Direction.E, s.getFacing());
        s.turnLeft();
        assertEquals(Direction.NE, s.getFacing());
        s.turnLeft();
        assertEquals(Direction.N, s.getFacing());
    }

    @Test
    public void turnRight() {
        s.turnRight();
        assertEquals(Direction.NE, s.getFacing());
    }

    @Test
    public void soMuchRight() {
        s.turnRight();
        assertEquals(Direction.NE, s.getFacing());
        s.turnRight();
        assertEquals(Direction.E, s.getFacing());
        s.turnRight();
        assertEquals(Direction.SE, s.getFacing());
        s.turnRight();
        assertEquals(Direction.S, s.getFacing());
        s.turnRight();
        assertEquals(Direction.SW, s.getFacing());
        s.turnRight();
        assertEquals(Direction.W, s.getFacing());
        s.turnRight();
        assertEquals(Direction.NW, s.getFacing());
        s.turnRight();
        assertEquals(Direction.N, s.getFacing());
    }

    @Test
    public void speed() {
        assertEquals(0, s.getCurrentSpeed(), 0.01);
    }

    @Test
    public void startEngine() {
        s.startEngine();
        assertEquals(0.1, s.getCurrentSpeed(), 0.01);
    }

    @Test
    public void move() {
        s.startEngine();
        s.move();
        assertEquals(0.1, s.getPosition().getY(), 0.01);
        assertEquals(0, s.getPosition().getX(), 0);
    }

    @Test
    public void speedyMove() {
        s.gas(1);
        s.move();
        assertEquals(1, s.getPosition().getY(), 0.01);
        assertEquals(0, s.getPosition().getX(), 0);
    }

    @Test
    public void turboMove() {
        s.setTurboOn();
        s.gas(1);
        s.move();
        assertEquals(1.3, s.getPosition().getY(), 0.01);
        assertEquals(0, s.getPosition().getX(), 0);
    }

    @Test
    public void movingSoMuch() {
        s.startEngine();
        s.move();
        assertEquals(0.1, s.getPosition().getY(), 0.01);
        assertEquals(0, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(0.15, s.getPosition().getY(), 0.01);
        assertEquals(0.05, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(0.15, s.getPosition().getY(), 0.01);
        assertEquals(0.15, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(0.1, s.getPosition().getY(), 0.01);
        assertEquals(0.2, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(0, s.getPosition().getY(), 0.01);
        assertEquals(0.2, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(-0.05, s.getPosition().getY(), 0.01);
        assertEquals(0.15, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(-0.05, s.getPosition().getY(), 0.01);
        assertEquals(0.05, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(0, s.getPosition().getY(), 0.01);
        assertEquals(0, s.getPosition().getX(), 0.01);
        s.turnRight();
        s.move();
        assertEquals(0.1, s.getPosition().getY(), 0.01);
        assertEquals(0, s.getPosition().getX(), 0.01);
    }

    @Test
    public void turbo() {
        assertFalse(s.isTurboOn());
    }

    @Test
    public void turboOff() {
        s.setTurboOn();
        assertTrue(s.isTurboOn());
    }

    @Test
    public void soMuchTurbo() {
        assertFalse(s.isTurboOn());
        s.setTurboOn();
        assertTrue(s.isTurboOn());
        s.setTurboOn();
        assertTrue(s.isTurboOn());
        s.setTurboOff();
        assertFalse(s.isTurboOn());
        s.setTurboOff();
        assertFalse(s.isTurboOn());
    }

    @Test
    public void gas() {
        s.gas(1);
        assertEquals(1, s.getCurrentSpeed(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notGas() {
        s.gas(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notGas2() {
        s.gas(2);
    }

    @Test
    public void soMuchGas() {
        while (s.getCurrentSpeed() < s.getEnginePower()) {
            s.gas(1);
        }
        s.gas(1);
        assertEquals(100, s.getCurrentSpeed(), 0);
        assertEquals(100, s.getEnginePower(), 0);
        assertEquals(s.getEnginePower(), s.getCurrentSpeed(), 0);
    }

    @Test
    public void brake() {
        s.gas(1);
        assertEquals(1, s.getCurrentSpeed(), 0.01);
        s.brake(1);
        assertEquals(0, s.getCurrentSpeed(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notBrake() {
        s.brake(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notBrake2() {
        s.brake(2);
    }

    @Test
    public void soMuchBrake() {
        s.brake(1);
        assertEquals(0, s.getCurrentSpeed(), 0);
    }

    @Test
    public void turboGas() {
        s.setTurboOn();
        s.gas(1);
        assertEquals(1.3, s.getCurrentSpeed(), 0.01);
    }

}