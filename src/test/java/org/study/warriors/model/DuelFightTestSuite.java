package org.study.warriors.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.*;
import org.study.warriors.service.Battle;

import static org.junit.jupiter.api.Assertions.*;

class DuelFightTestSuite {

    @Test
    void smokeTest() {
        //Given
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

        //When & Then
        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    @DisplayName("1. Fight: Warrior defends himself against knight and should fail & die")
    void whenWarriorDefendsHimselfVsKnight_WarriorShouldFailAndDie() {
        //Given
        var warrior = new Warrior();
        var knight = new Knight();

        //When
        var battleResult = Battle.fight(knight, warrior);
        var isWarriorAlive = warrior.isAlive();

        //Then
        assertTrue(battleResult);
        assertFalse(isWarriorAlive);
    }

    @Test
    @DisplayName("2. Fight: Knight defends himself against warrior and should win & stay alive")
    void whenKnightDefendsHimselfVsWarrior_KnightShouldWinAndStayAlive() {
        //Given
        var knight = new Knight();
        var warrior = new Warrior();

        //When
        var battleResult = Battle.fight(warrior, knight);
        var isKnightAlive = knight.isAlive();

        //Then
        assertFalse(battleResult);
        assertTrue(isKnightAlive);
    }

    @Test
    @DisplayName("3. Fight: Warrior attacks knight and should fail & die")
    void whenWarriorAttacksKnight_WarriorShouldFailAndDie() {
        //Given
        var warrior = new Warrior();
        var knight = new Knight();

        //When
        var battleResult = Battle.fight(warrior, knight);
        var isWarriorAlive = warrior.isAlive();

        //Then
        assertFalse(battleResult);
        assertFalse(isWarriorAlive);
    }

    @Test
    @DisplayName("4. Fight: Knight attacks warrior and should win & stay alive")
    void whenKnightAttacksWarrior_KnightShouldWinAndStayAlive() {
        //Given
        var knight = new Knight();
        var warrior = new Warrior();

        //When
        var battleResult = Battle.fight(knight, warrior);
        var isKnightAlive = knight.isAlive();

        //Then
        assertTrue(battleResult);
        assertTrue(isKnightAlive);
    }

    @Test
    @DisplayName("5. Fight: Warrior attacks warrior and second one should be dead")
    void whenWarriorAttacksWarrior_BothShouldBeDead() {
        //Given
        var warrior1 = new Warrior();
        var warrior2 = new Warrior();

        //When
        Battle.fight(warrior1, warrior2);
        var isWarrior1Alive = warrior1.isAlive();
        var isWarrior2Alive = warrior2.isAlive();

        //Then
        assertTrue(isWarrior1Alive);
        assertFalse(isWarrior2Alive);
    }

    @Test
    @DisplayName("6. Fight: Knight attacks knight and second one should be dead")
    void whenKnightAttacksKnight_BothShouldBeDead() {
        //Given
        var knight1 = new Knight();
        var knight2 = new Knight();

        //When
        Battle.fight(knight1, knight2);
        var isKnight1Alive = knight1.isAlive();
        var isKnight2Alive = knight2.isAlive();

        //Then
        assertTrue(isKnight1Alive);
        assertFalse(isKnight2Alive);
    }

    @Test
    @DisplayName("7. Fight: Rookie attacks defender and second one shouldn't lose any HP")
    void whenRookieAttacksDefender_DefenderShouldNotLoseAnyHp() {
        //Given
        var rookie = new Rookie();
        var defender = new Defender();
        var defenderHealthBeforeFight = defender.getHealth();

        //When
        Battle.fight(rookie, defender);
        var isRookieAlive = rookie.isAlive();
        var isDefenderAlive = defender.isAlive();
        var defenderHealthAfterFight = defender.getHealth();

        //Then
        assertFalse(isRookieAlive);
        assertTrue(isDefenderAlive);
        assertEquals(defenderHealthBeforeFight, defenderHealthAfterFight);
    }

    @Test
    @DisplayName("8. Fight: Vampire attacks defender and should be dead")
    void whenVampireAttacksDefender_VampireShouldWinTheDuel() {
        //Given
        var vampire = new Vampire();
        var defender = new Defender();

        //When
        var battleResult = Battle.fight(vampire, defender);
        var isVampireAlive = vampire.isAlive();
        var isDefenderAlive = defender.isAlive();

        //Then
        assertFalse(battleResult);
        assertFalse(isVampireAlive);
        assertTrue(isDefenderAlive);
    }

    @Test
    @DisplayName("9. Fight: Vampire attacks warrior and should win & stay alive")
    void whenVampireAttacksWarrior_VampireShouldWinTheDuel() {
        //Given
        var vampire = new Vampire();
        var warrior = new Warrior();

        //When
        var battleResult = Battle.fight(vampire, warrior);
        var isVampireAlive = vampire.isAlive();
        var isWarriorAlive = warrior.isAlive();

        //Then
        assertTrue(battleResult);
        assertTrue(isVampireAlive);
        assertFalse(isWarriorAlive);
    }

    @Test
    @DisplayName("9. Fight: Vampire attacks vampire the attacker Vampire should win")
    void whenVampireAttacksVampire_FirstVampire() {
        //Given
        var vampire1 = new Vampire();
        var vampire2 = new Vampire();

        //When
        var battleResult = Battle.fight(vampire1, vampire2);
        var isVampire1Alive = vampire1.isAlive();
        var isVampire2Alive = vampire2.isAlive();

        //Then
        assertTrue(battleResult);
        assertTrue(isVampire1Alive);
        assertFalse(isVampire2Alive);
    }

    private static class Rookie extends Warrior {
        @Override
        public int getAttack() {
            return 1;
        }
    }
}