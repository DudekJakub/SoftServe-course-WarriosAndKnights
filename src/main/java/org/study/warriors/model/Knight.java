package org.study.warriors.model;

public class Knight extends Warrior {
    static final int ATTACK = 7;

    public Knight() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
