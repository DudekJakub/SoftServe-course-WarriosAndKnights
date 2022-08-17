package org.study.warriors.model.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.damage.SimpleDamage;
import org.study.warriors.model.observer.Observable;
import org.study.warriors.model.request.Chain;
import org.study.warriors.model.request.type.DamageRequest;
import org.study.warriors.model.request.type.HealRequest;

public interface IWarrior extends HasHealth, CanAttack, CanWieldWeapon, Chain, Observable {

    Logger LOGGER = LoggerFactory.getLogger(IWarrior.class);

    int getInitialHealth();

    default void hit(IWarrior target) {
        LOGGER.trace("---- {} IN ACTION ----", this);
        LOGGER.trace("{} hits {}", this, target);
        sendRequest(new DamageRequest(new SimpleDamage(getAttack())), target);
        sendRequest(new HealRequest(this), getNextInChain());
    }
}
