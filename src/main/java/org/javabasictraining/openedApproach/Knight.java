package org.javabasictraining.openedApproach;

public class Knight extends Warrior {
    private static final int ATTACK = 7;

    public Knight() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
