package org.study.warriors.model.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Healer;
import org.study.warriors.model.interfaces.CanHeal;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.type.HealRequest;
import org.study.warriors.model.request.IRequest;

public class HealerDecorator extends WarriorDecorator implements CanHeal {

    static final Logger LOGGER = LoggerFactory.getLogger(HealerDecorator.class);

    public HealerDecorator(final IWarrior warrior) {
        super(warrior);
    }

    @Override
    public void processRequest(IRequest request) {
        if (request instanceof HealRequest healRequest) {

            LOGGER.trace("{} is currently process...", request.getRequestName());

            heal(healRequest.getSender(), ((Healer) decorated).getHealPower());
        } else {
            super.processRequest(request);
        }
    }

    @Override
    public int getHealPower() {
        return ((Healer) decorated).getHealPower();
    }

    @Override
    public void setHealPower(int healPower) {
        ((Healer) decorated).setHealPower(healPower);
    }
}
