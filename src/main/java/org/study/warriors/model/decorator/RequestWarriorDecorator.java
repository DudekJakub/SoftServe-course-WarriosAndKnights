package org.study.warriors.model.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Warrior;
import org.study.warriors.model.damage.IPiercing;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.Chain;
import org.study.warriors.model.request.type.DamageRequest;
import org.study.warriors.model.request.type.HealRequest;
import org.study.warriors.model.request.type.IRequest;


public class RequestWarriorDecorator implements IWarrior, Chain, Cloneable {

    static final Logger LOGGER = LoggerFactory.getLogger(RequestWarriorDecorator.class);

    protected IWarrior decorated;
    private Chain nextInChain;
    private Chain previousInChain;

    public RequestWarriorDecorator(final IWarrior warrior) {
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
    public void hit(IWarrior target) {
        decorated.hit(target);
        sendRequest(new HealRequest(this), getNextInChain());
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        decorated.reduceHealthBasedOnDamage(damage);
    }

    @Override
    public void enlargeHealthBasedOnHeal(int heal) {
        decorated.enlargeHealthBasedOnHeal(heal);
    }

    @Override
    public void processRequest(IRequest request) {
        if (request instanceof DamageRequest damageRequest) {
            var damageType = damageRequest.getDamageType();

            LOGGER.trace("{} is currently process...", request.getRequestName());

            int initialHealth = getHealth();
            receiveDamage(damageType);
            int dealtDamage = initialHealth - getHealth();

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
    public RequestWarriorDecorator clone() {
        try {
            RequestWarriorDecorator clone = (RequestWarriorDecorator) super.clone();
            clone.decorated = ((Warrior) this.decorated).clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
