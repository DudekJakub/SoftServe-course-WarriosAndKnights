package org.study.warriors.model.divine.usable.spell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.divine.DivineHealer;
import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.usable.Usable;
import org.study.warriors.model.divine.usable.buff.PierceResistanceBuff;

public class PierceResistSpell extends Usable implements SupportSpell {

    private static final Logger LOGGER = LoggerFactory.getLogger(PierceResistSpell.class);

    public PierceResistSpell(DivineSoldier holder) {
        super(holder);
        this.invocationCounter = 0;
        this.invocationLimit = 1;
    }

    @Override
    public void apply(DivineSoldier ally) {
        var isAllyHealer = ally instanceof DivineHealer;
        var pierceResistanceBuff = new PierceResistanceBuff(ally);

        if (isAllyHealer && isReadyToUse && !ally.getBuffs().contains(pierceResistanceBuff)) {
            invocationCounter++;
            checkActiveStatus();
            ally.addBuff(pierceResistanceBuff);
            LOGGER.trace("PIERCE_RESIST_SPELL is being casted...");
            LOGGER.trace("PIERCE_RESIST_SPELL casted to {} successfully! {} receives {}!", ally, ally, pierceResistanceBuff);
        } else if (!isReadyToUse) {
            holder.removeSpell(this);
            LOGGER.trace("PIERCE_RESIST_SPELL has been already used and now removed! [INVOCATION LIMIT REACHED]");
        } else {
            LOGGER.trace("PIERCE_RESIST_SPELL cannot be cast: {} is not a HEALER!", ally);
        }
    }
}
