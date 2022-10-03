package org.study.warriors.model.divine.modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.divine.DivineVampire;

public class MoonModifier implements DivineModifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoonModifier.class);

    @Override
    public void modifyVampirism(DivineVampire vampire) {
        var vampirismModifierForNightTime = 60;
        var currentVampirism = vampire.getVampirism();

        if (!vampire.isDayTime() && checkIfModifierMustBeApplied(vampirismModifierForNightTime, currentVampirism)) {
            vampire.setVampirism(vampirismModifierForNightTime);
            LOGGER.trace("[NIGHT TIME] {} is affected by the goddess of the MOON | NEW VAMPIRISM : {}", vampire, vampirismModifierForNightTime);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
