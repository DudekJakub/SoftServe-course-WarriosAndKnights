package org.study.warriors.model.divine.goddess;

import org.study.warriors.model.divine.modifier.DivineModifier;
import org.study.warriors.model.divine.modifier.SunModifier;

public class SunGoddess implements Goddess, Sun {

    private static SunGoddess instance = null;

    private final DivineModifier divineModifier = new SunModifier();

    private SunGoddess() {};

    public static SunGoddess getInstance() {
        if (instance == null) {
            instance = new SunGoddess();
        }
        return instance;
    }

    public DivineModifier getDivineModifier() {
        return divineModifier;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
