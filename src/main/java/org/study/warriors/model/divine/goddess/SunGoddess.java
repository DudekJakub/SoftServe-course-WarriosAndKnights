package org.study.warriors.model.divine.goddess;

import org.study.warriors.model.divine.DivineHealer;
import org.study.warriors.model.divine.DivineLancer;
import org.study.warriors.model.divine.DivineVampire;
import org.study.warriors.model.divine.modifier.DivineModifier;
import org.study.warriors.model.divine.modifier.SunModifier;
import org.study.warriors.model.divine.usable.spell.EmpowerAlliesHealerPowerSpell;
import org.study.warriors.model.divine.usable.spell.PassDrainedLifeSpell;
import org.study.warriors.model.divine.usable.spell.PierceResistSpell;
import org.study.warriors.model.divine.usable.spell.Spell;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SunGoddess implements Goddess, Sun {

    private static SunGoddess instance = null;
    public static GoddessType divineType = GoddessType.SUN;

    private final DivineModifier divineModifier = new SunModifier();
    private final Map<Class<?>, Set<Spell>> sunSpells = new HashMap<>(
            Map.of(
                    DivineLancer.class, Set.of(new PierceResistSpell(null)),
                    DivineVampire.class, Set.of(new PassDrainedLifeSpell(null)),
                    DivineHealer.class, Set.of(new EmpowerAlliesHealerPowerSpell(null)))
            );

    private SunGoddess() {}

    public static SunGoddess getInstance() {
        if (instance == null) {
            instance = new SunGoddess();
        }
        return instance;
    }

    @Override
    public Map<Class<?>, Set<Spell>> getSunSpells() {
        return sunSpells;
    }

    @Override
    public DivineModifier getDivineModifier() {
        return divineModifier;
    }

    @Override
    public GoddessType getDivineType() {
        return divineType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
