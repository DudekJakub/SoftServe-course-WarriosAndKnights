package org.study.warriors.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.*;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.Unit;
import org.study.warriors.service.Battle;

import static org.junit.jupiter.api.Assertions.*;

class ArmyFightTestSuite {

    @Test
    @DisplayName("1. Battlefield: Knights (attack) vs Warriors (defend) -> Knights win")
    void whenKnightArmyAttackWarriorArmy_KnightArmyWin() {
        //Given
        Army knights = new Army();
        Army warriors = new Army();
        knights.addUnits(Unit.UnitType.KNIGHT, 3);
        warriors.addUnits(Unit.UnitType.WARRIOR, 3);

        //When
        var armyBattleResult = Battle.fight(knights, warriors);
        var warriorsAlive = warriors.getSoldiers().stream()
                .filter(IWarrior::isAlive)
                .count();

        //Then
        assertFalse(armyBattleResult);
        assertEquals(0, warriorsAlive);
    }

    @Test
    @DisplayName("2. Battlefield: Warriors (attack) vs Knights (defend) -> Knights win")
    void whenWarriorArmyAttackKnightArmy_KnightArmyWin() {
        //Given
        Army warriors = new Army();
        Army knights = new Army();
        warriors.addUnits(Warrior.class, 3);
        knights.addUnits(Knight.class, 3);

        //When
        var armyBattleResult = Battle.fight(warriors, knights);
        var knightsAlive = knights.getSoldiers().stream()
                                                      .filter(IWarrior::isAlive)
                                                      .count();
        var warriorsAlive = warriors.getSoldiers().stream()
                                                        .filter(IWarrior::isAlive)
                                                        .count();
        //Then
        assertTrue(armyBattleResult);
        assertEquals(1, knightsAlive);
        assertEquals(0, warriorsAlive);
    }

    @Test
    @DisplayName("3. Battlefield: Warriors (attack) vs Defenders (defend) -> Defenders win")
    void whenWarriorArmyAttackDefenderArmy_DefenderArmyWin() {
        //Given
        Army warriors = new Army();
        Army defenders = new Army();
        warriors.addUnits(Warrior::new, 3);
        defenders.addUnits(Defender::new, 3);

        //When
        var armyBattleResult = Battle.fight(warriors, defenders);
        var defendersAlive = defenders.getSoldiers().stream()
                                                          .filter(IWarrior::isAlive)
                                                          .count();
        var warriorsAlive = warriors.getSoldiers().stream()
                                                        .filter(IWarrior::isAlive)
                                                        .count();

        //Then
        assertTrue(armyBattleResult);
        assertEquals(1, defendersAlive);
        assertEquals(0, warriorsAlive);
    }
}