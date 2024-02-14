package src;

public class Main {


    public static void main(String[] args) {
        /**
         * Doesn't need Class parameter in the <> but, it strictly enforces the workshop to only handle those objects
         * Unnecessary to have Class parameter when instanced at beginning
         * */
        WorkShop<Volvo240> myWorkshop = new WorkShop<Volvo240>(10);
        WorkShop<Saab95> saabWorkShop = new WorkShop<Saab95>(10);
        WorkShop<PrivateCars> privateWorkshop = new WorkShop<>(10);

        Volvo240 myCar = new Volvo240();
        Saab95 shouldFail = new Saab95();


        privateWorkshop.addCar(myCar);
        privateWorkshop.addCar(shouldFail);

        saabWorkShop.addCar(shouldFail);
        myWorkshop.addCar(myCar);
        //myWorkshop.addCar(shouldFail); Fails Correct
        // remove car and print serial
        //myWorkshop.removeCar(myCar); Fails Correct


    }
}
