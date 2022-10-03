package org.study.warriors.model.equipment;

import org.study.warriors.model.weapon.Weapon;

public interface IWeaponEquipment {
    void addWeapons(Weapon... weapons);
    void markWeaponsWithInstanceModifiersAsApplied(Weapon... weapons);
    void markSingleWeaponAsApplied(Weapon weapon);
}
