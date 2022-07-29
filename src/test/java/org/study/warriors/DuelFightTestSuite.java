package org.study.warriors;

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

    @Test
    @DisplayName("7. Fight: Warrior attacks warrior and second one should be dead")
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
    @DisplayName("8. Fight: Knight attacks knight and second one should be dead")
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
    @DisplayName("9. Fight: Rookie attack defender and second one shouldn't lose any HP")
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

    private static class Rookie extends Warrior {
        @Override
        public int getAttack() {
            return 1;
        }
    }
}