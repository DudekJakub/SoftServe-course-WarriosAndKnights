package org.study.warriors.model.request;

import org.study.warriors.model.damage.IPiercing;
import org.study.warriors.model.interfaces.HasHealth;
import org.study.warriors.model.observer.Observable;
import org.study.warriors.model.request.type.DamageRequest;

public interface Chain extends HasHealth {
    void setNextInChain(Chain nextInChain);
    Chain getNextInChain();

    default void sendRequest(IRequest request, Chain target) {
        LOGGER.trace("{} is being prepared{} by {}...", request.getRequestName(), request.getInvocationCounter() > 0 ? " to pass forward" : "",  this);

        if (target != null && request.getInvocationCounter() < request.getInvocationLimit()) {
            request.increaseInvocationCounter();
            LOGGER.trace("{} has been {} to {}", request.getRequestName(), request.getInvocationCounter() > 1 ? "forwarded" : "sent" , target);
            target.processRequest(request);
        } else {
            LOGGER.trace("{} hasn't been sent ({})", request.getRequestName(), target == null ? "no one more in chain" : "request invocation: " + request.getInvocationLimit() + " limit has been reached");
        }
    }

    default void processRequest(IRequest request) {
        if (request instanceof DamageRequest damageRequest) {
            var damageType = damageRequest.getDamageType();

            LOGGER.trace("{} is currently process...", request.getRequestName());

            int healthBefore = getHealth();
            receiveDamage(damageType, request.getRequestSender());
            int dealtDamage = healthBefore - getHealth();

            if (damageType instanceof IPiercing piercing && piercing.getCounter() > 1) {
                piercing.decreaseCounter();
                var pierceDamage = piercing.getPierceDamageBasedOnPreviousDealtDamage(dealtDamage);
                damageType.setHitPoints(pierceDamage);
                sendRequest(request, getNextInChain());
            }
            if (this instanceof Observable observable) {
                observable.notifyObserver();
            }
            return;
        }
        LOGGER.trace("{} cannot be processed by {}", request.getRequestName(), this);
        sendRequest(request, getNextInChain());
    }
}
