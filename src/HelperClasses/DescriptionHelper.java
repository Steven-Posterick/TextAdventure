package HelperClasses;

import GameEntities.Car;
import GameEntities.Driver;

public class DescriptionHelper {

    private DescriptionHelper() {}

    public static String getHungerDescription(Driver driver) {
        int hunger = driver.getHunger();
        if (hunger <= 25)
            return "Stuffed";
        else if (hunger <= 60)
            return "Hungry";
        else if (hunger < 100)
            return "Starving";
        else
            return "Starved";
    }

    public static String getFuelDescription(Car car) {
        int fuel = car.getGallonsOfGas();

        if (fuel <= 2)
            return "Almost empty";
        else if (fuel <= 5)
            return "1/4 tank";
        else if (fuel <= 10)
            return "1/2 tank";
        else if (fuel <= 15)
            return "3/4 tank";
        else
            return "Full";
    }

    public static String getFoodStorageDescription(Car car) {
        int food = car.getFoodAmount();

        if (food == 0)
            return "No food";
        if (food <= 5)
            return "A few packages of ramen";
        else if (food <= 10)
            return "Half a trunk of ramen";
        else if (food <= 15)
            return "Much ramen";
        else
            return "Full trunk of ramen";
    }

    public static String getEnergyDescription(Driver driver) {
        int energy = driver.getEnergy();

        if (energy <= 25)
            return "Exhausted";
        else if (energy <= 50)
            return "Average";
        else
            return "Full";
    }

    public static String getHealthDescription(Driver driver) {
        int health = driver.getHealth();

        if (health <= 25)
            return "Almost dead";
        else if (health <= 50)
            return "Close enough";
        else if (health <= 75)
            return "Well";
        else
            return "Healthy";
    }

    public static String getThirstDescription(Driver driver) {
        int thirst = driver.getThirst();

        if (thirst <= 25)
            return "Quenched";
        else if (thirst <= 50)
            return "Fine";
        else if (thirst <= 75)
            return "Parched";
        else
            return "Dehydrated";
    }

    public static String getWaterStorageDescription(Car car) {
        int water = car.getWaterAmount();

        if (water == 0)
            return "No water";
        if (water <= 5)
            return "A few bottles of water";
        else if (water <= 10)
            return "Container of water";
        else if (water <= 15)
            return "Plenty of water";
        else
            return "Full of water";
    }
}
