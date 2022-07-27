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
        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    void whenWarriorDuelsKnight_KnightShouldWin() {
        //Given
        var warrior = new Warrior();
        var knight = new Knight();

        //When
        var battleResult = Battle.fight(warrior, knight);

        //Then
        assertFalse(battleResult);
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
    @DisplayName("3. Fight: Knight should win")
    void whenKnightFightsVsWarrior_WarriorShouldLoseTheBattle() {
        //Given
        var knight = new Knight();
        var warrior = new Warrior();

        //When
        var battleResult = Battle.fight(knight, warrior);

        //Then
        assertFalse(battleResult);
    }
}