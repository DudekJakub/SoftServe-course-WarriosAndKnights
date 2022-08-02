package org.study.warriors.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleHitTestSuite {

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
    @DisplayName("3. Single Hit By Defender: Warrior should lose 3 HP")
    void whenDefenderHitsWarrior_WarriorShouldLoseThreeHp() {
        //Given
        var defender = new Defender();
        var warrior = new Warrior();
        var warriorInitialHealthPoints = warrior.getHealth();

        //When
        defender.hit(warrior);
        var warriorReducedHealthPoints = warrior.getHealth();

        //Then
        assertEquals(3, warriorInitialHealthPoints - warriorReducedHealthPoints);
    }

    @Test
    @DisplayName("4. Single Hit Received By Defender: Defender attacked by Warrior should lose only 3 HP (2 damage blocked)")
    void whenWarriorHitDefender_DefenderLoseThreeHp() {
        //Given
        var warrior = new Warrior();
        var defender = new Defender();
        var defenderHealthBeforeHit = defender.getHealth();

        //When
        warrior.hit(defender);
        var defenderHealthAfterHit = defender.getHealth();

        //Then
        assertEquals(3, defenderHealthBeforeHit - defenderHealthAfterHit);
    }

    @Test
    @DisplayName("5. Single Hit Received By Defender: Defender attacked by Rookie shouldn't lose any HP (whole damage blocked)")
    void whenRookieHitDefender_DefenderDoesntLoseHp() {
        //Given
        var rookie = new Rookie();
        var defender = new Defender();
        var defenderHealthBeforeHit = defender.getHealth();

        //When
        rookie.hit(defender);
        var defenderHealthAfterHit = defender.getHealth();

        //Then
        assertEquals(defenderHealthBeforeHit, defenderHealthAfterHit);
    }

    private static class Rookie extends Warrior {
        @Override
        public int getAttack() {
            return 1;
        }
    }
}
