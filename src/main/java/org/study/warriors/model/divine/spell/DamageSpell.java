package org.study.warriors.model.divine.spell;

import org.study.warriors.model.divine.DivineWarrior;

public interface DamageSpell extends Spell {

    void castDamageSpell(DivineWarrior enemy);
}
