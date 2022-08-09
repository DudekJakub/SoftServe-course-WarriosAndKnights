package org.study.warriors.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.decorator.RequestHealerDecorator;
import org.study.warriors.model.decorator.RequestWarriorDecorator;
import org.study.warriors.model.interfaces.Unit;

import static org.junit.jupiter.api.Assertions.*;

class ArmyBasicFunctionTestSuite {

    private Army army;

    @BeforeEach
    void prepareNewArmy() {
        army = new Army();
    }

    @Test
    @DisplayName("1. AddSingleUnit to Army")
    void whenArmyAddSingleUnit_ArmySizeIncreasesRespectively() {
        //Given
            //BeforeEach settings
        Warrior warrior1 = new Warrior();
        Warrior warrior2 = new Warrior();
        var armySizeBefore = army.getArmySize();

        //When
        army.addSingleUnit(warrior1);
        var armySizeAfterFirstUnitJoinedIt = army.getArmySize();
        army.addSingleUnit(warrior2);
        var armySizeAfterSecondUnitJoinedIt = army.getArmySize();

        //Then
        assertEquals(0, armySizeBefore);
        assertEquals(1, armySizeAfterFirstUnitJoinedIt);
        assertEquals(2, armySizeAfterSecondUnitJoinedIt);
    }

    @Test
    @DisplayName("2. AddUnits : via Generic Class (wildcard)")
    void whenArmyAddUnitsViaGenericCLass_ArmySizeIncreasesRespectively() {
        //When
        army.addUnits(Warrior.class, 5);
        var armySoldiersListSize = army.getArmySize();

        //Then
        assertEquals(5, armySoldiersListSize);
    }

    @Test
    @DisplayName("3. AddUnits : via Factory Pattern")
    void whenArmyAddUnitsViaFactory_ArmySizeIncreasesRespectively() {
        //When
        army.addUnits(Unit.UnitType.WARRIOR, 5);
        var armySoldiersListSize = army.getArmySize();

        //Then
        assertEquals(5, armySoldiersListSize);
    }

    @Test
    @DisplayName("4. AddUnits : via Shallow Cloning")
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
    @DisplayName("5. AddUnits : via Functional Interface (Supplier)")
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

    @Test
    @DisplayName("6. AssignSoldierPositionInChain method: after the army is ultimately formed then all allies should be bounded with each others in chain respectively")
    void whenArmyIsFormedUltimately_AlliesAreBoundedInChainRespectively() {
        //Given
            //BeforeEach setting
        var unit1 = new RequestWarriorDecorator(new Warrior());
        var unit2 = new RequestWarriorDecorator(new Defender());
        var unit3 = new RequestHealerDecorator(new Healer());

        //When
        army.addSingleUnit(unit1).addSingleUnit(unit2).addSingleUnit(unit3);
        var firstUnitInFrontSoldier = unit1.getPreviousInChain();
        var firstUnitInBehindSoldier = unit1.getNextInChain();
        var secondUnitInFrontSoldier = unit2.getPreviousInChain();
        var secondUnitInBehindSoldier = unit2.getNextInChain();
        var thirdUnitInFrontSoldier = unit3.getPreviousInChain();
        var thirdUnitInBehindSoldier = unit3.getNextInChain();

        //Then
        assertNull(firstUnitInFrontSoldier);
        assertEquals(unit2, firstUnitInBehindSoldier);
        assertEquals(unit1, secondUnitInFrontSoldier);
        assertEquals(unit3, secondUnitInBehindSoldier);
        assertEquals(unit2, thirdUnitInFrontSoldier);
        assertNull(thirdUnitInBehindSoldier);
    }
}
