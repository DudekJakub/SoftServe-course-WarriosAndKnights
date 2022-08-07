package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.*;

/** Skeleton class for all fighters (so far 04/08).
 *  Main function is dealing and receiving damage:
 *      1) hit -> this method uses receiveDamage() on the target
 *      2) receiveDamage -> ^^ uses reduceHealthBasedOnDamage() to decrease amount of target's health
 *      3) reduceHealthBasedOnDamage -> ^^ uses setHealth() to determinate final amount of fighter's HP after duel
 **/


public class Warrior implements Unit, IWarrior, Cloneable, RequestProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(Warrior.class);
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 5;

    private int health;
    private int attack;
    private int lastReceivedDamage = 0;
    private Chain nextInChain;
    private Chain previousInChain;

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
        return this.health;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }

    @Override
    public Chain getNextInChain() {
        return nextInChain;
    }

    @Override
    public int getLastReceivedDamage() {
        return lastReceivedDamage;
    }

    public void setAttack(int newAttack) {
        this.attack = newAttack;
    }

    public Chain getPreviousInChain() {
        return previousInChain;
    }

    public void setPreviousInChain(Chain previousInChain) {
        this.previousInChain = previousInChain;
    }

    @Override
    public void setLastReceivedDamage(int damage) {
        this.lastReceivedDamage = damage;
    }


    /** To avoid referring directly to 'this.health' variable, we can hide this action behind setters*/
    public void setHealth(int health) {
        this.health = health;
    }


    /** We shift responsibility of dealing the damage to method 'receiveDamage'.
     *  Thanks to that we are able to use IWarrior interface (polymorphism) and handle every Unit which is able to fight or at least HasHealth in similar way */
    @Override
    public void hit(IWarrior target) {
        LOGGER.trace("{} hits {}", this, target);
        target.receiveDamage(this);
        makeRequest(new RequestHealerCureAlly(this));
    }


    /** This method use another interface 'CanAttack'. Thanks to that in future we would be able to define different value of damage depending on dmgDealer's attack */
    @Override
    public void receiveDamage(CanAttack damageDealer) {
        LOGGER.trace("{} (victim) is receiving damage {} from {} (attacker)", this, damageDealer.getAttack(), damageDealer);
        reduceHealthBasedOnDamage(damageDealer.getAttack());
    }


    /** Final step of 3-methods process of dealing damage to target unit */
    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        setHealth(getHealth() - damage);
        setLastReceivedDamage(damage);
        LOGGER.trace("{}'s (victim) HP has been reduced by {}", this, damage);
    }

    @Override
    public void enlargeHealthBasedOnHeal(int heal) {
        setHealth(Math.min(INITIAL_HEALTH, getHealth() + heal));
        LOGGER.trace("{} has been healed by {}", this, heal);
    }

    @Override
    public void setNextInChain(Chain nextInChain) {
        this.nextInChain = nextInChain;
    }

    @Override
    public void handleRequest(Request request) {

        if (request instanceof RequestLancerPierceAttack requestLPA && request.isNotFullyHandled() && request.isRequestNotHandledByHandler(this)) {
            LOGGER.trace("{} (next in line) is handling the request DEAL PIERCE ATTACK...", this);
            var pierceDamage = requestLPA.getPierceDamageForHandler(this);
            setHealth(getHealth() - pierceDamage);
            LOGGER.trace("DEAL PIERCE ATTACK request processed! {} has received pierce damage {} from Lancer | HP = {}", this, pierceDamage, getHealth());
        }

        if (getNextInChain() != null) {
            passRequest(getNextInChain(), request);
        } else {
            LOGGER.trace(Request.REQUEST_ENDED, request.getHandlersSize() > 0 ? request.getHandlersSize() : Request.REQUEST_HANDLED_BY_NO_ONE);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Warrior shallowClone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException ignored) {
            LOGGER.trace("This message will never be printed");
        }
        return null;
    }
}
