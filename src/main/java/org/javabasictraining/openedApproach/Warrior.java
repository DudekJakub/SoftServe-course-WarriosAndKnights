package org.javabasictraining.openedApproach;

public class Warrior implements Unit {
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 5;

    private int health;
    private int attack;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void hit(Warrior target) {
        target.health -= getAttack();
    }

    @Override
    public void joinArmy(Army army) {
        army.getSoldiers().add(this);
    }
}
