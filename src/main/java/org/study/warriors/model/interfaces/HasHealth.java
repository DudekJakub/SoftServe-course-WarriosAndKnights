package org.study.warriors.model.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    default void enlargeHealthBasedOnHeal(int heal) {
        setHealth(Math.min(getInitialHealth(), getHealth() + heal));
    }

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
