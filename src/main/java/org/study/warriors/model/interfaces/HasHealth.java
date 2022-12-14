package org.study.warriors.model.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.damage.IDamage;

public interface HasHealth {

    Logger LOGGER = LoggerFactory.getLogger(IWarrior.class);

    int getHealth();
    int getInitialHealth();
    int getLastReceivedDamage();

    void setHealth(int health);
    void setLastReceivedDamage(int damage);

    default void reduceHealthBasedOnDamage(int damage) {
        setHealth(getHealth() - damage);
        setLastReceivedDamage(damage);
        LOGGER.trace("{} HP has been reduced by {} | HP = {}", this, damage, getHealth());
    }

    default void receiveDamage(IDamage damage, IWarrior dealer) {
        LOGGER.trace("{} is receiving damage {}", this, damage.getHitPoints());
        reduceHealthBasedOnDamage(damage.getHitPoints());
    }

    default void increaseHealthBasedOnHeal(int heal) {
        setHealth(Math.min(getInitialHealth(), getHealth() + heal));
    }

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
