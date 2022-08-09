package org.study.warriors.model.damage;

public class PierceDamage extends SimpleDamage implements IPiercing {

    private int counter = 2;

    public PierceDamage(int hitPoints) {
        super(hitPoints);
    }

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public void decreaseCounter() {
        counter -= 1;
    }
}
