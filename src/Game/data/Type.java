package Game.data;

import HelperClasses.StringHelper;

public enum Type {

    STANDARD,
    RANDOM,
    CUSTOM;

    @Override
    public String toString() {
        return StringHelper.formatEnumName(this.name());
    }
}
