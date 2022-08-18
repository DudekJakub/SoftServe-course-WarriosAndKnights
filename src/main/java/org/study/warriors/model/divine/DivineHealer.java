package org.study.warriors.model.divine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Healer;
import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.interfaces.CanHeal;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.IRequest;
import org.study.warriors.model.request.type.HealRequest;

public class DivineHealer extends DivineWarrior implements CanHeal {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivineHealer.class);

    public DivineHealer(IWarrior decorated) {
        super(decorated);
        if (!(decorated instanceof Healer)) throw new IllegalStateException("Given object to decorate is not Healer!");
    }

    @Override
    public int getHealPower() {
        return ((Healer) decorated).getHealPower();
    }

    @Override
    public void setHealPower(int healPower) {
        ((Healer) decorated).setHealPower(healPower);
    }

    @Override
    public void processRequest(IRequest request) {
        if (request instanceof HealRequest && isAlive()) {
            LOGGER.trace("{} is currently process...", request.getRequestName());
            heal(request.getRequestSender(), getHealPower());
            LOGGER.trace("{} has been processed!", request.getRequestName());
        } else {
            super.processRequest(request);
        }
    }
}
