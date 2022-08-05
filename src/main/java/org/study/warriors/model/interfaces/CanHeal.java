package org.study.warriors.model.interfaces;

public interface CanHeal {
    default void healAlly(HasHealth target) {
        target.enlargeHealthBasedOnHeal(2);
    }
}
