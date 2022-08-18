package org.study.warriors.model.divine;

import org.study.warriors.model.divine.buff.Buff;
import org.study.warriors.model.divine.modifier.DivineModifier;
import org.study.warriors.model.divine.spell.Spell;
import org.study.warriors.model.interfaces.IWarrior;

import java.util.List;
import java.util.Set;

public interface DivineSoldier extends IWarrior {
    Set<Buff> getBuffs();
    Set<Spell> getSpells();

    void setDivineModifier(DivineModifier divineModifier);
    void setResistanceActive(boolean active);

    default void addBuff(Buff buff){
        getBuffs().add(buff);
    }

    default void addSpell(Spell spell) {
        getSpells().add(spell);
    }

    default void removeBuff(Buff buff){
        getBuffs().removeIf(currentBuff -> !currentBuff.isReadyToUse());
    }

    default void removeSpell(Spell spell) {
        getSpells().remove(spell);
    }
}
