package org.study.warriors.model;

import org.study.warriors.model.interfaces.Chain;

public class RequestHealerCureAlly extends Request {

    private final int healValue;

    public RequestHealerCureAlly(Chain targetChain) {
        super(targetChain);
        this.healValue = 2;
    }

    public int getHealValue() {
        return healValue;
    }
}
