package org.study.warriors.model;

public interface Unit {

    enum UnitType {
        WARRIOR, KNIGHT
    }
    static Unit newUnit(UnitType type) {
        return switch (type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Warrior(50, 7);
        };
    }
}
