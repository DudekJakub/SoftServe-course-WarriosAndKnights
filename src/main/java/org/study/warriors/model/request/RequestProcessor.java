package org.study.warriors.model.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.request.type.IRequest;

public interface RequestProcessor {

    Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);

    default void sendRequest(IRequest request, Chain target) {
        LOGGER.trace("{} is being prepared{} by {}...", request.getRequestName(), request.getInvocationCounter() > 0 ? " to pass forward" : "",  this);
        if (target != null) {
            request.increaseInvocationCounter();
            LOGGER.trace("{} has been {} to {}", request.getRequestName(), request.getInvocationCounter() > 1 ? "forwarded" : "sent" , target);
            target.processRequest(request);
        } else {
            LOGGER.trace("{} hasn't been sent (no one more in chain)", request.getRequestName());
        }
    }

    void processRequest(IRequest request);
}
