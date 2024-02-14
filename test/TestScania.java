package test;

import org.junit.Before;
import org.junit.Test;
import src.Direction;
import src.Scania;


import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class TestScania {

        Scania v;

        @Before
        public void init() {v = new Scania();}

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
            assertEquals(Color.blue, v.getColor());
        }

        @Test
        public void facing() {
            assertEquals(Direction.N, v.getDirection());
        }

        @Test
        public void modelName() {
            assertEquals("Scania", v.getModelName());
        }

        @Test
        public void position() {
            assertEquals(new Point2D.Double(0, 0), v.getPosition());
        }

        @Test
        public void newColor() {
            v.setColor(Color.black);
            assertEquals(Color.black, v.getColor());
        }

        @Test
        public void turnLeft() {
            v.turnLeft();
            assertEquals(Direction.NW, v.getDirection());
        }

        @Test
        public void soMuchLeft() {
            v.turnLeft();
            assertEquals(Direction.NW, v.getDirection());
            v.turnLeft();
            assertEquals(Direction.W, v.getDirection());
            v.turnLeft();
            assertEquals(Direction.SW, v.getDirection());
            v.turnLeft();
            assertEquals(Direction.S, v.getDirection());
            v.turnLeft();
            assertEquals(Direction.SE, v.getDirection());
            v.turnLeft();
            assertEquals(Direction.E, v.getDirection());
            v.turnLeft();
            assertEquals(Direction.NE, v.getDirection());
            v.turnLeft();
            assertEquals(Direction.N, v.getDirection());
        }

        @Test
        public void turnRight() {
            v.turnRight();
            assertEquals(Direction.NE, v.getDirection());
        }

        @Test
        public void soMuchRight() {
            v.turnRight();
            assertEquals(Direction.NE, v.getDirection());
            v.turnRight();
            assertEquals(Direction.E, v.getDirection());
            v.turnRight();
            assertEquals(Direction.SE, v.getDirection());
            v.turnRight();
            assertEquals(Direction.S, v.getDirection());
            v.turnRight();
            assertEquals(Direction.SW, v.getDirection());
            v.turnRight();
            assertEquals(Direction.W, v.getDirection());
            v.turnRight();
            assertEquals(Direction.NW, v.getDirection());
            v.turnRight();
            assertEquals(Direction.N, v.getDirection());
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
            assertEquals(0.17, v.getPosition().getY(), 0.01);
            assertEquals(0.07, v.getPosition().getX(), 0.01);
            v.turnRight();
            v.move();
            assertEquals(0.17, v.getPosition().getY(), 0.01);
            assertEquals(0.17, v.getPosition().getX(), 0.01);
            v.turnRight();
            v.move();
            assertEquals(0.1, v.getPosition().getY(), 0.01);
            assertEquals(0.24, v.getPosition().getX(), 0.01);
            v.turnRight();
            v.move();
            assertEquals(0, v.getPosition().getY(), 0.01);
            assertEquals(0.24, v.getPosition().getX(), 0.01);
            v.turnRight();
            v.move();
            assertEquals(-0.07, v.getPosition().getY(), 0.01);
            assertEquals(0.17, v.getPosition().getX(), 0.01);
            v.turnRight();
            v.move();
            assertEquals(-0.07, v.getPosition().getY(), 0.01);
            assertEquals(0.07, v.getPosition().getX(), 0.01);
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
            assertEquals(1.0, v.getCurrentSpeed(), 0.01);
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
            assertEquals(1.0, v.getCurrentSpeed(), 0.01);
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

        //new test for Scania specific
        @Test
        public void incDegreeTest() {
            v.lowerRamp();
            assertEquals(10, v.getRampDegree(), 0);
        }
        @Test
        public void decDegreeTest() {
            v.lowerRamp();
            v.liftRamp();
            assertEquals(0, v.getRampDegree(), 0);
        }
        @Test
        public void decDegreeTest2() {
            v.liftRamp();
            assertEquals(0, v.getRampDegree(), 0);
        }

        @Test
        public void moveWithRampDown() {
            v.lowerRamp();
            v.startEngine();
            v.move();
            assertEquals(0, v.getPosition().getY(), 0.01);
            assertEquals(0, v.getPosition().getX(), 0.01);
        }






}


