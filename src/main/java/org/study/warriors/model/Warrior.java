package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.equipment.weapon.WeaponEquipment;
import org.study.warriors.model.interfaces.CanWieldWeapon;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.Unit;
import org.study.warriors.model.observer.Observer;
import org.study.warriors.model.request.Chain;

import java.util.ArrayList;
import java.util.List;

/** Skeleton class for all fighters (so far 04/08).
 *  Main function is dealing and receiving damage (default methods from corresponding interfaces):
 *      1) hit -> this method uses receiveDamage() on the target
 *      2) receiveDamage -> ^^ uses reduceHealthBasedOnDamage() to decrease amount of target's health
 *      3) reduceHealthBasedOnDamage -> ^^ uses setHealth() to determinate final amount of fighter's HP after duel
 **/

public class Warrior implements Unit, IWarrior, Chain, Cloneable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Warrior.class);

    static final int INITIAL_HEALTH = 50;
    static final int INITIAL_ATTACK = 5;

    private int health;
    private int attack;
    private int lastReceivedDamage = 0;
    private Chain nextInChain;
    private final WeaponEquipment weaponEquipment;
    private final List<Observer> observers;

    public Warrior() {
        this(INITIAL_HEALTH, INITIAL_ATTACK);
    }

    public Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
        this.weaponEquipment = new WeaponEquipment();
        this.observers = new ArrayList<>();
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH + weaponEquipment.getHealthWeaponModifiers();
    }

    @Override
    public int getLastReceivedDamage() {
        return lastReceivedDamage;
    }

    @Override
    public Chain getNextInChain() {
        return nextInChain;
    }

    @Override
    public WeaponEquipment getEquipment() {
        return weaponEquipment;
    }

    @Override
    public void setLastReceivedDamage(int damage) {
        this.lastReceivedDamage = damage;
    }

    @Override
    public void setNextInChain(Chain nextInChain) {
        this.nextInChain = nextInChain;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers)
            observer.update(this);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
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
