package org.study.warriors.model.divine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.Healer;
import org.study.warriors.model.Vampire;
import org.study.warriors.model.divine.goddess.MoonGoddess;
import org.study.warriors.model.divine.goddess.SunGoddess;
import org.study.warriors.service.Battle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivineModifiersSuiteTest {

    @Test
    @DisplayName("1. DivineModifier: when vampire is in SunArmy -> he should has decreased vampirism during DayTime by 20% + normal vampirism during NightTime" +
                    " (if SunArmy starts battle - it is DayTime | if MoonArmy starts battle - it is NightTime")
    void test1() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        moonArmy.addUnits(() -> new DivineHealer(new Healer()), 1);
        sunArmy.addUnits(() -> new DivineVampire(new Vampire()), 1);

        //When
        Battle.fight(sunArmy, moonArmy);
        var vampirismDuringDayTime = ((DivineVampire) sunArmy.unitAtPosition(0)).getVampirism();

        moonArmy.unitAtPosition(0).setHealth(10);
        Battle.fight(moonArmy, sunArmy);
        var vampirismDuringNightTime = ((DivineVampire) sunArmy.unitAtPosition(0)).getVampirism();

        //Then
        assertEquals(30, vampirismDuringDayTime);
        assertEquals(50, vampirismDuringNightTime);
    }

    @Test
    @DisplayName("2. DivineModifier: when vampire is in MoonArmy -> he should has increased vampirism during NightTime by 10%" +
                    " (if SunArmy starts battle - it is DayTime | if MoonArmy starts battle - it is NightTime")
    void test2() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        sunArmy.addUnits(() -> new DivineHealer(new Healer()), 1);
        moonArmy.addUnits(() -> new DivineVampire(new Vampire()), 1);

        //When
        Battle.fight(moonArmy, sunArmy);
        var vampirismDuringNightTime = ((DivineVampire) moonArmy.unitAtPosition(0)).getVampirism();

        //Then
        assertEquals(60, vampirismDuringNightTime);
    }
}
