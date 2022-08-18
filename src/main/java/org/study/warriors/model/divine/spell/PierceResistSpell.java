package org.study.warriors.model.divine.spell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.divine.DivineHealer;
import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.buff.PierceResistanceBuff;

public class PierceResistSpell extends AbstractSpell implements SupportSpell {

    private static final Logger LOGGER = LoggerFactory.getLogger(PierceResistSpell.class);

    private final int invocationLimit;
    private boolean isReadyToUse = true;

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
            LOGGER.trace("PIERCE_RESIST_SPELL casted to {} successfully!", ally);
        } else {
            LOGGER.trace("PIERCE_RESIST_SPELL cannot be cast: {}", !isAllyHealer ? "ALLY BEHIND IS NOT HEALER" : "INVOCATION LIMIT REACHED OR HEALER ALREADY BUFFED");
            if (!isReadyToUse) {
                holder.removeSpell(this);
                LOGGER.trace("PIERCE_RESIST_SPELL has been used and removed! [INVOCATION LIMIT REACHED]");
            }
        }
    }

    private void checkActiveStatus() {
        if (invocationCounter == invocationLimit) {
            setReadyToUse(false);
        }
    }

    @Override
    public void setReadyToUse(boolean isReadyToUse) {
        this.isReadyToUse = isReadyToUse;
    }
}
