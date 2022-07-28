package org.javabasictraining.encapsulatedApproach;

public interface Unit {

    enum UnitType {
        WARRIOR, KNIGHT, SUPPORT
    }
    static Unit newUnit(UnitType type) {
        return switch (type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Warrior(50, 7, false);
            case SUPPORT -> new Support();
        };
    }

    boolean isAlive();
    void reduceHealth(int damage);
    void setCurse(boolean isCursed);
}
