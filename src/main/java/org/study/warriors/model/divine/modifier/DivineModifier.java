package org.study.warriors.model.divine.modifier;

import org.study.warriors.model.divine.DivineVampire;

public interface DivineModifier {
    void modifyVampirism(DivineVampire vampire);

    default boolean checkIfModifierMustBeApplied(int modifier, int currentValue) {
        return modifier != currentValue;
    }
}
