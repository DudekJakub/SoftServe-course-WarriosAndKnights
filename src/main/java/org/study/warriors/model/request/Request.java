package org.study.warriors.model.request;

public class Request implements IRequest {

    protected int invocationCounter = 0;

    @Override
    public String getRequestName() {
        return getClass().getSimpleName();
    }

    @Override
    public int getInvocationCounter() {
        return invocationCounter;
    }

    @Override
    public void increaseInvocationCounter() {
        invocationCounter = invocationCounter + 1;
    }
}
