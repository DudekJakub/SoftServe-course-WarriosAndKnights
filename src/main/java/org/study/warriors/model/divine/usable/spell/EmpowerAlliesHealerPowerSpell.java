package org.study.warriors.model.divine.usable.spell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.divine.DivineHealer;
import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.usable.Usable;

public class EmpowerAlliesHealerPowerSpell extends Usable implements SupportSpell {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassDrainedLifeSpell.class);

    public EmpowerAlliesHealerPowerSpell(DivineSoldier holder) {
        super(holder);
        this.invocationCounter = 0;
        this.invocationLimit = 1;
    }


    @Override
    public void apply(DivineSoldier ally) {
        var brothersInArm = holder.getBrothersInArm();

        if (isReadyToUse) {
            invocationCounter++;
            checkActiveStatus();
            var healPowerBonus = 1;
            var healEssenceBonus = 1;
            brothersInArm.forEach(warrior -> {
                        if (warrior instanceof DivineHealer healer) {
                            healer.setHealPower(healer.getHealPower() + healPowerBonus);
                            healer.setHealEssence(healer.getHealEssence() + healEssenceBonus);
                        }
                    }
            );
            LOGGER.trace("EMPOWER_ALLIES_HEALER_POWER is being casted...");
            LOGGER.trace("EMPOWER_ALLIES_HEALER_POWER casted successfully! All allies healer have received bonuses to HealPower ({}) and HealEssence ({})", healPowerBonus, healEssenceBonus);
        } else {
            holder.removeSpell(this);
            LOGGER.trace("EMPOWER_ALLIES_HEAL_CONVERTER has been already used and now removed! [INVOCATION LIMIT REACHED]");
        }
    }
}
