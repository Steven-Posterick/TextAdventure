package Game.data;

import HelperClasses.StringHelper;

public enum MenuOption {
    APPLY_TO_JOB,
    WORK_AT_JOB,
    GAMBLE,
    SLEEP,
    BUY_FOOD,
    BUY_WATER,
    BUY_GAS,
    GO_TO_HOSPITAL,
    TRAVEL;

    @Override
    public String toString() {
        return StringHelper.formatEnumName(this.name());
    }
}
