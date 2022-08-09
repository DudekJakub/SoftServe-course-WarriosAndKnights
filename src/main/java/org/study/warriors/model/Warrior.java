package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.Unit;

/** Skeleton class for all fighters (so far 04/08).
 *  Main function is dealing and receiving damage (default methods from corresponding interfaces):
 *      1) hit -> this method uses receiveDamage() on the target
 *      2) receiveDamage -> ^^ uses reduceHealthBasedOnDamage() to decrease amount of target's health
 *      3) reduceHealthBasedOnDamage -> ^^ uses setHealth() to determinate final amount of fighter's HP after duel
 **/

public class Warrior implements Unit, IWarrior, Cloneable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Warrior.class);
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 5;

    private int health;
    private final int attack;
    private int lastReceivedDamage = 0;

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

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }

    @Override
    public int getLastReceivedDamage() {
        return lastReceivedDamage;
    }

    @Override
    public void setLastReceivedDamage(int damage) {
        this.lastReceivedDamage = damage;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Warrior clone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException ignored) {
            LOGGER.trace("This message will never be printed");
        }
        return null;
    }
}
