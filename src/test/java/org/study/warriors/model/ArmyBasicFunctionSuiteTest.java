package org.study.warriors.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.interfaces.Unit;

import static org.junit.jupiter.api.Assertions.*;

class ArmyBasicFunctionSuiteTest {

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
        var unit1 = new Warrior();
        var unit2 = new Defender();
        var unit3 = new Healer();

        //When
        army.addSingleUnit(unit1).addSingleUnit(unit2).addSingleUnit(unit3);
        var firstUnitInBehindSoldier = unit1.getNextInChain();
        var secondUnitInBehindSoldier = unit2.getNextInChain();
        var thirdUnitInBehindSoldier = unit3.getNextInChain();

        //Then
        assertEquals(unit2, firstUnitInBehindSoldier);
        assertEquals(unit3, secondUnitInBehindSoldier);
        assertNull(thirdUnitInBehindSoldier);
    }

    @Test
    @DisplayName("7. Warlord in army: when Warlord is added to army then there cannot be any other Warlord more")
    void whenWarlordAddedToArmy_NoMoreWarlordCanBeAddedToArmy() {
        //Given
        var army = new Army();

        //When
        army.addUnits(Warlord::new, 2);
        army.addUnits(Warlord::new, 1);

        //Then
        assertEquals(1, army.getArmySize());
    }

    @Test
    @DisplayName("8. Army moveUnits(): when moveUnits() method is invoked then army should be rearranged respectively to the rules")
    void testMoveUnits() {
        //Given
        var army = new Army();
        army.addUnits(Warlord::new, 1)
                .addUnits(Warrior::new, 2)
                .addUnits(Healer::new, 1)
                .addUnits(Lancer::new, 2);

        //When
        army.moveUnits();
        var soldierAtFirstPosition = army.unitAtPosition(0);
        var soldierAtSecondPosition = army.unitAtPosition(1);
        var soldierAtThirdPosition = army.unitAtPosition(2);
        var soldierAtTheLastPosition = army.unitAtPosition(army.getArmySize() - 1);

        //Then
        assertEquals(Lancer.class, soldierAtFirstPosition.getClass());
        assertEquals(Healer.class, soldierAtSecondPosition.getClass());
        assertEquals(Lancer.class, soldierAtThirdPosition.getClass());
        assertEquals(Warlord.class, soldierAtTheLastPosition.getClass());
    }
}
