package org.study.warriors.model.interfaces;

public interface CanDrainLife {
    int getVampirism();
    int getLastDealtDamage();
    void setVampirism(int vampirism);
    void setLastDealtDamage(int lastDealtDamage);
    void drainLifeBasedOnDealtAttack(IWarrior target);
}
