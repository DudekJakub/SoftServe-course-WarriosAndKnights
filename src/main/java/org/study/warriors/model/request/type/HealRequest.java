package org.study.warriors.model.request.type;

import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.Request;

public class HealRequest extends Request {

    private final int invocationLimit;

    public HealRequest(IWarrior requestSender) {
        super(requestSender);
        this.invocationLimit = 1;
    }

    @Override
    public int getInvocationLimit() {
        return invocationLimit;
    }
}
