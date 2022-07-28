package org.study.warriors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.Army;
import org.study.warriors.model.Unit;
import org.study.warriors.model.Warrior;

import static org.junit.jupiter.api.Assertions.*;

class ArmyBasicFunctionTestSuite {

    private final Army army = new Army();

    @BeforeEach
    void clearArmySize() {
        army.getSoldiers().clear();
    }

    @Test
    @DisplayName("1. AddsUnits : via Factory Pattern")
    void whenArmyAddsUnitsViaFactory_ArmySizeIncreasesRespectively() {
        //When
        army.addUnits(Unit.UnitType.WARRIOR, 5);
        var armySoldiersListSize = army.getSoldiers().size();

        //Then
        assertEquals(5, armySoldiersListSize);
    }

    @Test
    @DisplayName("2. AddsUnits : via Shallow Cloning")
    void whenArmyAddsUnitsViaShallowCloning_ArmySizeIncreasesRespectively() {
        //Given
        var warrior = new Warrior();

        //When
        army.addUnits(warrior, 5);
        var armySoldiersListSize = army.getSoldiers().size();

        //Then
        assertEquals(5, armySoldiersListSize);
    }

    @Test
    @DisplayName("3. AddsUnits : via Functional Interface (Supplier)")
    void whenArmyAddsUnitsViaSupplier_ArmySizeIncreasesRespectively() {
        //When
        army.addUnits(Warrior::new, 5);
        var armySoldiersListSize = army.getSoldiers().size();

        //Then
        assertEquals(5, armySoldiersListSize);
    }
}
