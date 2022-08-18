package org.study.warriors.model.divine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Army;
import org.study.warriors.model.divine.goddess.Goddess;
import org.study.warriors.model.interfaces.IWarrior;

import java.util.function.Supplier;

public class DivineArmy extends Army {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivineArmy.class);

    private final Goddess goddess;

    public DivineArmy(Goddess goddess) {
        this.goddess = goddess;
        if (goddess == null) throw new IllegalStateException("DivineArmy cannot stand without its Goddess!");
    }

    @Override
    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        super.addUnits(factory, quantity);

        var soldiersWithoutDivineModifier = soldiers.stream()
                .filter(soldier -> ((DivineWarrior) soldier).getDivineModifier() == null)
                .toList();

        for (var divineSoldier : soldiersWithoutDivineModifier) {
            ((DivineWarrior) divineSoldier).setDivineModifier(goddess.getDivineModifier());
        }
        return this;
    }

    public void updateSoldiersIfItsDayTime(boolean isDayTime) {
        soldiers.forEach(soldier -> ((DivineWarrior) soldier).updateDayNightCycle(isDayTime));
    }

    public Goddess getGoddess() {
        return goddess;
    }
}
