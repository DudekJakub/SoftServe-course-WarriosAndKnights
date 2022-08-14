package org.study.warriors.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.study.warriors.model.decorator.HealerDecorator;
import org.study.warriors.model.decorator.WarriorDecorator;
import org.study.warriors.model.weapon.Dagger;
import org.study.warriors.model.weapon.Weapon;
import org.study.warriors.service.Battle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeaponSuiteTest {

    @Test
    @DisplayName("1. Weapon equip: when Warrior equips Sword, his health and attack should increase by 5 & 2 respectively")
    void test1() {
        //Given
        var sword = Weapon.newSword();
        var warrior = new Warrior();

        //When
        warrior.equipWeapons(sword);

        //Then
        assertEquals(55, warrior.getHealth());
        assertEquals(7, warrior.getAttack());
    }

    @Test
    @DisplayName("2. Weapon equip: when Defender equips Shield, his health and defense should increase by 20 & 2 respectively but attack should decrease by 1")
    void test2() {
        //Given
        var shield = Weapon.newShield();
        var defender = new Defender();

        //When
        defender.equipWeapons(shield);

        //Then
        assertEquals(80, defender.getHealth());
        assertEquals(2, defender.getAttack());
        assertEquals(4, defender.getDefense());
    }

    @Test
    @DisplayName("3. Weapon equip: when Vampire equips Great Axe, his attack and vampirism should increase by 5 & 10 respectively but health should decrease by 15")
    void test3() {
        //Given
        var greatAxe = Weapon.newGreatAxe();
        var vampire = new Vampire();

        //When
        vampire.equipWeapons(greatAxe);

        //Then
        assertEquals(25, vampire.getHealth());
        assertEquals(9, vampire.getAttack());
        assertEquals(60, vampire.getVampirism());
    }

    @Test
    @DisplayName("4. Weapon equip: when Healer equips MagicWand, his health and heal power should increase by 30 & 3 respectively (healer's attack cannot be changed)")
    void test4() {
        //Given
        var magicWand = Weapon.newMagicWand();
        var healer = new Healer();

        //When
        healer.equipWeapons(magicWand);

        //Then
        assertEquals(90, healer.getHealth());
        assertEquals(0, healer.getAttack());
        assertEquals(5, healer.getHealPower());
    }

    @Test
    @DisplayName("5. Weapon equip: equipping the same weapon shouldn't lead to sharing it between warriors")
    void test5() {
        //Given
        var sword1 = Weapon.newSword();
        var sword2 = Weapon.newSword();
        var warrior1 = new Warrior();
        var vampire1 = new Vampire();

        //When
        warrior1.equipWeapons(sword1);
        vampire1.equipWeapons(sword1);

        //Then
        assertEquals(7, warrior1.getAttack());
        assertEquals(55, warrior1.getHealth());
        assertEquals(6, vampire1.getAttack());
    }

    @Test
    @DisplayName("6. Weapon equip (for decorators): equipping weapons by decorated warriors should work as for normal warriors")
    void test6() {
        //Given
        var sword = Weapon.newSword();
        var decoratedWarrior1 = new WarriorDecorator(new Warrior());
        var decoratedWarrior2 = new WarriorDecorator(new Warrior());

        //When
        decoratedWarrior1.equipWeapons(sword);
        decoratedWarrior2.equipWeapons(sword);

        //Then
        assertEquals(7, decoratedWarrior1.getAttack());
        assertEquals(55, decoratedWarrior1.getHealth());
        assertEquals(7, decoratedWarrior2.getAttack());
        assertEquals(55, decoratedWarrior2.getHealth());
    }

    @Test
    @DisplayName("7. Duel between x2 equipped Warriors: both Warriors should be boosted by weapons and second first one should win")
    void test7() {
        //Given
        var sword = Weapon.newSword();
        var shield = Weapon.newShield();
        var warrior1 = new Warrior();
        var warrior2 = new Warrior();

        //When
        warrior1.equipWeapons(sword);
        warrior2.equipWeapons(shield, new Dagger(), shield);

        assertEquals(55, warrior1.getHealth());
        assertEquals(7, warrior1.getAttack());
        assertEquals(90, warrior2.getHealth());
        assertEquals(3, warrior2.getAttack());

        var battleResult = Battle.fight(warrior1, warrior2);

        //Then
        assertTrue(battleResult);
    }

    @Test
    @DisplayName("8. Weapon equip (for decorators): equipping lazy initial-weapons by decorated warriors should work as for normal warriors")
    void test8() {
        //Given
        var dagger = new Dagger();
        var decoratedWarrior1 = new WarriorDecorator(new Warrior());
        var decoratedWarrior2 = new WarriorDecorator(new Warrior());

        //When
        decoratedWarrior1.equipWeapons(dagger);
        decoratedWarrior2.equipWeapons(dagger);

        var battleResult = Battle.fight(decoratedWarrior1, decoratedWarrior2);

        //Then
        assertEquals(8, decoratedWarrior1.getAttack());
        assertEquals(8, decoratedWarrior2.getAttack());
    }

    @Test
    @DisplayName("9. Weapon equip: single warrior equips the same sword two times so his attack = 9 and health = 60")
    void test9() {
        //Given
        var sword = Weapon.newSword();
        var warrior = new Warrior();

        //When
        warrior.equipWeapons(sword, sword);

        //Then
        assertEquals(9, warrior.getAttack());
        assertEquals(60, warrior.getHealth());
    }

    @Test
    @DisplayName("10. Weapon equip: single vampire equips the same katana two times so his attack = 12 and health = 0 and vampirism = 150")
    void test10() {
        //Given
        var katana = Weapon.newKatana();
        var vampire = new Vampire();

        //When
        vampire.equipWeapons(katana, katana);

        //Then
        assertEquals(0, vampire.getHealth());
        assertEquals(16, vampire.getAttack());
        assertEquals(150, vampire.getVampirism());
    }

    @Test
    @DisplayName("11. Weapon equip: ")
    void test11() {
        //Given
        var magicWand = Weapon.newMagicWand();
        var sword = Weapon.newSword();
        var healer = new Healer();
        var warrior = new Warrior();

        //When
        warrior.equipWeapons(sword);
        healer.equipWeapons(magicWand);

        warrior.setHealth(54);
        healer.heal(warrior, healer.getHealPower());

        //Then
        assertEquals(55, warrior.getHealth());
    }

    @Test
    @DisplayName("12. Weapon equip: ")
    void test12() {
        //Given
        var greatAxe = Weapon.newGreatAxe();
        var vampire = new Vampire();
        var warrior = new Warrior();

        //When
        vampire.equipWeapons(greatAxe);
        vampire.hit(warrior);

        //Then
        assertEquals(25, vampire.getHealth());
    }

    @Test
    void test13givenWoundedWarrior10Hp_whenEquippedWithSword_thenHisHpShouldIncreaseTo30() {
        //Given
        var warrior = new Warrior();
        warrior.setHealth(10);

        //When
        var sword = Weapon.newSword();
        warrior.equipWeapons(sword);

        //Then
        assertEquals(15, warrior.getHealth());
    }

    @Test
    void test14() {
        //Given
        var katana = Weapon.newKatana();
        var vampire = new Vampire();
        var warrior = new Warrior();

        //When
        vampire.equipWeapons(katana);
        vampire.hit(warrior);

        //Then
        assertEquals(20, vampire.getHealth());
    }


    @Test
    void test15() {
        //Given
        var katana = Weapon.newKatana();
        var magicWand = Weapon.newMagicWand();
        var vampire = new WarriorDecorator(new Vampire());
        var healer = new HealerDecorator(new Healer());

        //When
        vampire.equipWeapons(katana);
        healer.equipWeapons(magicWand);
        healer.heal(vampire, healer.getHealPower());

        //Then
        assertEquals(20, vampire.getHealth());
    }

    @Test
    @DisplayName("")
    void whenWoundedVampire25HpEquipsKatanaAndHitsWarriorTwice_HisHealthShouldBe20() {
        //Given
        var vampire = new Vampire();
        var warrior = new Warrior();
        vampire.reduceHealthBasedOnDamage(15);

        //When
        vampire.equipWeapons(Weapon.newKatana());
        vampire.hit(warrior);
        var healthAfterFirstHit = vampire.getHealth();
        vampire.hit(warrior);
        var healthAfterSecondHit = vampire.getHealth();

        //Then
        assertEquals(15, healthAfterFirstHit);
        assertEquals(20, healthAfterSecondHit);
    }
}
