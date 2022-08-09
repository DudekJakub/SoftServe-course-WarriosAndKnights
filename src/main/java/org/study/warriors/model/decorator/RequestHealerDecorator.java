package org.study.warriors.model.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.CanHeal;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.type.HealRequest;
import org.study.warriors.model.request.IRequest;

public class RequestHealerDecorator extends RequestWarriorDecorator implements CanHeal {

    static final Logger LOGGER = LoggerFactory.getLogger(RequestHealerDecorator.class);

    public RequestHealerDecorator(final IWarrior warrior) {
        super(warrior);
    }

    @Override
    public void processRequest(IRequest request) {
        if (request instanceof HealRequest healRequest) {

            LOGGER.trace("{} is currently process...", request.getRequestName());

            heal(healRequest.getSender(), 2);
        } else {
            super.processRequest(request);
        }
    }
}
