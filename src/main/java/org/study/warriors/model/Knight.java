package org.study.warriors.model;

import org.study.warriors.model.weapon.Weapon;

public class Knight extends Warrior {
    static final int ATTACK = 7;

    public Knight() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH + weaponEquipment.getWeaponModifiersOfGivenType(Weapon::getHealthModifier);
    }
}
