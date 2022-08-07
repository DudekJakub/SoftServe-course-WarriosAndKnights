package org.study.warriors.model.interfaces;

public interface CanHeal {

    default void heal(HasHealth target) {
        target.enlargeHealthBasedOnHeal(2);
    }
}
