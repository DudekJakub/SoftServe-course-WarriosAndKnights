package org.study.warriors.model.request.type;

import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.Request;

public class HealRequest extends Request {

    private final IWarrior sender;

    public HealRequest(IWarrior sender) {
        this.sender = sender;
    }

    public IWarrior getSender() {
        return sender;
    }
}
