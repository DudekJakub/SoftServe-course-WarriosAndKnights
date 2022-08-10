package org.study.warriors.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.service.Battle;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyStraightTypeFightTestSuite {

    @Test
    @DisplayName("1. Battlefield - StraightFight: x2 Warriors ATTACK vs. DEFEND x2 Warriors -> DEFENDERS should lose")
    void test1() {
        //Given
        var attackers = new Army();
        var defenders = new Army();
        attackers.addUnits(new Lancer(), 7)
                .addUnits(new Vampire(), 3)
                .addUnits(new Warrior(), 4)
                .addUnits(new Defender(), 2);

        defenders.addUnits(new Warrior(), 4)
                .addUnits(new Defender(),4 )
                .addUnits(new Vampire(), 6)
                .addUnits(new Lancer(), 4);

        //When
        var battleResult = Battle.straightFight(attackers, defenders);

        //Then
        assertTrue(battleResult);
    }

}
