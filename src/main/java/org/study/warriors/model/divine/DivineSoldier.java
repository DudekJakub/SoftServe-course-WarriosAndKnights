package org.study.warriors.model.divine;

import org.study.warriors.model.divine.goddess.Goddess;
import org.study.warriors.model.divine.usable.buff.Buff;
import org.study.warriors.model.divine.modifier.DivineModifier;
import org.study.warriors.model.divine.usable.spell.Spell;
import org.study.warriors.model.interfaces.IWarrior;

import java.util.Set;

public interface DivineSoldier extends IWarrior {
    Set<Buff> getBuffs();
    Set<Spell> getSpells();
    Iterable<IWarrior> getBrothersInArm();

    void setDivineModifier(DivineModifier divineModifier);
    void setResistanceActive(boolean active);
    void setBrothersInArm(Iterable<IWarrior> brothersInArm);

    default void acceptGoddessBlessing(Goddess goddess) {
        goddess.blessDivineWarriorWithDivineSpells(this);
    }

    default void addBuff(Buff buff){
        getBuffs().add(buff);
    }

    default void addSpell(Spell spell) {
        getSpells().add(spell);
    }

    default void removeBuff(Buff buff) {
        getBuffs().remove(buff);
    }

    default void removeSpell(Spell spell) {
        getSpells().remove(spell);
    }
}
