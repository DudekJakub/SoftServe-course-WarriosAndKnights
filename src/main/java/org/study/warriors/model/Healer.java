package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.CanHeal;
import org.study.warriors.model.interfaces.IWarrior;

public class Healer extends Warrior implements CanHeal {

    private static final Logger LOGGER = LoggerFactory.getLogger(Healer.class);

    static final int INITIAL_HEALTH = 60;
    static final int ATTACK = 0;

    public Healer() {
        this(INITIAL_HEALTH, ATTACK);
    }

    public Healer(int health, int attack) {
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
        LOGGER.trace("{} would like to attack the target {}, but he is so very pacifist!", this, target);
    }

    @Override
    public void handleRequest(Request request) {

        if (request instanceof RequestHealerCureAlly requestHHA) {
            LOGGER.trace("{} (next in line) is handling the request HEAL ALLY...", this);
            heal((IWarrior) getPreviousInChain());
            requestHHA.addHandlerToCheckSet(this);
            LOGGER.trace("HEAL ALLY request processed!");

            if (getNextInChain() != null) {
                passRequest(getNextInChain(), request);
            } else {
                LOGGER.trace(Request.REQUEST_ENDED, request.getHandlersSize() > 0 ? request.getHandlersSize() : Request.REQUEST_HANDLED_BY_NO_ONE);
            }
        } else {
            super.handleRequest(request);
        }
    }
}
