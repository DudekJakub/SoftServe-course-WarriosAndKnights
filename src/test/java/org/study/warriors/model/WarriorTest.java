package org.study.warriors.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    @Test
    void shallowClone() {
        //Given
        var warrior = new Warrior();

        //When
        var clonedWarrior = warrior.shallowClone();
        clonedWarrior.setAttack(6);

        //Then
        assertNotEquals(warrior.getAttack(), clonedWarrior.getAttack());
        assertEquals(6, clonedWarrior.getAttack());
    }
}