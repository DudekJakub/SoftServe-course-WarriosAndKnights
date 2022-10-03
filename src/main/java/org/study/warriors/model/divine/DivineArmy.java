package org.study.warriors.model.divine;

import org.study.warriors.model.Army;
import org.study.warriors.model.divine.goddess.Goddess;
import org.study.warriors.model.interfaces.IWarrior;

import java.util.function.Supplier;

public class DivineArmy extends Army {

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
            ((DivineWarrior) divineSoldier).acceptGoddessBlessing(goddess);
            ((DivineWarrior) divineSoldier).setBrothersInArm(soldiers);
        }
        return this;
    }

    public void updateSoldiersIfItsDayTime(boolean isDayTime) {
        soldiers.forEach(soldier -> ((DivineWarrior) soldier).updateDayNightCycle(isDayTime));
    }

    public Goddess.GoddessType getDivineType() {
        return goddess.getDivineType();
    }
}
