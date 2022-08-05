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

    @Test
    @DisplayName("6. Single Hit Received By Defender: Defender attacked by Knight should lose 5 HP")
    void whenKnightHitDefender_DefenderLoseFiveHp() {
        //Given
        var knight = new Knight();
        var defender = new Defender();
        var defenderHealthBeforeHit = defender.getHealth();

        //When
        knight.hit(defender);
        var defenderHealthAfterHit = defender.getHealth();

        //Then
        assertEquals(5, defenderHealthBeforeHit - defenderHealthAfterHit);
    }

    @Test
    @DisplayName("7. Single Hit Received By Defender: Defender attacked by Vampire should lose 2 HP")
    void whenVampireHitDefender_DefenderLoseTwoHp() {
        //Given
        var vampire = new Vampire();
        var defender = new Defender();
        var defenderHealthBeforeHit = defender.getHealth();

        //When
        vampire.hit(defender);
        var defenderHealthAfterHit = defender.getHealth();

        //Then
        assertEquals(2, defenderHealthBeforeHit - defenderHealthAfterHit);
    }

    @Test
    @DisplayName("8. Single Hit By Vampire: Warrior should lose 4 HP")
    void whenVampireHitsWarrior_WarriorShouldLoseFourHp() {
        //Given
        var vampire = new Vampire();
        var warrior = new Warrior();
        var warriorInitialHealthPoints = warrior.getHealth();

        //When
        vampire.hit(warrior);
        var warriorReducedHealthPoints = warrior.getHealth();

        //Then
        assertEquals(4, warriorInitialHealthPoints - warriorReducedHealthPoints);
    }

    @Test
    @DisplayName("9. Single Hit By Wounded Vampire: Vampire should drain life in value of 50% of his attack")
    void whenWoundedVampireHitsWarrior_VampireShouldDrainLifeInValueOfHisHalfAttack() {
        //Given
        var vampire = new Vampire();
        var warrior = new Warrior();
        vampire.setHealth(30);

        //When
        vampire.hit(warrior);
        var vampireHealthAfterHit = vampire.getHealth();

        //Then
        assertEquals(32, vampireHealthAfterHit);
    }

    @Test
    @DisplayName("10. Single Hit By Wounded Vampire: Vampire should drain life in value of 50% of his attack reduced by defender's defense")
    void whenWoundedVampireHitsDefender_VampireShouldDrainLifeInValueOfHisHalfAttackReducedByDefense() {
        //Given
        var vampire = new Vampire();
        var defender = new Defender();
        vampire.setHealth(30);

        //When
        vampire.hit(defender);
        var vampireHealthAfterHit = vampire.getHealth();

        //Then
        assertEquals(31, vampireHealthAfterHit);
    }

    @Test
    @DisplayName("11. Single Hit By Full Health Vampire: Vampire shouldn't have more life after then his initial HP")
    void whenFullHpVampireHitsEnemy_VampireShouldNotCrossedHisInitialHealthWhileDrainingLife() {
        //Given
        var vampire = new Vampire();
        var warrior = new Warrior();
        var vampireInitialHealth = vampire.getHealth();

        //When
        vampire.hit(warrior);
        var vampireHealthAfterHit = vampire.getHealth();

        //Then
        assertEquals(vampireInitialHealth, vampireHealthAfterHit);
    }

    @Test
    @DisplayName("12. Single Hit By Wounded Vampire to 1 HP Warrior: Vampire should heal himself by 2 HP despite of Warrior's negative HP after hit")
    void whenWoundedVampireHitsOneHitEnemy_VampireShouldHealsHimselfAsNormal() {
        //Given
        var vampire = new Vampire();
        var warrior = new Warrior();
        vampire.setHealth(30);
        warrior.setHealth(1);

        //When
        vampire.hit(warrior);
        var vampireHealthAfterHit = vampire.getHealth();

        //Then
        assertEquals(32 ,vampireHealthAfterHit);
    }

    private static class Rookie extends Warrior {
        @Override
        public int getAttack() {
            return 1;
        }
    }
}
