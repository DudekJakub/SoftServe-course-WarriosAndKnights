package org.study.warriors.model.interfaces;

import org.study.warriors.model.Request;

public interface Chain {
    void setNextInChain(Chain nextInChain);
    void setPreviousInChain(Chain previousInChain);
    void handleRequest(Request request);
    Chain getNextInChain();

    default void passRequest(IWarrior target, Request request) {
        target.handleRequest(request);
    }
}
