package org.study.warriors.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    @Test
    void shallowCloneOfWarrior() {
        //Given
        var warrior = new Warrior();

        //When
        var clonedWarrior = warrior.clone();
        clonedWarrior.setHealth(30);

        var army1 = new Army();
        army1.addUnits(warrior, 2);

        //Then
        assertNotEquals(warrior.getHealth(), clonedWarrior.getHealth());
        assertEquals(5, clonedWarrior.getAttack());
    }
}