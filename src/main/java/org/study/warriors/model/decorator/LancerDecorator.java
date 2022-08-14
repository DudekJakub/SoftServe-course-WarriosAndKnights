package org.study.warriors.model.decorator;

import org.study.warriors.model.damage.PierceDamage;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.Chain;
import org.study.warriors.model.request.type.DamageRequest;
import org.study.warriors.model.request.type.HealRequest;

public class LancerDecorator extends WarriorDecorator {

    public LancerDecorator(final IWarrior warrior) {
        super(warrior);
    }

    @Override
    public void hit(IWarrior target) {
        LOGGER.trace("---- {} IN ACTION ----", this);
        sendRequest(new DamageRequest(new PierceDamage(getAttack())), (Chain) target);
        sendRequest(new HealRequest(this), getNextInChain());
    }
}
