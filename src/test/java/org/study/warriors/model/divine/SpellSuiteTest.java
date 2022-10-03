package org.study.warriors.model.divine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.*;
import org.study.warriors.model.divine.goddess.MoonGoddess;
import org.study.warriors.model.divine.goddess.SunGoddess;
import org.study.warriors.service.Battle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpellSuiteTest {

    @Test
    @DisplayName("1. Cast spell: when healer hit and cast Curse spell -> dead lancer (if present) should be resurrected and healer should has 1 HP. " +
            "Also opponent Lancer & Warlord should be cursed respectively")
    void test1() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        moonArmy.addUnits(() -> new DivineHealer(new Healer()), 1).addUnits(() -> new DivineLancer(new Lancer()), 1);
        sunArmy.addUnits(() -> new DivineLancer(new Lancer()), 1).addUnits(() -> new DivineWarlord(new Warlord()), 1);
        moonArmy.unitAtPosition(1).setHealth(0);

        //When
        ((DivineWarrior) moonArmy.unitAtPosition(0)).castDamageSpell((DivineSoldier) sunArmy.unitAtPosition(0));

        //Then
        var deadLancerFromMoonArmy = moonArmy.unitAtPosition(1).getHealth();
        var lancerAttackFromSunArmy = sunArmy.unitAtPosition(0).getAttack();
        var warlordHealthFromSunArmy = sunArmy.unitAtPosition(1).getHealth();

        assertTrue(deadLancerFromMoonArmy > 0);
        assertEquals(5, lancerAttackFromSunArmy);
        assertEquals(80, warlordHealthFromSunArmy);
    }

    @Test
    @DisplayName("2. Cast spell: when lancer hit and cast PierceResistSpell -> healer behind should receive resist for Pierce Damage for 2 hits" +
                    "+ after 2 hits healer should remove his PierceResistBuff")
    void test2() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        moonArmy.addUnits(() -> new DivineLancer(new Lancer()), 1).addUnits(() -> new DivineHealer(new Healer()), 1);
        sunArmy.addUnits(() -> new DivineLancer(new Lancer()), 1);
        (sunArmy.unitAtPosition(0)).setHealth(18);

        //When
        ((DivineWarrior) moonArmy.unitAtPosition(0)).castSupportSpell((DivineSoldier) moonArmy.unitAtPosition(1));

        Battle.fight(sunArmy, moonArmy);

        var healerHealthAfterThirdPierceDamage = moonArmy.unitAtPosition(1).getHealth();
        var healerBuffsShouldBeEmpty = ((DivineWarrior) moonArmy.unitAtPosition(1)).getBuffs().size() == 0;

        //Then
        assertEquals(57, healerHealthAfterThirdPierceDamage);
        assertTrue(healerBuffsShouldBeEmpty);
    }

    @Test
    @DisplayName("3. Cast spell: when lancer hit and cast PierceResistSpell -> healer behind should receive resist for Pierce Damage for 2 rounds")
    void test3() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        moonArmy.addUnits(() -> new DivineLancer(new Lancer()), 1).addUnits(() -> new DivineHealer(new Healer()), 1);
        sunArmy.addUnits(() -> new DivineLancer(new Lancer()), 1);
        (sunArmy.unitAtPosition(0)).setHealth(18);

        //When
        ((DivineWarrior) moonArmy.unitAtPosition(0)).castSupportSpell((DivineSoldier) moonArmy.unitAtPosition(1));

        Battle.fight(sunArmy, moonArmy);

        var healerHealthAfterThirdPierceDamage = moonArmy.unitAtPosition(1).getHealth();

        //Then
        assertEquals(57, healerHealthAfterThirdPierceDamage);
    }

    @Test
    @DisplayName("4. Cast spell: when vampire hit and cast PassDrainedLifeSpell -> vampire should pass drained life to the warrior behind based on dealt damage")
    void test4() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        moonArmy.addUnits(() -> new DivineWarrior(new Warrior()), 1);
        moonArmy.unitAtPosition(0).setHealth(4);

        sunArmy.addUnits(() -> new DivineVampire(new Vampire()), 1).addUnits(() -> new DivineHealer(new Healer()), 1);
        sunArmy.unitAtPosition(1).setHealth(10);

        var allyBehindVampireHealthBeforePassDrainLifeSpell = sunArmy.unitAtPosition(1).getHealth();

        //When
        Battle.fight(moonArmy, sunArmy);

        var vampireLastDealtDamage = moonArmy.unitAtPosition(0).getLastReceivedDamage();
        var healthPointValuePassedToAllyBehind = vampireLastDealtDamage / 3;

        var allyBehindVampireHealthAfterPassDrainLifeSpell = sunArmy.unitAtPosition(1).getHealth();

        //Then
        assertEquals(allyBehindVampireHealthAfterPassDrainLifeSpell, allyBehindVampireHealthBeforePassDrainLifeSpell + healthPointValuePassedToAllyBehind);
    }

    @Test
    @DisplayName("5. Cast spell: when healer is in SunArmy and cast EmpowerAlliesHealerPowerSpell -> all allies healers should has HealPower & HealEssence increased by 1")
    void test5() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        moonArmy.addUnits(() -> new DivineWarrior(new Warrior()), 1);
        moonArmy.unitAtPosition(0).setHealth(5);

        sunArmy.addUnits(() -> new DivineWarrior(new Warrior()), 1)
                .addUnits(() -> new DivineHealer(new Healer()), 3);

        //When
        Battle.fight(sunArmy, moonArmy);

        ((DivineWarrior) sunArmy.unitAtPosition(2)).castSupportSpell(null);

        //Then
        var firstHealerHealPower = ((DivineHealer) sunArmy.unitAtPosition(1)).getHealPower();
        var secondHealerHealPower = ((DivineHealer) sunArmy.unitAtPosition(2)).getHealPower();
        var thirdHealerHealPower = ((DivineHealer) sunArmy.unitAtPosition(3)).getHealPower();
        
        var secondHealerHealEssence = ((DivineHealer) sunArmy.unitAtPosition(2)).getHealEssence();
        var thirdHealerHealEssence = ((DivineHealer) sunArmy.unitAtPosition(3)).getHealEssence();

        assertEquals(3, firstHealerHealPower);
        assertEquals(3, secondHealerHealPower);
        assertEquals(3, thirdHealerHealPower);

        assertEquals(11, secondHealerHealEssence);
        assertEquals(11, thirdHealerHealEssence);
    }
}
