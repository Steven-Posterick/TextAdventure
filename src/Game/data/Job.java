package Game.data;

import HelperClasses.StringHelper;

public enum Job {
    UNEMPLOYED(1.0, 0),
    COLLECT_CANS(1.0, 20),
    COFFEE_SHOP(0.50, 80),
    SOFTWARE_ENGINEER(0.10,240);

    private final double acceptanceRate;
    private final double workWage;

    Job(double acceptanceRate, double workWage) {
        this.acceptanceRate = acceptanceRate;
        this.workWage = workWage;
    }

    public double getAcceptanceRate() {
        return acceptanceRate;
    }

    public double getWorkWage() {
        return workWage;
    }

    @Override
    public String toString() {
        return StringHelper.formatEnumName(this.name());
    }
}
