package org.study.warriors.model.divine.usable.buff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.damage.PierceDamage;
import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.usable.Usable;

public class PierceResistanceBuff extends Usable implements ResistanceBuff {

    private static final Logger LOGGER = LoggerFactory.getLogger(PierceResistanceBuff.class);

    public PierceResistanceBuff(DivineSoldier holder) {
        super(holder);
        this.invocationCounter = 0;
        this.invocationLimit = 2;
    }

    @Override
    public void apply(IDamage damageType) {
        var isDamagePierceType = damageType instanceof PierceDamage;
        if (isDamagePierceType && isReadyToUse) {
            invocationCounter++;
            checkActiveStatus();
            holder.setResistanceActive(true);
            LOGGER.trace("{} is resisted to {}!", holder, damageType);
        } else if (!isReadyToUse) {
            holder.removeBuff(this);
            LOGGER.trace("PIERCE_RESISTANCE_BUFF expired for {} and has been removed!", holder);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
