package org.study.warriors.model.interfaces;

public interface HasHealth {
    int getHealth();
    void reduceHealthBasedOnDamage(int damage);

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
