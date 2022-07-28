package org.javabasictraining.encapsulatedApproach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Battle.fight(knights, warriors);
        var knightsQuantity = knights.getSoldiers().size();
        var warriorsQuantity = warriors.getSoldiers().size();

        //Then
        assertTrue(knightsQuantity > 0);
        assertEquals(0, warriorsQuantity);
    }

    @Test
    @DisplayName("2. Battlefield: Warriors (attack) vs Knights (defend) -> Knights win")
    void whenWarriorArmyAttackKnightArmy_KnightArmyWin() {
        //Given
        Army warriors = new Army();
        Army knights = new Army();
        warriors.addUnits(Warrior::new, 3);
        knights.addUnits(new Warrior(50, 7), 3);

        //When
        Battle.fight(warriors, knights);
        var warriorsQuantity = warriors.getSoldiers().size();
        var knightsQuantity = knights.getSoldiers().size();

        //Then
        assertFalse(warriorsQuantity > 0);
        assertTrue(knightsQuantity > 0);
        assertEquals(1, knightsQuantity);
    }
}