package org.study.warriors.model;

import org.study.warriors.model.interfaces.Chain;

import java.util.HashSet;
import java.util.Set;

public abstract class Request {

    public final String REQUEST_CLASS_NAME = this.getClass().getSimpleName();
    public static final String REQUEST_SENDING_ATTEMPT = "{} starts an attempt to send a request: {}";
    public static final String REQUEST_SENT = "SUCCESS! {} has sent request: {}";
    public static final String REQUEST_ENDED = "END OF REQUEST! Request has been handled by: {}";
    public static final String REQUEST_HANDLED_BY_NO_ONE = "NO HANDLER COULD PROCESS THIS REQUEST";

    protected boolean isHandled;
    protected final Set<Chain> requestHandlersCollection;
    private final Chain targetChain;

    protected Request(Chain targetChain) {
        this.targetChain = targetChain;
        this.isHandled = false;
        this.requestHandlersCollection = new HashSet<>();
    }

    public boolean isNotFullyHandled() {
        return !isHandled;
    }

    protected void setHandled() {
        this.isHandled = true;
    }

    protected boolean isRequestNotHandledByHandler(Chain handler) {
        return !requestHandlersCollection.contains(handler);
    }

    protected void addHandlerToCheckSet(Chain invoker) {
        requestHandlersCollection.add(invoker);
    }

    public int getHandlersSize() {
        return requestHandlersCollection.size();
    }

    public Chain getTargetChain() {
        return targetChain;
    }
}
