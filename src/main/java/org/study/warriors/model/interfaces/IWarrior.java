package org.study.warriors.model.interfaces;

public interface IWarrior extends HasHealth, CanAttack{
    void hit(IWarrior target);
    default void receiveDamage(CanAttack damageDealer) {
        reduceHealthBasedOnDamage(damageDealer.getAttack());
    }
}
