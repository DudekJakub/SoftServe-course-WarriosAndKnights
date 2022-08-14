package org.study.warriors.model;

import org.junit.jupiter.api.Test;
import org.study.warriors.model.decorator.WarriorDecorator;

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
        assertNotEquals(warrior.getAttack(), clonedWarrior.getAttack());
        assertEquals(6, clonedWarrior.getAttack());
    }

    @Test
    void deepCloneOfWarriorDecorator() {
        //Given
        var warriorDeco = new WarriorDecorator(new Warrior());
        var clonedWarriorDeco = warriorDeco.clone();
        var warriorDecoHp = warriorDeco.getHealth();
        var clonedWarriorDecoHp = clonedWarriorDeco.getHealth();

        //When
        warriorDeco.setHealth(40);
        var newWarriorDecoHp = warriorDeco.getHealth();
        var clonedWarriorDecoOldHp = clonedWarriorDeco.getHealth();

        //Then
        assertEquals(10, warriorDecoHp - newWarriorDecoHp);
        assertEquals(clonedWarriorDecoHp, clonedWarriorDecoOldHp);
    }
}