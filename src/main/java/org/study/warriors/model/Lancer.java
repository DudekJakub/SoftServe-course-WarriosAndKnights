package org.study.warriors.model;

public class Lancer extends Warrior {

    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 6;

    public Lancer() {
        this(INITIAL_HEALTH, ATTACK);
    }

    public Lancer(int health, int attack) {
        super(health, attack);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }
}
