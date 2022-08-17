package org.study.warriors.model.request;

import org.study.warriors.model.interfaces.IWarrior;

public interface IRequest {
    IWarrior getRequestSender();
    String getRequestName();
    int getInvocationCounter();
    int getInvocationLimit();
    void increaseInvocationCounter();
}
