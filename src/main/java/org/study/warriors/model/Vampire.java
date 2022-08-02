package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.IVampire;
import org.study.warriors.model.interfaces.IWarrior;

public class Vampire extends Warrior implements IVampire {

    private static final Logger LOGGER = LoggerFactory.getLogger(Vampire.class);

    static final int INITIAL_HEALTH = 40;
    static final int ATTACK = 4;
    static final int VAMPIRISM = 50;

    private final int vampirism;

    public Vampire() {
        this(INITIAL_HEALTH, ATTACK, VAMPIRISM);
    }

    public Vampire(int health, int attack, int vampirism) {
        super(health, attack);
        this.vampirism = vampirism;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public int getVampirism() {
        return VAMPIRISM;
    }

    @Override
    public void hit(IWarrior target) {
        super.hit(target);
        drainLifeBasedOnAttack(target);
    }

    @Override
    public void drainLifeBasedOnAttack(IWarrior target) {
        var healthValueAfterDrainLife = getHealth() + ((target.getLastReceivedDamage() * getVampirism())/100);
        setHealth(Math.min(INITIAL_HEALTH, healthValueAfterDrainLife));
        LOGGER.trace("{} drains life from his final damage dealt ({}) and heals himself by ({} * {})/100, new HP = {}", this, target.getLastReceivedDamage(), target.getLastReceivedDamage(), getVampirism(), getHealth());
    }
}
