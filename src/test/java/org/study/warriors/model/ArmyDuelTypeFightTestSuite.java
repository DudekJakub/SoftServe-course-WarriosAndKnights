package org.study.warriors.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.study.warriors.model.decorator.RequestHealerDecorator;
import org.study.warriors.model.decorator.RequestLancerDecorator;
import org.study.warriors.model.decorator.RequestWarriorDecorator;
import org.study.warriors.model.interfaces.Unit;
import org.study.warriors.service.Battle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArmyDuelTypeFightTestSuite {

    @Test
    @DisplayName("1. Battlefield (balanced size of soldiers): Knights (attack) vs Warriors (defend) -> Knights win")
    void whenArmiesAreBalancedAndKnightArmyVsWarriorArmy_KnightArmyWin() {
        //Given
        Army knights = new Army();
        Army warriors = new Army();
        knights.addUnits(Unit.UnitType.KNIGHT, 3);
        warriors.addUnits(Unit.UnitType.WARRIOR, 3);

        //When
        var armyBattleResult = Battle.fight(knights, warriors);
        var warriorsAlive = warriors.getAliveSoldiers();

        //Then
        assertTrue(armyBattleResult);
        assertEquals(0, warriorsAlive);
    }

    @Test
    @DisplayName("2. Battlefield (balanced size of soldiers): Warriors (attack) vs Knights (defend) -> Knights win")
    void whenArmiesAreBalancedAndWarriorArmyVsKnightArmy_KnightArmyWin() {
        //Given
        Army warriors = new Army();
        Army knights = new Army();
        warriors.addUnits(Warrior.class, 3);
        knights.addUnits(Knight.class, 3);

        //When
        var armyBattleResult = Battle.fight(warriors, knights);
        var knightsAlive = knights.getAliveSoldiers();
        var warriorsAlive = warriors.getAliveSoldiers();
        //Then
        assertFalse(armyBattleResult);
        assertEquals(1, knightsAlive);
        assertEquals(0, warriorsAlive);
    }

    @Test
    @DisplayName("3. Battlefield (balanced size of soldiers): Warriors (attack) vs Defenders (defend) -> Defenders win")
    void whenArmiesAreBalancedAndWarriorArmyVsDefenderArmy_DefenderArmyWin() {
        //Given
        Army warriors = new Army();
        Army defenders = new Army();
        warriors.addUnits(Warrior::new, 3);
        defenders.addUnits(Defender::new, 3);

        //When
        var armyBattleResult = Battle.fight(warriors, defenders);
        var defendersAlive = defenders.getAliveSoldiers();
        var warriorsAlive = warriors.getAliveSoldiers();

        //Then
        assertFalse(armyBattleResult);
        assertEquals(1, defendersAlive);
        assertEquals(0, warriorsAlive);
    }

    @Test
    @DisplayName("4. Battlefield (balanced size of soldiers): Vampires (attack) vs Warriors (defend) -> Vampires win")
    void whenArmiesAreBalancedAndVampireArmyVsWarriorArmy_VampireArmyWin() {
        //Given
        Army vampires = new Army();
        Army warriors = new Army();
        vampires.addUnits(Unit.UnitType.VAMPIRE, 3);
        warriors.addUnits(Unit.UnitType.WARRIOR, 3);

        //When
        var armyBattleResult = Battle.fight(vampires, warriors);
        var vampiresAlive = vampires.getAliveSoldiers();
        var warriorsAlive = warriors.getAliveSoldiers();

        //Then
        assertTrue(armyBattleResult);
        assertEquals(1, vampiresAlive);
        assertEquals(0, warriorsAlive);
    }

    @Test
    @DisplayName("5. Battlefield (balanced size of soldiers): Vampires (attack) vs Defenders (defend) -> Vampires lose")
    void whenArmiesAreBalancedAndVampireArmyVsDefenderArmy_VampireArmyLose() {
        //Given
        Army vampires = new Army();
        Army defenders = new Army();
        vampires.addUnits(Unit.UnitType.VAMPIRE, 3);
        defenders.addUnits(Unit.UnitType.DEFENDER, 3);

        //When
        var armyBattleResult = Battle.fight(vampires, defenders);
        var vampiresAlive = vampires.getAliveSoldiers();
        var defendersAlive = defenders.getAliveSoldiers();

        //Then
        assertFalse(armyBattleResult);
        assertEquals(0, vampiresAlive);
        assertEquals(1, defendersAlive);
    }

    @Test
    @DisplayName("6. Battlefield (balanced size of soldiers): Warriors (attack) vs Vampires (defend) -> Warriors win")
    void whenArmiesAreBalancedAndWarriorArmyVsVampireArmy_WarriorArmyWins() {
        //Given
        Army warriors = new Army();
        Army vampires = new Army();
        warriors.addUnits(Unit.UnitType.WARRIOR, 3);
        vampires.addUnits(Unit.UnitType.VAMPIRE, 3);

        //When
        var armyBattleResult = Battle.fight(warriors, vampires);
        var vampiresAlive = vampires.getAliveSoldiers();
        var warriorsAlive = warriors.getAliveSoldiers();

        //Then
        assertTrue(armyBattleResult);
        assertEquals(1, warriorsAlive);
        assertEquals(0, vampiresAlive);
    }

    @Test
    @DisplayName("7. Battlefield (balanced size of soldiers): Defenders (attack) vs Vampires (defend) -> Defenders win")
    void whenArmiesAreBalancedAndDefendersArmyVsVampireArmy_DefendersArmyWins() {
        //Given
        Army defenders = new Army();
        Army vampires = new Army();
        defenders.addUnits(Unit.UnitType.DEFENDER, 3);
        vampires.addUnits(Unit.UnitType.VAMPIRE, 3);

        //When
        var armyBattleResult = Battle.fight(defenders, vampires);
        var vampiresAlive = vampires.getAliveSoldiers();
        var defendersAlive = defenders.getAliveSoldiers();

        //Then
        assertTrue(armyBattleResult);
        assertEquals(2, defendersAlive);
        assertEquals(0, vampiresAlive);
    }

    @Test
    @DisplayName("8. Battlefield (unbalanced size of soldiers): x1 WarriorArmy vs x2 WarriorArmy -> First one loses")
    void whenArmiesAreNotBalancedAndX1WarriorArmyVsX2WarriorArmy_FirstArmyLoses() {
        //Given
        Army warriorsArmy1 = new Army();
        Army warriorsArmy2 = new Army();
        warriorsArmy1.addUnits(Warrior.class, 1);
        warriorsArmy2.addUnits(Warrior.class, 2);

        //When
        var armyBattleResult = Battle.fight(warriorsArmy1, warriorsArmy2);

        //Then
        assertFalse(armyBattleResult);
    }

    @Test
    @DisplayName("9. Battlefield (unbalanced size of soldiers): x2 WarriorArmy vs x3 WarriorArmy -> First one loses")
    void whenArmiesAreNotBalancedAndX2WarriorArmyVsX3WarriorArmy_FirstArmyLoses() {
        //Given
        Army warriorsArmy1 = new Army();
        Army warriorsArmy2 = new Army();
        warriorsArmy1.addUnits(Warrior.class, 2);
        warriorsArmy2.addUnits(Warrior.class, 3);

        //When
        var armyBattleResult = Battle.fight(warriorsArmy1, warriorsArmy2);

        //Then
        assertFalse(armyBattleResult);
    }

    @Test
    @DisplayName("10. Battlefield (unbalanced size of soldiers): x5 WarriorArmy vs x7 WarriorArmy -> First one loses")
    void whenArmiesAreNotBalancedAndX5WarriorArmyVsX7WarriorArmy_FirstArmyLoses() {
        //Given
        Army warriorsArmy1 = new Army();
        Army warriorsArmy2 = new Army();
        warriorsArmy1.addUnits(Warrior.class, 5);
        warriorsArmy2.addUnits(Warrior.class, 7);

        //When
        var armyBattleResult = Battle.fight(warriorsArmy1, warriorsArmy2);

        //Then
        assertFalse(armyBattleResult);
    }

    @Test
    @DisplayName("11. Battlefield: x1 LancerArmy vs x2 WarriorArmy -> Lancer hits both warriors")
    void whenSingleLancerFightVsTwoSoldiersInLine_BothSoldiersShouldBeHitRespectivelyAndLancerArmyLoses() {
        //Given
        var singleLancerArmy = new Army();
        var warriorArmy = new Army();
        var warrior1 = new RequestWarriorDecorator(new Warrior());
        var warrior2 = new RequestWarriorDecorator(new Warrior());
        singleLancerArmy.addUnits(new RequestLancerDecorator(new Lancer()), 1);
        warriorArmy.addSingleUnit(warrior1);
        warriorArmy.addSingleUnit(warrior2);

        //When
        var battleResult = Battle.fight(singleLancerArmy, warriorArmy);
        var firstWarriorHealthAfterFight = warrior1.getHealth();
        var secondWarriorHealthAfterFight = warrior2.getHealth();

        //Then
        assertFalse(battleResult);
        assertEquals(-4, firstWarriorHealthAfterFight);
        assertEquals(11, secondWarriorHealthAfterFight);
    }

    @Test
    @DisplayName("12. Battlefield: x1 LancerArmy vs x2 DefenderArmy -> LancerArmy hits both defender where both should reduced damage taken respectively")
    void whenSingleLancerFightVsTwoDefendersInLine_BothShouldReduceDamageTakenRespectivelyAndLancerArmyLoses() {
        //Given
        var singleLancerArmy = new Army();
        var defenderArmy = new Army();
        singleLancerArmy.addUnits(Lancer.class, 1);
        Defender defender1 = new Defender();
        Defender defender2 = new Defender();
        defenderArmy.addSingleUnit(defender1);
        defenderArmy.addSingleUnit(defender2);

        //When
        var battleResult = Battle.fight(singleLancerArmy, defenderArmy);

        //Then
        assertFalse(battleResult);
    }

    @Test
    @DisplayName("13. Battlefield: x1 WarriorArmy vs (x1 Warrior & x1 Healer)Army ->  SingleWarriorArmy loses & " +
                                   "Warrior from sec. army should remains 20 HP & Healer from sec. army shouldn't lose any HP")
    void whenSingleWarriorArmyFightVsOneWarriorAndOneHealerArmy_HealerShouldHealsItsAllyAndWonAndDoesntLoseHp() {
        //Given
        var singleWarriorArmy = new Army();
        var warriorAndHealerArmy = new Army();
        var warriorFromSecondArmy = new RequestWarriorDecorator(new Warrior());
        var healerFromSecondArmy = new RequestHealerDecorator(new Healer());
        var healerFromSecondArmyInitialHealth = healerFromSecondArmy.getHealth();

        warriorAndHealerArmy.addSingleUnit(warriorFromSecondArmy).addSingleUnit(healerFromSecondArmy);
        singleWarriorArmy.addUnits(new RequestWarriorDecorator(new Warrior()), 1);

        //When
        var battleResult = Battle.fight(singleWarriorArmy, warriorAndHealerArmy);
        var warriorFromSecondArmyHealthPointsAfterBattle = warriorFromSecondArmy.getHealth();
        var healerFromSecondArmyHealthPointsAfterBattle = healerFromSecondArmy.getHealth();

        //Then
        assertFalse(battleResult);
        assertEquals(20, warriorFromSecondArmyHealthPointsAfterBattle);
        assertEquals(healerFromSecondArmyInitialHealth, healerFromSecondArmyHealthPointsAfterBattle);
    }

    @Test
    @DisplayName("14. Battlefield: x1 LancerArmy vs (x1 Warrior & x1 Healer)Army -> SingleLancerArmy loses & " +
                                   "Warrior from sec. army should remains 10 HP & Healer from sec. army should remains 30 HP")
    void whenSingleLancerArmyFightVsOneWarriorAndOneHealerArmy_HealerShouldHealsItsAllyAndWonAndLoseSomeHp() {
        //Given
        var singleLancerArmy = new Army();
        var warriorAndHealerArmy = new Army();
        var warriorFromSecondArmy = new RequestWarriorDecorator(new Warrior());
        var healerFromSecondArmy = new RequestHealerDecorator(new Healer());
        warriorAndHealerArmy.addSingleUnit(warriorFromSecondArmy).addSingleUnit(healerFromSecondArmy);
        singleLancerArmy.addUnits(new RequestLancerDecorator(new Lancer()), 1);

        //When
        var battleResult = Battle.fight(singleLancerArmy, warriorAndHealerArmy);
        var warriorFromSecondArmyHealthPointsAfterBattle = warriorFromSecondArmy.getHealth();
        var healerFromSecondArmyHealthPointsAfterBattle = healerFromSecondArmy.getHealth();

        //Then
        assertFalse(battleResult);
        assertEquals(10, warriorFromSecondArmyHealthPointsAfterBattle);
        assertEquals(30, healerFromSecondArmyHealthPointsAfterBattle);
    }

    @Test
    @DisplayName("15. Battlefield: x1 LancerArmy vs (x1 Warrior & x2 Healer)Army -> SingleLancerArmy loses & " +
                 "Warrior from sec. army should remains 10 HP & first Healer from sec. army should remains 41 HP")
    void whenSingleLancerArmyFightVsOneWarriorAndTwoHealerArmy_HealerShouldHealsItsAllyAndWonAndFirstHealerShouldLoseSomeHp() {
        //Given
        var singleLancerArmy = new Army();
        var warriorAndHealerArmy = new Army();
        var warriorFromSecondArmy = new RequestWarriorDecorator(new Warrior());
        var firstHealerFromSecondArmy = new RequestHealerDecorator(new Healer());
        var secondHealerFromSecondArmy = new RequestHealerDecorator(new Healer());

        warriorAndHealerArmy.addUnits(warriorFromSecondArmy, 1).addUnits(firstHealerFromSecondArmy, 1)
                                                                               .addUnits(secondHealerFromSecondArmy, 1);
        singleLancerArmy.addUnits(new RequestLancerDecorator(new Lancer()), 1);

        //When
        var battleResult = Battle.fight(singleLancerArmy, warriorAndHealerArmy);
        var warriorFromSecondArmyHealthPointsAfterBattle = warriorAndHealerArmy.getSoldierFromGivenPosition(0).getHealth();
        var firstHealerFromSecondArmyHealthPointsAfterBattle = warriorAndHealerArmy.getSoldierFromGivenPosition(1).getHealth();
        var secondHealerFromSecondArmyHealthPointsAfterBattle = warriorAndHealerArmy.getSoldierFromGivenPosition(2).getHealth();

        //Then
        assertFalse(battleResult);
        assertEquals(10, warriorFromSecondArmyHealthPointsAfterBattle);
        assertEquals(30, firstHealerFromSecondArmyHealthPointsAfterBattle);
        assertEquals(60, secondHealerFromSecondArmyHealthPointsAfterBattle);
    }

    @Test
    @DisplayName("16. Battlefield: x1 LancerArmy vs (x2 Defender & x2 Healer)Army -> SingleLancerArmy loses & " +
            "Second Defender should be healed respectively & First Healer shouldn't be healed at all")
    void whenSingleLancerArmyFightVsTwoDefenderAndTwoHealerArmy_SecondDefenderShouldBeHealedRespectivelyAndFirstHealerShouldNotReceiveAnyHeal() {
        //Given
        var singleLancerArmy = new Army();
        var defenderAndHealerArmy = new Army();
        var defender1FromSecondArmy = new RequestWarriorDecorator(new Defender());
        var defender2FromSecondArmy = new RequestWarriorDecorator(new Defender());
        var healer1FromSecondArmy = new RequestHealerDecorator(new Healer());
        var healer2FromSecondArmy = new RequestHealerDecorator(new Healer());

        defenderAndHealerArmy.addSingleUnit(defender1FromSecondArmy).addSingleUnit(defender2FromSecondArmy)
                             .addSingleUnit(healer1FromSecondArmy).addSingleUnit(healer2FromSecondArmy);

        singleLancerArmy.addUnits(new RequestLancerDecorator(new Lancer()), 1);

        //When
        var battleResult = Battle.fight(singleLancerArmy, defenderAndHealerArmy);
        var firstDefenderFromSecondArmyHealthPointsAfterBattle = defender1FromSecondArmy;
        var secondDefenderFromSecondArmyHealthPointsAfterBattle = defender2FromSecondArmy;
        var firstHealerFromSecondArmyHealthPointsAfterBattle = healer1FromSecondArmy;

        //Then
        assertFalse(battleResult);
        assertEquals(0 ,0 );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("17. Battlefield (parametrizedTest): Battles of Armies check if result of fights match with real outputs")
    void provideSoldiersForArmies(Army army1, Army army2, boolean expectedResult) {
        Assertions.assertSame(expectedResult, Battle.fight(army1, army2));
    }

    private static Stream<Arguments> provideSoldiersForArmies() {
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Warrior.class, 5)
                                .addUnits(Defender.class, 4)
                                .addUnits(Defender.class, 5),
                        new Army()
                                .addUnits(Warrior.class, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender.class, 5)
                                .addUnits(Warrior.class, 20)
                                .addUnits(Defender.class, 4),
                        new Army()
                                .addUnits(Warrior.class, 21),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior.class, 10)
                                .addUnits(Defender.class, 5)
                                .addUnits(Defender.class, 10),
                        new Army()
                                .addUnits(Warrior.class, 5),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender.class, 2)
                                .addUnits(Warrior.class, 1)
                                .addUnits(Defender.class, 1),
                        new Army()
                                .addUnits(Warrior.class, 5),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Defender.class, 5)
                                .addUnits(Vampire.class, 6)
                                .addUnits(Warrior.class, 7),
                        new Army()
                                .addUnits(Warrior.class, 6)
                                .addUnits(Defender.class, 6)
                                .addUnits(Vampire.class, 6),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Defender.class, 2)
                                .addUnits(Vampire.class, 3)
                                .addUnits(Warrior.class, 4),
                        new Army()
                                .addUnits(Warrior.class, 4)
                                .addUnits(Defender.class, 4)
                                .addUnits(Vampire.class, 3),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Defender.class, 11)
                                .addUnits(Vampire.class, 3)
                                .addUnits(Warrior.class, 4),
                        new Army()
                                .addUnits(Warrior.class, 4)
                                .addUnits(Defender.class, 4)
                                .addUnits(Vampire.class, 13),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender.class, 9)
                                .addUnits(Vampire.class, 3)
                                .addUnits(Warrior.class, 8),
                        new Army()
                                .addUnits(Warrior.class, 4)
                                .addUnits(Defender.class, 4)
                                .addUnits(Vampire.class, 13),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(new RequestLancerDecorator(new Lancer()), 5)
                                .addUnits(new RequestWarriorDecorator(new Vampire()), 3)
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 4)
                                .addUnits(new RequestWarriorDecorator(new Defender()), 2),
                        new Army()
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 4)
                                .addUnits(new RequestWarriorDecorator(new Defender()), 4)
                                .addUnits(new RequestWarriorDecorator(new Vampire()), 6)
                                .addUnits(new RequestWarriorDecorator(new Lancer()), 5),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(new RequestLancerDecorator(new Lancer()), 7)
                                .addUnits(new RequestWarriorDecorator(new Vampire()), 3)
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 4)
                                .addUnits(new RequestWarriorDecorator(new Defender()), 2),
                        new Army()
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 4)
                                .addUnits(new RequestWarriorDecorator(new Defender()), 4)
                                .addUnits(new RequestWarriorDecorator(new Vampire()), 6)
                                .addUnits(new RequestLancerDecorator(new Lancer()), 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 2),
                        new Army()
                                .addUnits(new RequestLancerDecorator(new Lancer()), 1)
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 1),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(new RequestLancerDecorator(new Lancer()), 7)
                                .addUnits(new RequestWarriorDecorator(new Vampire()), 3)
                                .addUnits(new RequestHealerDecorator(new Healer()), 1)
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 4)
                                .addUnits(new RequestHealerDecorator(new Healer()), 1)
                                .addUnits(new RequestWarriorDecorator(new Defender()), 2),
                        new Army()
                                .addUnits(new RequestWarriorDecorator(new Warrior()), 4)
                                .addUnits(new RequestWarriorDecorator(new Defender()), 4)
                                .addUnits(new RequestHealerDecorator(new Healer()), 1)
                                .addUnits(new RequestWarriorDecorator(new Vampire()), 6)
                                .addUnits(new RequestLancerDecorator(new Lancer()), 4),
                        true)
        );
    }
}