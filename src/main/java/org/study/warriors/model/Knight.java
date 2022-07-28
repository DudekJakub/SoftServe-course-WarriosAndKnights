package org.study.warriors.model;

public class Knight extends Warrior {
    static final int ATTACK = 5;

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
