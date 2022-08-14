package org.study.warriors.model.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Warrior;
import org.study.warriors.model.equipment.weapon.WeaponEquipment;
import org.study.warriors.model.damage.IPiercing;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.observer.Observer;
import org.study.warriors.model.request.Chain;
import org.study.warriors.model.request.type.DamageRequest;
import org.study.warriors.model.request.type.HealRequest;
import org.study.warriors.model.request.IRequest;


public class WarriorDecorator implements IWarrior, Chain, Cloneable {

    static final Logger LOGGER = LoggerFactory.getLogger(WarriorDecorator.class);

    protected IWarrior decorated;
    private Chain nextInChain;
    private Chain previousInChain;

    public WarriorDecorator(final IWarrior warrior) {
        this.decorated = warrior;
    }

    @Override
    public int getAttack() {
        return decorated.getAttack();
    }

    @Override
    public int getHealth() {
        return decorated.getHealth();
    }

    @Override
    public int getLastReceivedDamage() {
        return decorated.getLastReceivedDamage();
    }

    @Override
    public Chain getNextInChain() {
        return nextInChain;
    }

    @Override
    public Chain getPreviousInChain() {
        return previousInChain;
    }

    @Override
    public int getInitialHealth() {
        return decorated.getInitialHealth();
    }

    @Override
    public WeaponEquipment getEquipment() {
        return decorated.getEquipment();
    }

    @Override
    public void setAttack(int attack) {
        decorated.setAttack(attack);
    }

    @Override
    public void setHealth(int health) {
        decorated.setHealth(health);
    }

    @Override
    public void setLastReceivedDamage(int damage) {
        decorated.setLastReceivedDamage(damage);
    }

    @Override
    public void setNextInChain(Chain nextInChain) {
        this.nextInChain = nextInChain;
    }

    @Override
    public void setPreviousInChain(Chain previousInChain) {
        this.previousInChain = previousInChain;
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        decorated.reduceHealthBasedOnDamage(damage);
    }

    @Override
    public void increaseHealthBasedOnHeal(int heal) {
        decorated.increaseHealthBasedOnHeal(heal);
    }

    @Override
    public void registerObserver(Observer observer) {
        decorated.registerObserver(observer);
    }

    @Override
    public void notifyObserver() {
        decorated.notifyObserver();
    }

    @Override
    public void removeObserver(Observer observer) {
        decorated.removeObserver(observer);
    }

    @Override
    public void hit(IWarrior target) {
        decorated.hit(target);
        sendRequest(new HealRequest(this), getNextInChain());
    }

    @Override
    public void processRequest(IRequest request) {
        if (request instanceof DamageRequest damageRequest) {
            var damageType = damageRequest.getDamageType();

            LOGGER.trace("{} is currently process...", request.getRequestName());

            int healthBefore = getHealth();
            receiveDamage(damageType);
            int dealtDamage = healthBefore - getHealth();

            if (damageType instanceof IPiercing piercing && piercing.getCounter() > 1) {
                piercing.decreaseCounter();
                var pierceDamage = piercing.getPierceDamageBasedOnPreviousDealtDamage(dealtDamage);
                damageType.setHitPoints(pierceDamage);
                sendRequest(request, getNextInChain());
            }
            return;
        }
        LOGGER.trace("{} cannot be processed by {}", request.getRequestName(), this);
        sendRequest(request, nextInChain);
    }

    @Override
    public String toString() {
        return decorated.toString();
    }

    @Override
    public WarriorDecorator clone() {
        try {
            WarriorDecorator clone = (WarriorDecorator) super.clone();
            clone.decorated = ((Warrior) this.decorated).clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
