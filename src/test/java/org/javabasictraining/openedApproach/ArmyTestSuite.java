package org.javabasictraining.openedApproach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTestSuite {

    @Test
    void whenUnitJoinsArmy_ArmyShouldBeIncreasedByOne() {
        //Given
        var warrior = new Warrior();
        var army = new Army();

        //When
        warrior.joinArmy(army);

        //Then
        assertEquals(1, army.getSoldiers().size());
    }

    @Test
    void whenArmyAddsUnits_ArmyShouldBeIncreasedByGivenQuantity() {
        //Given
        var army = new Army();
        var knight = new Knight();

        //When
        var soldiers = army.addUnits(knight, 2);

        //Then
        assertEquals(2, soldiers.size());
    }
}