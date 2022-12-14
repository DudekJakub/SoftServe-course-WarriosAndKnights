package org.study.warriors.model.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CanHeal {

    Logger LOGGER = LoggerFactory.getLogger(CanHeal.class);

    int getHealPower();
    int getHealEssence();
    void setHealPower(int healPower);
    void setHealEssence(int healEssence);

    default void heal(HasHealth target, int healValue) {
        target.increaseHealthBasedOnHeal(healValue);
        LOGGER.trace("{} has healed {} by {} | NEW HP = {}", this, target, healValue, target.getHealth());
    }
}
