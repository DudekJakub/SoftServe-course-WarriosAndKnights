package org.study.warriors.model.divine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.*;
import org.study.warriors.model.divine.goddess.MoonGoddess;
import org.study.warriors.model.divine.goddess.SunGoddess;
import org.study.warriors.model.divine.modifier.MoonModifier;
import org.study.warriors.model.divine.modifier.SunModifier;
import org.study.warriors.model.divine.usable.spell.CurseOpponentsSpell;
import org.study.warriors.model.divine.usable.spell.EmpowerAlliesHealerPowerSpell;
import org.study.warriors.model.divine.usable.spell.PassDrainedLifeSpell;
import org.study.warriors.model.divine.usable.spell.PierceResistSpell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DivineArmySuiteTest {

    @Test
    @DisplayName("1. Join army: when divine soldiers join armies -> they should be bounded with divine modifiers respectively")
    void test1() {
        //Given
        var sunArmy = new DivineArmy(SunGoddess.getInstance());
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());

        //When
        sunArmy.addUnits(() -> new DivineWarrior(new Warrior()), 1);
        moonArmy.addUnits(() -> new DivineWarrior(new Warrior()), 1);

        //Then
        var firstUnitFromSunArmyModifier = ((DivineWarrior) sunArmy.unitAtPosition(0)).getDivineModifier();
        var firstUnitFromMoonArmyModifier = ((DivineWarrior) moonArmy.unitAtPosition(0)).getDivineModifier();

        assertEquals(SunModifier.class, firstUnitFromSunArmyModifier.getClass());
        assertEquals(MoonModifier.class, firstUnitFromMoonArmyModifier.getClass());
    }

    @Test
    @DisplayName("2. Join army: when lancer join SunArmy -> he should receive PierceResistSpell")
    void test2() {
        //Given
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        //When
        sunArmy.addUnits(() -> new DivineLancer(new Lancer()), 1);

        //Then
        var lancerUnitFromSunArmyModifier = ((DivineWarrior) sunArmy.unitAtPosition(0)).getSpells().stream().findFirst();

        assertTrue(lancerUnitFromSunArmyModifier.get() instanceof PierceResistSpell);
    }

    @Test
    @DisplayName("3. Join army: when healer join SunArmy -> he should receive EmpowerAlliesHealerPowerSpell")
    void test3() {
        //Given
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        //When
        sunArmy.addUnits(() -> new DivineHealer(new Healer()), 1);

        //Then
        var healerUnitFromSunArmyModifier = ((DivineWarrior) sunArmy.unitAtPosition(0)).getSpells().stream().findFirst();

        assertTrue(healerUnitFromSunArmyModifier.get() instanceof EmpowerAlliesHealerPowerSpell);
    }

    @Test
    @DisplayName("4. Join army: when vampire join SunArmy -> he should receive PassDrainedLifeSpell")
    void test4() {
        //Given
        var sunArmy = new DivineArmy(SunGoddess.getInstance());

        //When
        sunArmy.addUnits(() -> new DivineVampire(new Vampire()), 1);

        //Then
        var vampireUnitFromSunArmyModifier = ((DivineWarrior) sunArmy.unitAtPosition(0)).getSpells().stream().findFirst();

        assertTrue(vampireUnitFromSunArmyModifier.get() instanceof PassDrainedLifeSpell);
    }

    @Test
    @DisplayName("5. Join army: when healer join MoonArmy -> he should receive CurseOpponentsSpell")
    void test5() {
        //Given
        var moonArmy = new DivineArmy(MoonGoddess.getInstance());

        //When
        moonArmy.addUnits(() -> new DivineHealer(new Healer()), 1);

        //Then
        var healerUnitFromSunArmyModifier = ((DivineWarrior) moonArmy.unitAtPosition(0)).getSpells().stream().findFirst();

        assertTrue(healerUnitFromSunArmyModifier.get() instanceof CurseOpponentsSpell);
    }
}
