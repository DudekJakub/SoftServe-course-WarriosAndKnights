package org.study.warriors.model.interfaces;

public interface CanDrainLife {
    int getVampirism();
    void setVampirism(int vampirism);
    void drainLifeBasedOnDealtAttack(IWarrior target);
}
