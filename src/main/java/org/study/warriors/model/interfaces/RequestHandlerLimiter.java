package org.study.warriors.model.interfaces;

import java.util.Collection;

public interface RequestHandlerLimiter {
    default boolean isHandleLimitReached(Collection<?> handlers, int limit) {
        return handlers.size() == limit;
    }
    void setRequestAsHandled();
}
