package org.study.warriors.model.divine.usable;

public interface InvocationLimiter {
    boolean isReadyToUse();
    void setReadyToUse(boolean isReadyToUse);
}
