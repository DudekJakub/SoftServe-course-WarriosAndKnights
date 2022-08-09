package org.study.warriors.model.request;

public interface IRequest {
    String getRequestName();
    int getInvocationCounter();
    void increaseInvocationCounter();
}
