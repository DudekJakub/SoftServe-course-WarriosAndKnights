package org.study.warriors.model.divine.usable.spell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Lancer;
import org.study.warriors.model.Warlord;
import org.study.warriors.model.divine.DivineLancer;
import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.DivineWarlord;
import org.study.warriors.model.divine.usable.Usable;
import org.study.warriors.model.interfaces.IWarrior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurseOpponentsSpell extends Usable implements DamageSpell {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassDrainedLifeSpell.class);

    public CurseOpponentsSpell(DivineSoldier holder) {
        super(holder);
        this.invocationCounter = 0;
        this.invocationLimit = 1;
    }

    @Override
    public void apply(DivineSoldier target) {
        var opponentBrothersInArm = target.getBrothersInArm();
        var holderBrothersInArm = holder.getBrothersInArm();

        if (isReadyToUse) {
            invocationCounter++;
            checkActiveStatus();
            LOGGER.trace("CURSE_OPPONENTS_SPELL is being casted...");
            opponentBrothersInArm.forEach(warrior -> {
                if (warrior instanceof DivineLancer || warrior instanceof DivineWarlord && warrior.isAlive()) {
                    if (warrior.getAttack() > 0) {
                        warrior.setAttack(warrior.getAttack() - 1);
                        LOGGER.trace("Opponent {} have been cursed! Their attack is reduced by 1.", warrior);
                        if (warrior instanceof DivineWarlord divineWarlord) {
                            divineWarlord.setHealth(divineWarlord.getHealth() - 20);
                            LOGGER.trace("Opponent Warlord has been cursed! His health is reduced by 20.");
                        }
                    }
                }
            });
            var isAllyLancerResurrected = resurrectAllyLancer(holderBrothersInArm);
            LOGGER.trace("CURSE_OPPONENTS_SPELL casted successfully! {}", isAllyLancerResurrected ? "Ally lancer has been resurrected! Unfortunately spell caster had to sacrifice almost whole of his HP!" : "");
        }
    }

    private boolean resurrectAllyLancer(Iterable<IWarrior> brotherInArm) {
        LOGGER.trace("{} tries to sacrifice his life for ally lancer...", holder);
        var deadLancers = new ArrayList<IWarrior>();

        brotherInArm.forEach(warrior -> {
            if (!warrior.isAlive() && warrior instanceof DivineLancer) {
                deadLancers.add(warrior);
            }
        });

        deadLancers.stream().limit(1)
                .toList()
                .stream()
                .findFirst()
                .ifPresent(lancer -> {
                    lancer.setHealth(60);
                    holder.setHealth(1);
                    LOGGER.trace("ALLY LANCER RESURRECTED!");
                });

        return deadLancers.size() == 1;
    }
}

