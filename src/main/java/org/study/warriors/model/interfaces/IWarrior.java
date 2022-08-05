package org.study.warriors.model.interfaces;

public interface IWarrior extends HasHealth, CanAttack, Chain {
    void hit(IWarrior target);
    int getInitialHealth();
    default void receiveDamage(CanAttack damageDealer) {
        reduceHealthBasedOnDamage(damageDealer.getAttack());
    }
}
