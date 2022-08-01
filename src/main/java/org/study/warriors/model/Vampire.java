package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vampire extends Warrior {

    private static final Logger LOGGER = LoggerFactory.getLogger(Vampire.class);

    static final int INITIAL_HEALTH = 40;
    static final int ATTACK = 4;
    static final double VAMPIRISM = 0.5;

    private final double vampirism;

    public Vampire() {
        this(INITIAL_HEALTH, ATTACK, VAMPIRISM);
    }

    public Vampire(int health, int attack, double vampirism) {
        super(health, attack);
        this.vampirism = vampirism;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }


}
