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
        makeRequest(this, new RequestHealerCureAlly());
    }


    /** This method use another interface 'CanAttack'. Thanks to that in future we would be able to define different value of damage depending on dmgDealer's attack */
    public void receiveDamage(CanAttack damageDealer) {
        LOGGER.trace("{} (victim) is receiving damage ({}) from {} (attacker)", this, damageDealer.getAttack(), damageDealer);
        reduceHealthBasedOnDamage(damageDealer.getAttack());
    }


    /** Final step of 3-methods process of dealing damage to target unit */
    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        setHealth(getHealth() - damage);
        setLastReceivedDamage(damage);
        LOGGER.trace("{}'s (victim) HP has been reduced by {} and is equals to {} | final damage taken = {}", this, damage, getHealth(), damage);
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
    public void makeRequest(IWarrior target, Request request) {
        LOGGER.trace(Request.REQUEST_SENDING_ATTEMPT, this, request.REQUEST_CLASS_NAME);
        if (target.getNextInChain() != null) {
            LOGGER.trace(Request.REQUEST_SENT, this, request.REQUEST_CLASS_NAME);
            target.getNextInChain().handleRequest(request);
        } else {
            LOGGER.trace(Request.REQUEST_ENDED, request.REQUEST_CLASS_NAME);
        }
    }

    @Override
    public void handleRequest(Request request) {

        if (request instanceof RequestLancerPierceAttack requestLPA && request.isNotFullyHandled() && !request.isRequestAlreadyHandledByHandler(this)) {
            LOGGER.trace("{} (next in line) is handling the request (PIERCE ATTACK)...", this);
            setHealth(getHealth() - requestLPA.getPierceDamage());
            requestLPA.addHandlerToCheckSet(this);
            LOGGER.trace("PIERCE ATTACK request processed! {} has received pierce damage {} from Lancer and its actual HP = {}",
                                                                                                 this, requestLPA.getPierceDamage(), getHealth());
        } else if (nextInChain != null && request.isNotFullyHandled()) {
            nextInChain.handleRequest(request);
        } else {
            LOGGER.trace(Request.REQUEST_ENDED, request.getHandlersSize());
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
            System.out.println("This message will never be printed");
        }
        return null;
    }
}
