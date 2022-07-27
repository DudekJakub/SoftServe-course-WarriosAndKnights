package org.javabasictraining;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertFalse(Battle.fight(chuck, bruce));
        assertTrue(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    @DisplayName("1. Single Hit By Warrior: Knight should lose 5 HP")
    void whenWarriorHitsKnight_KnightShouldLoseFiveHp() {
        //Given
        var warrior = new Warrior();
        var knight = new Knight();
        var knightInitialHealthPoints = knight.getHealth();

        //When
        warrior.hit(knight);
        var knightReducedHealthPoints = knight.getHealth();

        //Then
        assertEquals(5, knightInitialHealthPoints - knightReducedHealthPoints);
    }

    @Test
    @DisplayName("2. Single Hit By Knight: Warrior should lose 7 HP")
    void whenKnightHitsWarrior_WarriorShouldLoseSevenHp() {
        //Given
        var knight = new Knight();
        var warrior = new Warrior();
        var warriorInitialHealthPoints = warrior.getHealth();

        //When
        knight.hit(warrior);
        var warriorReducedHealthPoints = warrior.getHealth();

        //Then
        assertEquals(7, warriorInitialHealthPoints - warriorReducedHealthPoints);
    }

    @Test
    @DisplayName("3. Fight: Warrior defends himself against knight and should fail & die")
    void whenWarriorDefendsHimselfVsKnight_WarriorShouldFailAndDie() {
        //Given
        var warrior = new Warrior();
        var knight = new Knight();

        //When
        var battleResult = Battle.fight(knight, warrior);
        var isWarriorAlive = warrior.isAlive();

        //Then
        assertFalse(battleResult);
        assertFalse(isWarriorAlive);
    }

    @Test
    @DisplayName("4. Fight: Knight defends himself against warrior and should win & stay alive")
    void whenKnightDefendsHimselfVsWarrior_KnightShouldWinAndStayAlive() {
        //Given
        var knight = new Knight();
        var warrior = new Warrior();

        //When
        var battleResult = Battle.fight(warrior, knight);
        var isKnightAlive = knight.isAlive();

        //Then
        assertTrue(battleResult);
        assertTrue(isKnightAlive);
    }

    @Test
    @DisplayName("5. Fight: Warrior attack knight and should fail & die")
    void whenWarriorAttacksKnight_WarriorShouldFailAndDie() {
        //Given
        var warrior = new Warrior();
        var knight = new Knight();

        //When
        var battleResult = Battle.fight(warrior, knight);
        var isWarriorAlive = warrior.isAlive();

        //Then
        assertTrue(battleResult);
        assertFalse(isWarriorAlive);
    }

    @Test
    @DisplayName("6. Fight: Knight attacks warrior and should win & stay alive")
    void whenKnightAttacksWarrior_KnightShouldWinAndStayAlive() {
        //Given
        var knight = new Knight();
        var warrior = new Warrior();

        //When
        var battleResult = Battle.fight(knight, warrior);
        var isKnightAlive = knight.isAlive();

        //Then
        assertFalse(battleResult);
        assertTrue(isKnightAlive);
    }
}