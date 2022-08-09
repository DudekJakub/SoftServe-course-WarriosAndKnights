package org.study.warriors.model.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.damage.SimpleDamage;

public interface IWarrior extends HasHealth, CanAttack {

    Logger LOGGER = LoggerFactory.getLogger(IWarrior.class);

    int getInitialHealth();

    default void hit(IWarrior target) {
        LOGGER.trace("---- {} IN ACTION ----", this);
        LOGGER.trace("{} hits {}", this, target);
        target.receiveDamage(new SimpleDamage(getAttack()));
    }

    default void receiveDamage(IDamage damage) {
        LOGGER.trace("{} is receiving damage {}", this, damage.getHitPoints());
        reduceHealthBasedOnDamage(damage.getHitPoints());
    }
}
