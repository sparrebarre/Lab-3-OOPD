import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class Volvo240Test {

    Volvo240 v;

    @Before
    public void init() {
        v = new Volvo240(2, 100, Color.red, "v240");
    }

    @Test
    public void doors() {
        assertEquals(2, v.getNrDoors());
    }
    
    @Test
    public void enginePower() {
        assertEquals(100, v.getEnginePower(), 0);
    }

    @Test
    public void color() {
        assertEquals(Color.red, v.getColor());
    }

    @Test
    public void facing() {
        assertEquals(Direction.N, v.getFacing());
    }

    @Test
    public void modelName() {
        assertEquals("v240", v.getModelName());
    }

    @Test
    public void position() {
        assertEquals(new Position(0, 0), v.getPosition());
    }

    @Test
    public void newColor() {
        v.setColor(Color.black);
        assertEquals(Color.black, v.getColor());
    }

    @Test
    public void turnLeft() {
        v.turnLeft();
        assertEquals(Direction.NW, v.getFacing());
    }

    @Test
    public void soMuchLeft() {
        v.turnLeft();
        assertEquals(Direction.NW, v.getFacing());
        v.turnLeft();
        assertEquals(Direction.W, v.getFacing());
        v.turnLeft();
        assertEquals(Direction.SW, v.getFacing());
        v.turnLeft();
        assertEquals(Direction.S, v.getFacing());
        v.turnLeft();
        assertEquals(Direction.SE, v.getFacing());
        v.turnLeft();
        assertEquals(Direction.E, v.getFacing());
        v.turnLeft();
        assertEquals(Direction.NE, v.getFacing());
        v.turnLeft();
        assertEquals(Direction.N, v.getFacing());
    }

    @Test
    public void turnRight() {
        v.turnRight();
        assertEquals(Direction.NE, v.getFacing());
    }

    @Test
    public void soMuchRight() {
        v.turnRight();
        assertEquals(Direction.NE, v.getFacing());
        v.turnRight();
        assertEquals(Direction.E, v.getFacing());
        v.turnRight();
        assertEquals(Direction.SE, v.getFacing());
        v.turnRight();
        assertEquals(Direction.S, v.getFacing());
        v.turnRight();
        assertEquals(Direction.SW, v.getFacing());
        v.turnRight();
        assertEquals(Direction.W, v.getFacing());
        v.turnRight();
        assertEquals(Direction.NW, v.getFacing());
        v.turnRight();
        assertEquals(Direction.N, v.getFacing());
    }

    @Test
    public void speed() {
        assertEquals(0, v.getCurrentSpeed(), 0.01);
    }

    @Test
    public void startEngine() {
        v.startEngine();
        assertEquals(0.1, v.getCurrentSpeed(), 0.01);
    }

    @Test
    public void move() {
        v.startEngine();
        v.move();
        assertEquals(0.1, v.getPosition().getY(), 0.01);
        assertEquals(0, v.getPosition().getX(), 0.01);
    }

    @Test
    public void movingSoMuch() {
        v.startEngine();
        v.move();
        assertEquals(0.1, v.getPosition().getY(), 0.01);
        assertEquals(0, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(0.15, v.getPosition().getY(), 0.01);
        assertEquals(0.05, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(0.15, v.getPosition().getY(), 0.01);
        assertEquals(0.15, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(0.1, v.getPosition().getY(), 0.01);
        assertEquals(0.2, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(0, v.getPosition().getY(), 0.01);
        assertEquals(0.2, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(-0.05, v.getPosition().getY(), 0.01);
        assertEquals(0.15, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(-0.05, v.getPosition().getY(), 0.01);
        assertEquals(0.05, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(0, v.getPosition().getY(), 0.01);
        assertEquals(0, v.getPosition().getX(), 0.01);
        v.turnRight();
        v.move();
        assertEquals(0.1, v.getPosition().getY(), 0.01);
        assertEquals(0, v.getPosition().getX(), 0.01);
    }

    @Test
    public void gas() {
        v.gas(1);
        assertEquals(1.25, v.getCurrentSpeed(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notGas() {
        v.gas(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notGas2() {
        v.gas(2);
    }

    @Test
    public void soMuchGas() {
        while (v.getCurrentSpeed() < v.getEnginePower()) {
            v.gas(1);
        }
        v.gas(1);
        assertEquals(100, v.getCurrentSpeed(), 0);
        assertEquals(100, v.getEnginePower(), 0);
        assertEquals(v.getEnginePower(),v.getCurrentSpeed(), 0);
    }

    @Test
    public void brake() {
        v.gas(1);
        assertEquals(1.25, v.getCurrentSpeed(), 0.01);
        v.brake(1);
        assertEquals(0, v.getCurrentSpeed(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notBrake() {
        v.brake(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notBrake2() {
        v.brake(2);
    }

    @Test
    public void soMuchBrake() {
        v.brake(1);
        assertEquals(0, v.getCurrentSpeed(), 0);
    }
}