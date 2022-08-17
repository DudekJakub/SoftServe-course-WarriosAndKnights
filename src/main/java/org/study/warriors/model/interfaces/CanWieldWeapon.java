package org.study.warriors.model.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.equipment.weapon.WeaponEquipment;
import org.study.warriors.model.observer.Observable;
import org.study.warriors.model.observer.Observer;
import org.study.warriors.model.weapon.Weapon;

import java.util.List;

public interface CanWieldWeapon extends HasHealth, CanAttack {

    Logger LOGGER = LoggerFactory.getLogger(CanWieldWeapon.class);

    WeaponEquipment getEquipment();

    default void equipWeapons(Weapon... weapons) {
        LOGGER.trace("{} is equipping new weapons: {}", this, weapons);

        Weapon[] clonedWeaponArray = new Weapon[weapons.length];
        for (int i = 0; i < weapons.length; i++) {
            var clonedWeapon = weapons[i].clone();
            clonedWeaponArray[i] = clonedWeapon;
        }

        getEquipment().addWeapons(clonedWeaponArray);
        updateParametersFromWeapons(getEquipment());
        getEquipment().markWeaponsWithInstanceModifiersAsApplied(clonedWeaponArray);

        LOGGER.trace("Weapons equipped. Instant modifiers applied!");

        for (var weapon : List.of(clonedWeaponArray)) {
            if (weapon instanceof Observer observer) {
                ((Observable) this).registerObserver(observer);
            }
        }
    }

    default void updateParametersFromWeapons(WeaponEquipment weaponEquipment) {
        setHealth(Math.max(0, getHealth() + weaponEquipment.getUnappliedHealthModifiers()));
        setAttack(Math.max(0, getAttack() + weaponEquipment.getUnappliedAttackModifiers()));
    }

    default void updateParameterFromSingleWeapon(Weapon weapon) {
        setHealth(Math.max(0, getHealth() + weapon.getHealthModifier()));
        setAttack(Math.max(0, getAttack() + weapon.getAttackModifier()));
        getEquipment().markSingleWeaponAsApplied(weapon);
        LOGGER.trace("{} marked as applied (by {})", weapon, this);
    }
}
