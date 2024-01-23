import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CarTest {


    Car c;

    @Before
    public void init() {
        c = new Car(2, 100, Color.red, "c");
    }

    @Test
    public void doors() {
        assertEquals(2, c.getNrDoors());
    }

    @Test
    public void enginePower() {
        assertEquals(100, c.getEnginePower(), 0);
    }

    @Test
    public void color() {
        assertEquals(Color.red, c.getColor());
    }

    @Test
    public void facing() {
        assertEquals(Direction.N, c.getFacing());
    }

    @Test
    public void modelName() {
        assertEquals("c", c.getModelName());
    }

    @Test
    public void position() {
        assertEquals(new Position(0, 0), c.getPosition());
    }

    @Test
    public void newColor() {
        c.setColor(Color.black);
        assertEquals(Color.black, c.getColor());
    }

    @Test
    public void turnLeft() {
        c.turnLeft();
        assertEquals(Direction.NW, c.getFacing());
    }

    @Test
    public void soMuchLeft() {
        c.turnLeft();
        assertEquals(Direction.NW, c.getFacing());
        c.turnLeft();
        assertEquals(Direction.W, c.getFacing());
        c.turnLeft();
        assertEquals(Direction.SW, c.getFacing());
        c.turnLeft();
        assertEquals(Direction.S, c.getFacing());
        c.turnLeft();
        assertEquals(Direction.SE, c.getFacing());
        c.turnLeft();
        assertEquals(Direction.E, c.getFacing());
        c.turnLeft();
        assertEquals(Direction.NE, c.getFacing());
        c.turnLeft();
        assertEquals(Direction.N, c.getFacing());
    }

    @Test
    public void turnRight() {
        c.turnRight();
        assertEquals(Direction.NE, c.getFacing());
    }

    @Test
    public void soMuchRight() {
        c.turnRight();
        assertEquals(Direction.NE, c.getFacing());
        c.turnRight();
        assertEquals(Direction.E, c.getFacing());
        c.turnRight();
        assertEquals(Direction.SE, c.getFacing());
        c.turnRight();
        assertEquals(Direction.S, c.getFacing());
        c.turnRight();
        assertEquals(Direction.SW, c.getFacing());
        c.turnRight();
        assertEquals(Direction.W, c.getFacing());
        c.turnRight();
        assertEquals(Direction.NW, c.getFacing());
        c.turnRight();
        assertEquals(Direction.N, c.getFacing());
    }

    @Test
    public void speed() {
        assertEquals(0, c.getCurrentSpeed(), 0.01);
    }

    @Test
    public void startEngine() {
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed(), 0.01);
    }

    @Test
    public void move() {
        c.startEngine();
        c.move();
        assertEquals(0.1, c.getPosition().getY(), 0.01);
        assertEquals(0, c.getPosition().getX(), 0.01);
    }

    @Test
    public void movingSoMuch() {
        c.startEngine();
        c.move();
        assertEquals(0.1, c.getPosition().getY(), 0.01);
        assertEquals(0, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(0.15, c.getPosition().getY(), 0.01);
        assertEquals(0.05, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(0.15, c.getPosition().getY(), 0.01);
        assertEquals(0.15, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(0.1, c.getPosition().getY(), 0.01);
        assertEquals(0.2, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(0, c.getPosition().getY(), 0.01);
        assertEquals(0.2, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(-0.05, c.getPosition().getY(), 0.01);
        assertEquals(0.15, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(-0.05, c.getPosition().getY(), 0.01);
        assertEquals(0.05, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(0, c.getPosition().getY(), 0.01);
        assertEquals(0, c.getPosition().getX(), 0.01);
        c.turnRight();
        c.move();
        assertEquals(0.1, c.getPosition().getY(), 0.01);
        assertEquals(0, c.getPosition().getX(), 0.01);
    }

    @Test
    public void gas() {
        c.gas(1);
        assertEquals(1, c.getCurrentSpeed(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notGas() {
        c.gas(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notGas2() {
        c.gas(2);
    }

    @Test
    public void soMuchGas() {
        while (c.getCurrentSpeed() < c.getEnginePower()) {
            c.gas(1);
        }
        c.gas(1);
        assertEquals(100, c.getCurrentSpeed(), 0);
        assertEquals(100, c.getEnginePower(), 0);
        assertEquals(c.getEnginePower(), c.getCurrentSpeed(), 0);
    }

    @Test
    public void brake() {
        c.gas(1);
        assertEquals(1, c.getCurrentSpeed(), 0.01);
        c.brake(1);
        assertEquals(0, c.getCurrentSpeed(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notBrake() {
        c.brake(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notBrake2() {
        c.brake(2);
    }

    @Test
    public void soMuchBrake() {
        c.brake(1);
        assertEquals(0, c.getCurrentSpeed(), 0);
    }
}