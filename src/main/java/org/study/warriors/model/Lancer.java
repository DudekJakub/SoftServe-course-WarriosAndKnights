package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.damage.PierceDamage;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.type.DamageRequest;
import org.study.warriors.model.request.type.HealRequest;

public class Lancer extends Warrior {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lancer.class);
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

    @Override
    public void hit(IWarrior target) {
        LOGGER.trace("---- {} IN ACTION ----", this);
        sendRequest(new DamageRequest(this, new PierceDamage(getAttack())), target);
        sendRequest(new HealRequest(this), getNextInChain());
    }
}
