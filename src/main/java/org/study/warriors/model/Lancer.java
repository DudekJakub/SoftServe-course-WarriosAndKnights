package org.study.warriors.model;

import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.RequestProvider;

public class Lancer extends Warrior implements RequestProvider {

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
        super.hit(target);
        makeRequest(new RequestLancerPierceAttack(target));
    }
}
