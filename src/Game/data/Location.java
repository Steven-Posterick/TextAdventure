package Game.data;

import HelperClasses.StringHelper;

public enum Location {
    FARGO(1.30, 0.005, 7, 2, 6, 1000, 1000),
    SILICON_VALLEY(1.8, 0.01, 15, 5, 15, 2000, 3500),
    TWIN_CITIES(1.25, 0.05, 10, 3, 10, 1200, 1800),
    NEW_YORK(1.6, 0.05, 12, 5, 15, 1600, 3000),
    CHICAGO(1.0, 0.18, 6,2, 8, 600, 800);

    private final double salaryModifier;
    private final double crimeRate;
    private final int foodCost;
    private final int waterCost;
    private final double carTravelCost;
    private final int costToLeave;
    private final int hospitalCost;

    Location(double salaryModifier, double crimeRate, int foodCost, int waterCost, double carTravelCost, int costToLeave, int hospitalCost) {
        this.salaryModifier = salaryModifier;
        this.crimeRate = crimeRate;
        this.foodCost = foodCost;
        this.waterCost = waterCost;
        this.carTravelCost = carTravelCost;
        this.costToLeave = costToLeave;
        this.hospitalCost = hospitalCost;
    }

    public double getSalaryModifier() {
        return salaryModifier;
    }

    public double getCrimeRate() {
        return crimeRate;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public double getCarTravelCost() {
        return carTravelCost;
    }

    public int getCostToLeave() {
        return costToLeave;
    }

    public int getHospitalCost() {
        return hospitalCost;
    }

    @Override
    public String toString() {
        return StringHelper.formatEnumName(this.name());
    }
}
