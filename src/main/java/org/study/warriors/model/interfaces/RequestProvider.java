package org.study.warriors.model.interfaces;

import org.study.warriors.model.Request;

public interface RequestProvider {
    void makeRequest(IWarrior target, Request request);
}
