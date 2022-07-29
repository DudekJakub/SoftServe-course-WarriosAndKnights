package org.study.warriors.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefenderTest {

    @Test
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