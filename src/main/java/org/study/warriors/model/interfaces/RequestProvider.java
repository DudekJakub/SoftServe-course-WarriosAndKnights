package org.study.warriors.model.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Request;

public interface RequestProvider {

    default void makeRequest(Request request) {
        final Logger LOGGER = LoggerFactory.getLogger(RequestProvider.class);
        final var target = request.getTargetChain();

        LOGGER.trace(Request.REQUEST_SENDING_ATTEMPT, this, request.REQUEST_CLASS_NAME);
        if (target.getNextInChain() != null) {
            LOGGER.trace(Request.REQUEST_SENT, this, request.REQUEST_CLASS_NAME);
            target.getNextInChain().handleRequest(request);
        } else {
            LOGGER.trace(Request.REQUEST_ENDED, request.getHandlersSize() == 0 ? "NO ONCE COULD HANDLE THIS REQUEST" : request.getHandlersSize() + " handlers");
        }
    }
}
