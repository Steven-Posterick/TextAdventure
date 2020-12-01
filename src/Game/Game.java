package Game;

import Game.data.*;
import GameEntities.Car;
import GameEntities.Driver;
import HelperClasses.DescriptionHelper;
import HelperClasses.MenuHelper;
import HelperClasses.StringHelper;

import java.util.Arrays;
import java.util.Random;

public class Game {

    // Car field
    private final Car car;

    // Rider field
    private final Driver driver;

    // Difficult modifier
    private Difficulty difficulty;

    // int fields
    private int distanceTravelled,
            persuerDistance,
            distanceBetweenRiderAndPersuer,
            currentDay;

    // Location field
    private Location location;

    // Time field
    private Time time;

    // Weather field
    private Weather weather;

    // Random field
    private final Random random;

    public Game(Driver driver, Car car, Difficulty difficulty) {
        this.driver = driver;
        this.car = car;
        this.difficulty = difficulty;
        this.random = new Random();
        this.time = Time.MORNING;
        this.weather = Weather.CLEAR;
        this.location = Location.FARGO;
        this.distanceBetweenRiderAndPersuer = 200 + random.nextInt(100);
        getNewLocation();
    }

    public void start() {
        // Introduction
        System.out.println();
        System.out.println("<--------------------------Introduction-------------------------->");
        System.out.println(
                "You are a newly graduated Computer Science student, with an abundance of student loans racked up. " +
                "You have escaped the debt collectors... for now, but they are following behind, so you must escape and save up to pay off your student loan debt. " +
                "In order to win the game, collect $100,000 and do not be caught by the debt collectors.");
        // Loop while the rider has not died
        while (!checkDriverIsDead()){
            // Check if the player has won the game.
            if (driver.getMoney() >= 100_000){
                outputWinInformation();
                return;
            }

            // Output the turn information
            outputTurnInformation();

            // Process turn menu
            processTurn();
        }
        // If the loop is broken, then output the loss.
        outputLossInformation();
    }

    private void processTurn() {
        // Store if it was validly selected.
        boolean validSelection = false;

        // Display the turn options
        System.out.println();
        System.out.println("<--------------------------Main Menu-------------------------->");
        MenuOption menuOption = MenuHelper.displayMenu("What would you like to do: ", MenuOption.values());

        switch (menuOption){
            case APPLY_TO_JOB:
                validSelection = applyToJob();
                break;

            case WORK_AT_JOB:
                validSelection = workAtJob();
                break;

            case GO_TO_HOSPITAL:
                validSelection = goToHospital();
                break;

            case SLEEP:
                sleep();
                validSelection = true;
                break;

            case GAMBLE:
                gamble();
                validSelection = true;
                break;

            case TRAVEL:
                validSelection = travel();
                break;

            case BUY_FOOD:
                validSelection = buyFood();
                break;

            case BUY_GAS:
                validSelection = buyGas();
                break;

            case BUY_WATER:
                validSelection = buyWater();
                break;
        }

        if (!validSelection)
            processTurn();
    }

    private boolean buyGas() {
        // Max gas
        int maxGas = 20 - car.getGallonsOfGas();

        if (maxGas <= 0){
            System.out.println("Already full of gas.");
            return false;
        }

        // Calculate max amount of water you can actually buy.
        maxGas = Math.min(maxGas, (driver.getMoney() / 4));

        if (maxGas == 0){
            System.out.println("Cannot afford more gas.");
            return false;
        }

        // Tell the cost of water
        System.out.println("Cost of gas: $4.00 / gallon");

        // Ask how much food they want.
        int amount = MenuHelper.displayMenu("How much gas would you like to buy: ", 0, maxGas);

        // Withdraw the cost
        driver.withdrawMoney(amount * 4);

        // Add more food
        car.setGallonsOfGas(car.getGallonsOfGas() + amount);

        crimeChance();
        return true;
    }

    private boolean buyWater() {
        // Max water
        int maxWater = 20 - car.getWaterAmount();

        if (maxWater <= 0){
            System.out.println("Already full of water.");
            return false;
        }

        // Calculate max amount of water you can actually buy.
        maxWater = Math.min(maxWater, (driver.getMoney() / location.getWaterCost()));

        if (maxWater == 0){
            System.out.println("Cannot afford more water.");
            return false;
        }

        // Tell the cost of water
        System.out.println("Cost of water: $" + location.getWaterCost());

        // Ask how much food they want.
        int amount = MenuHelper.displayMenu("How much water would you like to buy: ", 0, maxWater);

        // Withdraw the cost
        driver.withdrawMoney(amount * location.getWaterCost());

        // Add more food
        car.setWaterAmount(amount + car.getWaterAmount());

        crimeChance();
        return true;
    }

    private boolean buyFood() {
        // Max ramen
        int maxRamen = 20 - car.getFoodAmount();

        if (maxRamen <= 0){
            System.out.println("Already full of food.");
            return false;
        }

        // Calculate max food you can actually buy.
        maxRamen = Math.min(maxRamen, (driver.getMoney() / location.getFoodCost()));

        if (maxRamen == 0){
            System.out.println("Cannot afford more food.");
            return false;
        }

        // Tell price of food
        System.out.println("Cost of each ramen: $" + location.getFoodCost());

        // Ask how much food they want.
        int amount = MenuHelper.displayMenu("How much ramen would you like to buy: ", 0, maxRamen);

        // Withdraw the cost
        driver.withdrawMoney(amount * location.getFoodCost());

        // Add more food
        car.setFoodAmount(amount + car.getFoodAmount());

        // Crime for some fun
        crimeChance();
        return true;
    }

    private void crimeChance(){
        if (time != Time.NIGHT && time != Time.EVENING)
            return;

        if ((location.getCrimeRate() * getBillMultipler()) > random.nextDouble()){
            int randNum = random.nextInt(4);
            if (randNum == 0){
                System.out.println("You've just been attacked by a rabid raccoon");
                System.out.println("The raccoon critical hits you because you're a newb.");

                // Take away 50 hp
                driver.setHealth(driver.getHealth() - 50);
            } else if (randNum == 1){
                System.out.println("A bandit steals all your ramen noodles, water, and some of your money.");
                System.out.println("He also smacks you and does damage.");

                // Take away food and water
                car.setFoodAmount(0);
                car.setWaterAmount(0);

                // Take away 5% to 45% of your money.
                driver.setMoney((int) (driver.getMoney() * ((5 + random.nextInt(40)) / 100.0)));

                // Take away 20 hp.
                driver.setHealth(driver.getHealth() - 20);
            } else if (randNum == 2){
                System.out.println("You trip on a curb and a 10 year old steals your wallet.");

                // Take away 5% to 20% of your money.
                driver.setMoney((int) (driver.getMoney() * ((5 + random.nextInt(20)) / 100.0)));

                // Take away 15 hp.
                driver.setHealth(driver.getHealth() - 20);
            } else {
                System.out.println("While walking into the store a car hits you and continues driving.");

                // Take away 40 hp.
                driver.setHealth(driver.getHealth() - 40);
            }
        }
    }


    private boolean travel() {
        int actualCostToLeave = (int) (getBillMultipler() * location.getCostToLeave());
        int gasNeeded = 15;
        if (location.getCostToLeave() > driver.getMoney()) {
            System.out.println("You don't have enough money to leave, you need: " + actualCostToLeave);
            return false;
        }
        if (car.getGallonsOfGas() < gasNeeded) {
            System.out.println("You are too low on gas to travel");
            return false;
        }
        if (driver.getEnergy() < 75){
            System.out.println("You are too tired to travel, sleep first.");
            return false;
        }


        System.out.println("Travelling to a new location.");

        getNewLocation();

        System.out.println("You've arrived in: " + this.location.toString());

        // Distance variable
        int distance = 100 + random.nextInt(400);

        // Add the distance
        persuerDistance += distance;
        distanceTravelled += distance;

        // Charge the driver
        driver.withdrawMoney(actualCostToLeave);

        System.out.println("Cost to leave: " + actualCostToLeave);

        // Remove gas
        car.setGallonsOfGas(car.getGallonsOfGas() - gasNeeded);

        // Takes a random amount of days.
        currentDay += 1 + random.nextInt(3);
        return true;
    }

    private void gamble() {
        int start = driver.getMoney();
        while (true){
            System.out.println("You have $" + driver.getMoney() + " left.");

            // Will either return "Yes" or "No"
            String continueGambling = MenuHelper.displayMenu("Would you like to gamble: ", "Yes", "No");

            if (continueGambling.equals("Yes")){
                int amount = MenuHelper.displayMenu("You are playing 55x2, how much would you like to bet: ", 0, driver.getMoney());

                int roll = random.nextInt(100 + 1);

                if (roll >= 55){
                    // Display output
                    System.out.println("Rolled " + roll + ", you've won: " + amount);

                    // Deposit amount
                    driver.depositMoney(amount);
                } else {
                    // Display loss output
                    System.out.println("Rolled " + roll + ", you've lost: " + amount);

                    // Withdraw amount
                    driver.withdrawMoney(amount);
                }
            } else {
                int profit = driver.getMoney() - start;
                System.out.println("Done gambling for today.");
                if (profit > 0){
                    System.out.println("You made: " + profit);
                } else if (profit == 0){
                    System.out.println("You made no money.");
                } else {
                    System.out.println("You lost: " + -profit);
                }
                break;
            }
        }
    }

    private void sleep() {
        // Deal with eating/drinking
        // Will change the amount of eating/drinking depending on time of day.
        eatAndDrink(Time.values().length - time.ordinal());

        // Change time to morning
        time = Time.MORNING;

        // Reset energy
        driver.setEnergy(100);

        // Slow heal
        if (driver.getHealth() < 70)
            driver.setHealth(driver.getHealth() + random.nextInt(4));

        processTimeChange(true, 0);

        // Check if they've been caught
        if (driver.getHealth() != 0)
            System.out.println("You wake up full of energy and ready for the next day.");
    }

    private boolean goToHospital() {
        // Calculate multiplier.
        double healthInverse = 1.0 - ((driver.getHealth() / 100.0));
        double costMultiplier = healthInverse * getBillMultipler();

        // Calculate bill
        int bill = (int) (location.getHospitalCost() * costMultiplier);

        if (driver.getMoney() < bill){
            System.out.println("You see the estimated bill for the hospital costs, fall to the ground, " +
                    "and the nurses proceed to drop you off on the curb.");
            System.out.println("The estimated cost of the bill was: " + bill);
            return false;
        } else {
            System.out.println("You feel better and are billed: " + bill);

            // Reset the health.
            driver.setHealth(100);

            // Remove the bill cost
            driver.withdrawMoney(bill);
            return true;
        }
    }

    private double getBillMultipler(){
        double inverseDifficultyMultiplier = 1.0 - difficulty.getMultiplier();
        return 1.0 + inverseDifficultyMultiplier;
    }

    private boolean workAtJob() {
        // Check energy.
        if (driver.getEnergy() <= 50){
            System.out.println("Too tired to work today.");
            return false;
        }

        if (driver.getJob() == Job.UNEMPLOYED){
            System.out.println("You need to apply to a job before trying to work.");
            return false;
        }

        if (car.getGallonsOfGas() < 4){
            System.out.println("Too low on gas to travel to work.");
            return false;
        }

        // Cannot work later in the day.
        if (time == Time.MORNING || time == Time.AFTERNOON){
            int shifts = 2 - time.ordinal();
            // Change time of day.
            processTimeChange(false, shifts);

            // Deal with eat and drink calculations
            eatAndDrink(shifts);
            // Calculate daily pay
            double dailyPay = driver.getJob().getWorkWage() * location.getSalaryModifier() * difficulty.getMultiplier();

            // Calculate amount paid.
            int amountPaid = (int) Math.ceil(shifts / 2.0 * dailyPay);

            // Deposit amount of money
            driver.depositMoney(amountPaid);

            // Output the amount paid
            System.out.println("Money made for working: " + amountPaid);

            car.setGallonsOfGas(car.getGallonsOfGas() - 4);

            return true;
        } else {
            System.out.println("Too late in the day to work");
            return false;
        }
    }

    private boolean applyToJob() {
        // There is no job greater than Software Engineer of course.
        if (driver.getJob() == Job.SOFTWARE_ENGINEER){
            System.out.println("Cannot apply to a higher position, already best job.");
            return false;
        }

        // Only allow people to apply during certain times (Morning/Afternoon).
        if (time.ordinal() > Time.AFTERNOON.ordinal()){
            System.out.println("Too late in the day to apply to job.");
            return false;
        }

        // Need enough energy to apply.
        if (driver.getEnergy() <= 50){
            System.out.println("Too tired to apply today.");
            return false;
        }

        // Index of current job
        int currentJobIndex = this.driver.getJob().ordinal();

        // All job positions
        Job[] jobs = Job.values();

        // Positions that are better than your current job.
        Job[] positionsAvailable = Arrays.copyOfRange(jobs, currentJobIndex + 1, jobs.length);

        // Display jobs you can apply to.
        Job applyJob = MenuHelper.displayMenu("Positions available: ", positionsAvailable);

        // Attempt to apply to job.
        double applyRoll = random.nextDouble();
        double applyWeight = 1.0 - difficulty.getMultiplier() * applyJob.getAcceptanceRate();

        // Check if you successfully got hired
        if (applyRoll >= applyWeight){
            System.out.println("Successfully hired to new position: " + applyJob.toString());
            System.out.println("New full day work period: " + ((int) Math.ceil(applyJob.getWorkWage() * location.getSalaryModifier() * difficulty.getMultiplier())));
            driver.setJob(applyJob);
        } else {
            System.out.println("Failed to apply to position: " + applyJob.toString());
            System.out.println("Try an easier positon or try again another day.");
        }

        // Process day change.
        processTimeChange(false, 1);

        // Assume eat and drink once.
        eatAndDrink(1);

        driver.removeEnergy(50);
        return true;
    }

    private void outputTurnInformation() {
        // Tab character for formatting
        final String SEPARATOR = "\t\t\t";

        // Output the current day and distance travelled
        System.out.println();
        System.out.println("<--------------------------Information-------------------------->");
        System.out.println("Day: " + currentDay);
        System.out.println("Distance travelled: " + distanceTravelled);
        System.out.println("Pursuer distance: " + getPursuerDistanceDescription());

        // Output the time of day, location, and weather.
        System.out.println("Time: " + time.toString() + SEPARATOR +
                "Location: " + location.toString() + SEPARATOR +
                "Weather: " + weather.toString());

        // Output amount of money
        System.out.println("Money saved: " + driver.getMoney() + "/" + "100,000");

        // Output column details for Rider and car
        System.out.println("Driver Info: "  + SEPARATOR + "Car status: ");

        System.out.println(
                " Hunger: " + DescriptionHelper.getHungerDescription(driver) + "\t\t" +
                " Food stored: " + DescriptionHelper.getFoodStorageDescription(car));

        System.out.println(
                " Thirst: " + DescriptionHelper.getThirstDescription(driver) + "\t\t" +
                " Water stored: " + DescriptionHelper.getWaterStorageDescription(car));

        System.out.println(
                " Energy: " + DescriptionHelper.getEnergyDescription(driver) + SEPARATOR +
                " Fuel: " + DescriptionHelper.getFuelDescription(car));

        System.out.println(
                " Health: " + DescriptionHelper.getHealthDescription(driver)
                );
    }

    private void outputLossInformation() {
        System.out.println("You've lost the game and failed to pay off your student loans.");
        System.out.println("Stats: ");
        System.out.println("Days taken: " + currentDay);
        System.out.println("Distance travelled: " + distanceTravelled);
        System.out.println("Pursuer distance: " + persuerDistance);
        System.out.println("Final amount of money made: " + driver.getMoney());
    }

    private void outputWinInformation() {
        System.out.println("You've won the game and paid off your student loans.");
        System.out.println("Stats: ");
        System.out.println("Days taken: " + currentDay);
        System.out.println("Pursuer distance: " + persuerDistance);
        System.out.println("Distance travelled: " + distanceTravelled);
    }

    private void eatAndDrink(int amount){
        int hungerLeft = amount;
        int thirstLeft = amount;

        // Handle food storage depletion
        if (car.getFoodAmount() >= hungerLeft) {
            hungerLeft = 0;
            car.withdrawFood(hungerLeft);
            driver.setHunger(0);
        } else {
            hungerLeft -= car.getFoodAmount();
            car.setFoodAmount(0);
        }

        // Handle water storage depletion
        if (car.getWaterAmount() >= thirstLeft){
            thirstLeft = 0;
            car.withdrawWater(thirstLeft);
            driver.setThirst(0);
        } else {
            thirstLeft -= car.getWaterAmount();
            car.setWaterAmount(0);
        }

        // Handle depletion of driver hunger if out of food.
        if (hungerLeft > 0){
            driver.addHunger(hungerLeft * 10);
        }

        // Handle depletion of driver thirst if out of water.
        if (thirstLeft > 0){
            driver.addThirst(thirstLeft * 10);
        }
    }

    private void processTimeChange(boolean sleep, int amount){
        // Boolean to deal with sleeping.
        if (sleep){
            this.time = Time.MORNING;
            processNewDay();
            return;
        }

        // Otherwise go to the next time of day.
        Time[] values = Time.values();

        // Check if it goes through the night
        if (time.ordinal() + amount >= values.length)
            processNewDay();

        // Set the next time of day.
        this.time = values[(time.ordinal() + amount) % values.length];
    }

    private void processNewDay() {
        currentDay++;
        getNewWeather();

        // Recalculate the persuer distance.
        int distance = 5 + random.nextInt(5);
        persuerDistance += distance;
        distanceBetweenRiderAndPersuer -= distance;

        if (distanceBetweenRiderAndPersuer <= 0){
            System.out.println("The student loan collectors have found you and are looking for their money.");
            System.out.println("They rob you and throw you into a nearby ocean for you to swim with the fishies.");
            System.out.println("Be sure to travel when the loan collectors get nearby.");
            driver.setHealth(0);
        }
    }

    private boolean checkDriverIsDead(){
        return driver.getHealth() <= 0 || driver.getHunger() >= 100 || driver.getThirst() >= 100;
    }

    private void getNewLocation(){
        Location previousLocation = location;
        // Inclusive interval (0,14)
        int randomLocation = random.nextInt(14 + 1);

        // Choose a location randomly based on the number generated
        if (randomLocation <= 1){
            location = Location.FARGO;
        } else if (randomLocation <= 3){
            location = Location.NEW_YORK;
        } else if (randomLocation <= 5){
            location = Location.SILICON_VALLEY;
        } else if (randomLocation <= 11){
            location = Location.TWIN_CITIES;
        } else {
            location = Location.CHICAGO;
        }

        // Recall getting a new location if it is the same as the previous location...
        if (location == previousLocation)
            getNewLocation();
    }

    private void getNewWeather(){
        // Inclusive interval (0,9)
        int randomWeather = random.nextInt(9 + 1);

        // Choose a weather
        if (randomWeather <= 5){
            weather = Weather.CLEAR;
        } else if (randomWeather <= 6){
            weather = Weather.CLOUDY;
        } else if (randomWeather <= 7){
            weather = Weather.RAINING;
        } else if (randomWeather <= 8){
            weather = Weather.SNOWING;
        } else {
            weather = Weather.THUNDER_STORM;
        }
    }

    private String getPursuerDistanceDescription(){
        String pursuerDescription = "Collectors Distance: ";

        if (distanceBetweenRiderAndPersuer < 10){
            return pursuerDescription + "About a days travel away.";
        } else if (distanceBetweenRiderAndPersuer < 100){
            return pursuerDescription + "About a weeks travel away.";
        } else if (distanceBetweenRiderAndPersuer < 200){
            return pursuerDescription + "About two weeks away.";
        } else {
            return pursuerDescription + "Greater than two weeks away.";
        }
    }
}
