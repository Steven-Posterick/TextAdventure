package Game.data;

import HelperClasses.StringHelper;

public enum Difficulty {
    EASY(1.0),
    MEDIUM(.90),
    HARD(.75),
    CHALLENGING(.60);

    private final double multiplier;

    Difficulty(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }

    @Override
    public String toString() {
        return StringHelper.formatEnumName(this.name());
    }
}
