package src;

import java.util.Random;

public class CarGenerator {
    private static final Random random = new Random();

    // Constructor, no need for a constructor in this? only void method for random car generation
    public CarGenerator() {
    }

    // Method for generating a random car
    public static Car randomCarGen(){
        int x = random.nextInt(3);
        switch (x) {
            case 0:
                Car car = new Volvo240();
                car.setImagePath("pics/Volvo240.jpg");
                return car;

            case 1:
                Car car1 = new Saab95();
                car1.setImagePath("pics/Saab95.jpg");

                return car1;
            case 2:
                Car car2 = new Scania();
                car2.setImagePath("pics/Scania.jpg");

                return car2;

            default:
                return null; // will never happen
        }
    }
}
