package GameEntities;

import Game.data.Job;

public class Car {

    private int gallonsOfGas;
    private int foodAmount;
    private int waterAmount;

    public Car(int gallonsOfGas, int foodAmount, int waterAmount) {
        this.gallonsOfGas = gallonsOfGas;
        this.foodAmount = foodAmount;
        this.waterAmount = waterAmount;
    }

    public int getGallonsOfGas() {
        return gallonsOfGas;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void setGallonsOfGas(int gallonsOfGas) {
        this.gallonsOfGas = gallonsOfGas;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public void withdrawFood(int foodAmount) {
        this.foodAmount -= foodAmount;
    }

    public void withdrawWater(int waterAmount) {
        this.waterAmount -= waterAmount;
    }
}
