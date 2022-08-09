package org.study.warriors.model.request;

public interface Chain extends RequestProcessor {
    void setNextInChain(Chain nextInChain);
    void setPreviousInChain(Chain previousInChain);
    Chain getNextInChain();
    Chain getPreviousInChain();
}
