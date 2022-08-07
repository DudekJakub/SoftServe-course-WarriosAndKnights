package org.study.warriors.model;

import org.study.warriors.model.interfaces.Chain;
import org.study.warriors.model.interfaces.RequestHandlerLimiter;

public class RequestLancerPierceAttack extends Request implements RequestHandlerLimiter {

    private final int pierceDamage;
    private final int handleLimit;

    public RequestLancerPierceAttack(Chain targetChain) {
        super(targetChain);
        this.pierceDamage = 3;
        this.handleLimit = 5;
    }

    public int getPierceDamageForHandler(Chain handler) {
        addHandlerToCheckSet(handler);
        if (isHandleLimitReached(requestHandlersCollection, handleLimit)) setRequestAsHandled();
        return pierceDamage;
    }

    @Override
    public void setRequestAsHandled() {
        setHandled();
    }
}
