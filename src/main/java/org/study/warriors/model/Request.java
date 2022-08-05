package org.study.warriors.model;

import org.study.warriors.model.interfaces.Chain;

import java.util.HashSet;
import java.util.Set;

public abstract class Request {

    final String REQUEST_CLASS_NAME = this.getClass().getSimpleName();
    static final String REQUEST_SENDING_ATTEMPT = "{} starts an attempt to send a request: {}";
    static final String REQUEST_SENT = "SUCCESS! {} has sent request: {}";
    static final String REQUEST_ENDED = "END OF REQUEST! Request has been handled by: {} handlers";
    protected int maxCallCounterValue;
    protected boolean isHandled;
    protected final Set<Chain> requestHandlers;

    public Request(int maxCallCounterValue) {
        this.maxCallCounterValue = maxCallCounterValue;
        this.isHandled = false;
        this.requestHandlers = new HashSet<>();
    }

    public boolean isNotFullyHandled() {
        return !isHandled;
    }

    private void setHandled() {
        this.isHandled = true;
    }

    protected void compareHandlersSizeToMaxCallValue() {
        if (requestHandlers.size() == maxCallCounterValue) {
            setHandled();
        }
    }

    protected boolean isRequestAlreadyHandledByHandler(Chain handler) {
        return requestHandlers.contains(handler);
    }

    protected void addHandlerToCheckSet(Chain invoker) {
        requestHandlers.add(invoker);
    }

    public int getHandlersSize() {
        return requestHandlers.size();
    }
}
