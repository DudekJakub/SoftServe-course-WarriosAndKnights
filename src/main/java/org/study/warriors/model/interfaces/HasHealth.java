package org.study.warriors.model.interfaces;

public interface HasHealth {
    int getHealth();
    int getLastReceivedDamage();
    void setLastReceivedDamage(int damage);
    void reduceHealthBasedOnDamage(int damage);
    void enlargeHealthBasedOnHeal(int heal);
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
