package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.CanDefense;

public class Defender extends Warrior implements CanDefense {

    private static final Logger LOGGER = LoggerFactory.getLogger(Defender.class);

    static final int INITIAL_HEALTH = 60;
    static final int ATTACK = 3;
    static final int DEFENSE = 2;

    private final int defense;

    public Defender() {
        this(INITIAL_HEALTH, ATTACK, DEFENSE);
    }

    public Defender(int health, int attack, int defense) {
        super(health, attack);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        var finalReceivedDamage = damage - getDefense();
        LOGGER.trace("Defender is blocking received damage with defense {} | DMG = {}", getDefense(), finalReceivedDamage);
        super.reduceHealthBasedOnDamage(Math.max(0, finalReceivedDamage));
        setLastReceivedDamage(finalReceivedDamage);
    }
}
