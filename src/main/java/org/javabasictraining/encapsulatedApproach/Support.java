package org.javabasictraining.encapsulatedApproach;

public class Support implements Unit {

    static final int INITIAL_HEALTH = 10;
    static boolean IS_CURSED = false;

    private int health;
    private boolean isCursed;


    public Support() {
        this(INITIAL_HEALTH, IS_CURSED);
    }

    protected Support(int health, boolean isCursed) {
        this.health = health;
        this.isCursed = isCursed;
    }

    public void curse(Unit unit) {
    }

    @Override
    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    @Override
    public void setCurse(boolean isCursed) {
        this.isCursed = isCursed;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
