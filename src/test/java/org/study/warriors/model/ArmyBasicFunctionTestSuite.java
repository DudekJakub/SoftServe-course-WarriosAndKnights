package org.study.warriors.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.Army;
import org.study.warriors.model.interfaces.Unit;
import org.study.warriors.model.Warrior;

import static org.junit.jupiter.api.Assertions.*;

class ArmyBasicFunctionTestSuite {

    private Army army;

    @BeforeEach
    void prepareNewArmy() {
        army = new Army();
    }

    @Test
    @DisplayName("1. AddUnits : via Generic Class (wildcards)")
    void whenArmyAddUnitsViaGenericCLass_ArmySizeIncreasesRespectively() {
        //When
        army.addUnits(Warrior.class, 5);
        var armySoldiersListSize = army.getArmySize();

        //Then
        assertEquals(5, armySoldiersListSize);
    }

    @Test
    @DisplayName("2. AddUnits : via Factory Pattern")
    void whenArmyAddUnitsViaFactory_ArmySizeIncreasesRespectively() {
        //When
        army.addUnits(Unit.UnitType.WARRIOR, 5);
        var armySoldiersListSize = army.getArmySize();

        //Then
        assertEquals(5, armySoldiersListSize);
    }

    @Test
    @DisplayName("3. AddUnits : via Shallow Cloning")
    void whenArmyAddUnitsViaShallowCloning_ArmySizeIncreasesRespectively() {
        //Given
        var warrior = new Warrior();

        //When
        army.addUnits(warrior, 5);
        var armySoldiersListSize = army.getArmySize();

        //Then
        assertEquals(5, armySoldiersListSize);
    }

    @Test
    @DisplayName("4. AddUnits : via Functional Interface (Supplier)")
    void whenArmyAddUnitsViaSupplier_ArmySizeIncreasesRespectively() {
        //When
        army.addUnits(Warrior::new, 5);
        var armySoldiersListSize = army.getArmySize();

        army
           .addUnits(Warrior::new, 5)
           .addUnits(Warrior::new, 5);
        var extendedArmySoldiersListSize = army.getArmySize();

        //Then
        assertEquals(5, armySoldiersListSize);
        assertEquals(15, extendedArmySoldiersListSize);
    }
}
