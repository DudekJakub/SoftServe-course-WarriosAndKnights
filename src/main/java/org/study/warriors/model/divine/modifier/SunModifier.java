package org.study.warriors.model.divine.modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.divine.DivineVampire;

public class SunModifier implements DivineModifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(SunModifier.class);

    @Override
    public void modifyVampirism(DivineVampire vampire) {
        var vampirismModifierForDayTime = 30;
        var vampirismModifierForNightTime = 50;
        var currentVampirism = vampire.getVampirism();

        if (vampire.isDayTime() && checkIfModifierMustBeApplied(vampirismModifierForDayTime, currentVampirism)) {
            vampire.setVampirism(vampirismModifierForDayTime);
            LOGGER.trace("[DAY TIME] {} is affected by the goddess of the SUN | NEW VAMPIRISM : {}", vampire, vampirismModifierForDayTime);
        } else if (checkIfModifierMustBeApplied(vampirismModifierForNightTime, currentVampirism)){
            vampire.setVampirism(vampirismModifierForNightTime);
            LOGGER.trace("[NIGHT TIME] {}'s vampirism has been weakened | NEW VAMPIRISM : {}", vampire, vampirismModifierForNightTime);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
