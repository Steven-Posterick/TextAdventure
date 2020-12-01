package Game.data;

import HelperClasses.StringHelper;

public enum Time {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT;

    // Returns the correctly formatted name ie Morning.
    @Override
    public String toString() {
        return StringHelper.formatEnumName(this.name());
    }
}
