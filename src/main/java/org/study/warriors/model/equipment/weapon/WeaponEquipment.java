package org.study.warriors.model.equipment.weapon;

import org.study.warriors.model.equipment.IWeaponEquipment;
import org.study.warriors.model.weapon.Weapon;

import java.util.*;
import java.util.function.ToIntFunction;

public class WeaponEquipment implements IWeaponEquipment {

    private final List<Weapon> weaponList = new ArrayList<>();

    @Override
    public void addWeapons(Weapon... weapons) {
        this.weaponList.addAll(Arrays.asList(weapons));
    }

    public int getUnappliedWeaponModifiers(ToIntFunction<? super Weapon> function) {
        return weaponList.stream()
                         .filter(w -> w.isInstantModifiersApply() && !w.isModifierApplied())
                         .mapToInt(function)
                         .sum();
    }

    public int getHealthWeaponModifiers() {
        return weaponList.stream()
                         .mapToInt(Weapon::getHealthModifier)
                         .sum();
    }

    public int getUnappliedAttackModifiers() {
        return getUnappliedWeaponModifiers(Weapon::getAttackModifier);
    }

    public int getUnappliedHealthModifiers() {
        return getUnappliedWeaponModifiers(Weapon::getHealthModifier);
    }

    public int getUnappliedDefenseModifiers() {
        return getUnappliedWeaponModifiers(Weapon::getDefenseModifier);
    }

    public int getUnappliedVampirismModifiers() {
        return getUnappliedWeaponModifiers(Weapon::getVampirismModifier);
    }

    public int getUnappliedHealthPowerModifiers() {
        return getUnappliedWeaponModifiers(Weapon::getHealPowerModifier);
    }

    public void markWeaponsWithInstanceModifiersAsApplied(Weapon... weapons) {
        weaponList.forEach(weapon -> {

            if (List.of(weapons).contains(weapon) && weapon.isInstantModifiersApply()) {
                var weaponIndexInList = weaponList.indexOf(weapon);
                this.weaponList.get(weaponIndexInList).setModifierApplied(true);
            }
        });
    }

    public void markSingleWeaponAsApplied(Weapon weapon) {
        var weaponIndexInList = weaponList.indexOf(weapon);
        weaponList.get(weaponIndexInList).setModifierApplied(true);
    }
}
