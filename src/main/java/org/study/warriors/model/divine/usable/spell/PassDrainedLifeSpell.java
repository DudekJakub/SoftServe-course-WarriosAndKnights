package org.study.warriors.model.divine.usable.spell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.usable.Usable;
import org.study.warriors.model.interfaces.CanDrainLife;

public class PassDrainedLifeSpell extends Usable implements SupportSpell {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassDrainedLifeSpell.class);

    public PassDrainedLifeSpell(DivineSoldier holder) {
        super(holder);
        this.invocationCounter = 0;
        this.invocationLimit = 3;
    }

    @Override
    public void apply(DivineSoldier ally) {
        if (holder instanceof CanDrainLife vampire && ally != null) {
            invocationCounter++;
            checkActiveStatus();
            var healBasedOnLastDealtDamage = vampire.getLastDealtDamage() / 3;
            ally.setHealth(Math.min(ally.getInitialHealth(), ally.getHealth() + healBasedOnLastDealtDamage));
            LOGGER.trace("PASS_DRAINED_LIFE_SPELL is being casted...");
            LOGGER.trace("PASS_DRAINED_LIFE_SPELL casted to {} successfully! {} healed by {} | NEW HP: {}", ally, ally, healBasedOnLastDealtDamage, ally.getHealth());
        } else if(!isReadyToUse){
            holder.removeSpell(this);
            LOGGER.trace("PASS_DRAINED_LIFE_SPELL has been used and removed! [INVOCATION LIMIT REACHED]");
        } else {
            LOGGER.trace("PASS_DRAINED_LIFE_SPELL cannot be cast: {} {}", holder, ally == null ? "has no one behind!" : "has no ability to drain life!");
        }
    }
}
