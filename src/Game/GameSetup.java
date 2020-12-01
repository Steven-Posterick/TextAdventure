package Game;

import Game.data.Difficulty;
import Game.data.Type;
import GameEntities.Car;
import GameEntities.Driver;
import HelperClasses.MenuHelper;

import java.util.concurrent.ThreadLocalRandom;

public class GameSetup {
    public static Game setupGame(){
        // Print out the introduction
        System.out.println("Welcome to Livin’ in a Car");

        // Output and get the difficulty option
        Difficulty difficulty = MenuHelper.displayMenu("Please select a difficulty:", Difficulty.values());

        // Setup the car
        Car car = setupCar();

        // Setup the driver
        Driver driver = setupDriver();

        return new Game(driver, car, difficulty);
    }

    private static Driver setupDriver(){
        // Process the driver type
        Type driverType = MenuHelper.displayMenu("What kind of driver would you like to use: ", Type.values());

        // Declare and initialize the driver variables
        int money, hunger, thirst, health, energy;

        switch (driverType){
            case STANDARD:
                money = 2_000;
                hunger = 0;
                thirst = 0;
                health = 100;
                energy = 100;
                break;

            case RANDOM:
                money = ThreadLocalRandom.current().nextInt(500, 25_000);
                hunger = ThreadLocalRandom.current().nextInt(0,50);
                thirst = ThreadLocalRandom.current().nextInt(0,50);
                health = ThreadLocalRandom.current().nextInt(50,100);
                energy = ThreadLocalRandom.current().nextInt(50,100);
                break;

            default:
            case CUSTOM:
                money = MenuHelper.displayMenu("Please enter an initial money amount: ", 0, 50_000);
                hunger = MenuHelper.displayMenu("Please enter an initial hunger amount: ", 0, 80);
                thirst = MenuHelper.displayMenu("Please enter an initial thirst amount: ", 0,80);
                health = MenuHelper.displayMenu("Please enter an initial health amount: " ,10, 100);
                energy = MenuHelper.displayMenu("Please enter an initial energy amount: ", 20, 100);
                break;
        }
        return new Driver(money, hunger, thirst, health, energy);
    }

    private static Car setupCar(){

        // Process a car type
        Type carType = MenuHelper.displayMenu("What kind of car would you like to use: ", Type.values());

        // Declare and initialize the car variables
        int gallonsOfGas, foodAmount, waterAmount;

        switch (carType){
            case STANDARD:
                gallonsOfGas = 10;
                foodAmount = 5;
                waterAmount = 5;
                break;

            case RANDOM:
                gallonsOfGas = ThreadLocalRandom.current().nextInt(1, 20);
                foodAmount = ThreadLocalRandom.current().nextInt(1, 20);
                waterAmount = ThreadLocalRandom.current().nextInt(1,20);
                break;

            default:
            case CUSTOM:
                gallonsOfGas = MenuHelper.displayMenu("Enter the initial gallons of gas in your car: ", 1, 20);
                foodAmount = MenuHelper.displayMenu("Enter the initial amount of food in your car", 1, 20);
                waterAmount = MenuHelper.displayMenu("Enter the initial amount of water in your car", 1, 20);
                break;
        }

        return new Car(gallonsOfGas, foodAmount, waterAmount);
    }
}
