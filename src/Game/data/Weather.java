package Game.data;

import HelperClasses.StringHelper;

public enum Weather {
    THUNDER_STORM,
    CLOUDY,
    CLEAR,
    RAINING,
    SNOWING;

    @Override
    public String toString() {
        return StringHelper.formatEnumName(this.name());
    }
}
