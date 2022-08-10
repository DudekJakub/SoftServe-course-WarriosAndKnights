package org.study.warriors.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.service.Battle;

import static org.junit.jupiter.api.Assertions.*;
import static org.study.warriors.model.interfaces.Unit.UnitType.LANCER;

public class ArmyStraightTypeFightTestSuite {

    @Test
    @DisplayName("1. Battlefield - StraightFight: ATTACKERS vs. DEFENDERS -> ATTACKERS should lose")
    void test1() {
        //Given
        var attackers = new Army();
        var defenders = new Army();
        attackers.addUnits(Lancer.class, 5)
                .addUnits(Vampire.class, 3)
                .addUnits(Warrior.class, 4)
                .addUnits(Defender.class, 2);
        defenders.addUnits(Warrior.class, 4)
                .addUnits(Defender.class, 4)
                .addUnits(Vampire.class, 6)
                .addUnits(Lancer.class, 5);

        //When
        var battleResult = Battle.straightFight(attackers, defenders);

        //Then
        assertFalse(battleResult);
    }

    @Test
    @DisplayName("2. Battlefield - StraightFight: ATTACKERS vs. DEFENDERS -> ATTACKERS should win")
    void test2() {
        //Given
        var attackers = new Army();
        var defenders = new Army();
        attackers.addUnits(new Lancer(), 7)
                .addUnits(new Vampire(), 3)
                .addUnits(new Warrior(), 4)
                .addUnits(new Defender(), 2);

        defenders.addUnits(new Warrior(), 4)
                .addUnits(new Defender(),4)
                .addUnits(new Vampire(), 6)
                .addUnits(new Lancer(), 4);

        //When
        var battleResult = Battle.straightFight(attackers, defenders);

        //Then
        assertTrue(battleResult);
    }

    @Test
    @DisplayName("3. Battlefield - StraightFight: ATTACKERS vs. DEFENDERS -> ATTACKERS should lose")
    void test3() {
        //Given
        var attackers = new Army();
        var defenders = new Army();
        attackers.addUnits(new Lancer(), 7)
                .addUnits(new Vampire(), 3)
                .addUnits(new Healer(), 1)
                .addUnits(new Warrior(), 4)
                .addUnits(new Healer(), 1)
                .addUnits(new Defender(),2);

        defenders.addUnits(new Warrior(), 4)
                .addUnits(new Defender(),4)
                .addUnits(new Healer(), 1)
                .addUnits(new Vampire(), 6)
                .addUnits(new Lancer(), 4);

        //When
        var battleResult = Battle.straightFight(attackers, defenders);

        //Then
        assertFalse(battleResult);
    }

    @Test
    @DisplayName("4. Battlefield - StraightFight: ATTACKERS vs. DEFENDERS -> ATTACKERS should win")
    void test4() {
        //Given
        var attackers = new Army();
        var defenders = new Army();
        attackers.addUnits(new Lancer(), 4)
                .addUnits(new Warrior(), 3)
                .addUnits(new Healer(), 1)
                .addUnits(new Warrior(), 4)
                .addUnits(new Healer(), 1)
                .addUnits(new Knight(), 2);

        defenders.addUnits(new Warrior(), 4)
                .addUnits(new Defender(), 4)
                .addUnits(new Healer(), 1)
                .addUnits(new Vampire(), 2)
                .addUnits(new Lancer(), 4);

        //When
        var battleResult = Battle.straightFight(attackers, defenders);

        //Then
        assertTrue(battleResult);
    }
}
