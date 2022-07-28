package org.javabasictraining.encapsulatedApproach;

public class Knight extends Warrior {
    static final int ATTACK = 5;

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
