package org.study.warriors.model.damage;

public interface IPiercing {
    int getCounter();
    void decreaseCounter();

    default int getPierceDamageBasedOnPreviousDealtDamage(int previousDealtDamage) {
        return previousDealtDamage * 50 / 100;
    }
}
