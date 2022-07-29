package org.study.warriors.model;

public class Defender extends Warrior {

    static final int INITIAL_HEALTH = 60;
    static final int ATTACK = 3;
    static final int DEFENSE = 2;

    private final int defense;

    public Defender() {
        this(INITIAL_HEALTH, ATTACK, DEFENSE);
    }

    public Defender(int health, int attack, int defense) {
        super(health, attack);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        var reducedDamage = damage - getDefense();

        if (damage > getDefense()) {
            setHealth(getHealth() - reducedDamage);
        }
    }
}
