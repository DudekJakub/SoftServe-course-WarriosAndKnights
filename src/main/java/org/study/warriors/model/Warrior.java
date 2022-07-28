package org.study.warriors.model;

public class Warrior implements Unit, Cloneable {
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 5;

    private int health;
    private int attack;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    public Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
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

    protected Warrior shallowClone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException ignored) {
            //ignored
        }
        return null;
    }
}
