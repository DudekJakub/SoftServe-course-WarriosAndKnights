package org.study.warriors.model.divine.spell;

import org.study.warriors.model.divine.DivineSoldier;

public class PassDrainedLifeSpell extends AbstractSpell implements Spell {

    private final int invocationLimit = 3;
    private final boolean isReadyToUse = invocationCounter < invocationLimit;

    public PassDrainedLifeSpell(DivineSoldier holder) {
        super(holder);
    }

    @Override
    public void apply(DivineSoldier ally) {

    }
}
