package org.study.warriors.model.request.type;

public interface IRequest {
    String getRequestName();
    int getInvocationCounter();
    void increaseInvocationCounter();
}
