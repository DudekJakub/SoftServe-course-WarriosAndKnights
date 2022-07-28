package org.javabasictraining.encapsulatedApproach;

public class Warrior implements Unit, Cloneable {
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 5;
    static boolean IS_CURSED = false;

    private int health;
    private int attack;
    private boolean isCursed;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK, IS_CURSED);
    }

    public Warrior(int health, int attack, boolean isCursed) {
        this.health = health;
        this.attack = attack;
        this.isCursed = isCursed;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void setAttack(int newAttack) {
        this.attack = newAttack;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void hit(Warrior target) {
        target.health -= getAttack();
    }

    @Override
    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    @Override
    public void setCurse(boolean isCursed) {
        this.isCursed = isCursed;
    }

    protected Warrior shallowClone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException ignored) {
            //ignored
        }
        return null;
    }
}
