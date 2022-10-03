package org.study.warriors.model.divine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.Healer;
import org.study.warriors.model.Lancer;
import org.study.warriors.model.Vampire;
import org.study.warriors.model.Warlord;
import org.study.warriors.model.divine.goddess.MoonGoddess;
import org.study.warriors.model.divine.goddess.SunGoddess;
import org.study.warriors.service.Battle;

public class DivineBattleSuiteTest {

    @Test
    @DisplayName("")
    void test1() {
        //Given
        var sunArmy = new DivineArmy(SunGoddess.getInstance());
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());

        sunArmy.addUnits(() -> new DivineLancer(new Lancer()), 1)
                .addUnits(() -> new DivineHealer(new Healer()), 1)
                .addUnits(() -> new DivineWarlord(new Warlord()), 1);

        moonArmy.addUnits(() -> new DivineLancer(new Lancer()), 1)
                .addUnits(() -> new DivineVampire(new Vampire()), 1)
                .addUnits(() -> new DivineWarlord(new Warlord()), 1);

        //When
        var battleResult = Battle.fight(moonArmy, sunArmy);

        //Then
        System.out.println(battleResult);
    }
}
