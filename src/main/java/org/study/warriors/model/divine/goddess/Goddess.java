package org.study.warriors.model.divine.goddess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.modifier.DivineModifier;
import org.study.warriors.model.divine.usable.spell.Spell;

import java.util.Map;
import java.util.Set;

public interface Goddess {

    Logger LOGGER = LoggerFactory.getLogger(Goddess.class);

    Map<Class<?>, Set<Spell>> getSunSpells();
    DivineModifier getDivineModifier();
    GoddessType getDivineType();

    default void blessDivineWarriorWithDivineSpells(final DivineSoldier divineSoldier) {
        LOGGER.trace("{} is blessing {} with spells!", this, divineSoldier);
        var divineWarriorInstance = divineSoldier.getClass();
        getSunSpells().entrySet()
                .stream()
                .filter(classSetEntry -> classSetEntry.getKey() == divineWarriorInstance)
                .map(Map.Entry::getValue)
                .forEach(spells -> spells
                        .forEach(spell -> {
                                    spell.setHolder(divineSoldier);
                                    divineSoldier.addSpell(spell);
                                    LOGGER.trace("{} was gifted with {}", divineSoldier, spell);
                                }
                        ));
    }

    enum GoddessType {
        SUN, MOON
    }
}
