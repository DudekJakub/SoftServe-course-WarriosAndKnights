package org.javabasictraining.encapsulatedApproach;

public interface Unit {

    enum UnitType {
        WARRIOR, KNIGHT
    }
    static Unit newUnit(UnitType type) {
        return switch (type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Warrior(50, 7, false);
        };
    }

    boolean isAlive();
    void reduceHealth(int damage);
    void setCurse(boolean isCursed);
}
