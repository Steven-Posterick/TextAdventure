package GameEntities;

import Game.data.Job;

public class Driver {

    private int money;
    private int hunger;
    private int thirst;
    private int health;
    private int energy;
    private Job job;

    public Driver(int money, int hunger, int thirst, int health, int energy) {
        this.money = money;
        this.hunger = hunger;
        this.thirst = thirst;
        this.health = health;
        this.energy = energy;
        this.job = Job.UNEMPLOYED;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void depositMoney(int deposit){
        this.money += deposit;
    }

    public void withdrawMoney(int withdraw){
        this.money -= withdraw;
    }

    public void addHunger(int hunger){
        this.hunger += hunger;
    }

    public void addThirst(int thirst){
        this.thirst += thirst;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void removeEnergy(int energy) {
        this.energy -= energy;
    }
}
