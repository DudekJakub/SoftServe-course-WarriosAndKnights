package org.javabasictraining;

public class Warrior {
    private static final int ATTACK = 5;
    static final int INITIAL_HEALTH = 50;
    private int health = INITIAL_HEALTH;

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void hit(Warrior target) {
        target.health -= getAttack();
    }
}
