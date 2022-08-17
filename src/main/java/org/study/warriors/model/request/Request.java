package org.study.warriors.model.request;

import org.study.warriors.model.interfaces.IWarrior;

public class Request implements IRequest {

    protected int invocationCounter = 0;
    protected int invocationLimit = Integer.MAX_VALUE;
    protected final IWarrior requestSender;

    public Request(IWarrior requestSender) {
        this.requestSender = requestSender;
    }

    @Override
    public IWarrior getRequestSender() {
        return requestSender;
    }

    @Override
    public String getRequestName() {
        return getClass().getSimpleName();
    }

    @Override
    public int getInvocationCounter() {
        return invocationCounter;
    }

    @Override
    public int getInvocationLimit() {
        return invocationLimit;
    }

    @Override
    public void increaseInvocationCounter() {
        if (invocationLimit != -1) invocationCounter = invocationCounter + 1;
    }
}
