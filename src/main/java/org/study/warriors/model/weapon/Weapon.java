package org.study.warriors.model.weapon;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Weapon implements Cloneable {

    protected final String weaponName;
    protected final boolean instantModifiersApply;
    protected boolean modifierApplied;
    protected final int healthModifier;
    protected final int attackModifier;
    protected final int defenseModifier;
    protected final int vampirismModifier;
    protected final int healPowerModifier;

    private static final Weapon.WeaponBuilder SWORD =
            Weapon.builder()
                    .weaponName("SWORD")
                    .instantModifiersApply(true)
                    .healthModifier(5)
                    .attackModifier(2);

    private static final Weapon.WeaponBuilder SHIELD =
            Weapon.builder()
                    .weaponName("SHIELD")
                    .instantModifiersApply(true)
                    .healthModifier(20)
                    .attackModifier(-1)
                    .defenseModifier(2);

    private static final Weapon.WeaponBuilder GREAT_AXE =
            Weapon.builder()
                    .weaponName("GREAT AXE")
                    .instantModifiersApply(true)
                    .healthModifier(-15)
                    .attackModifier(5)
                    .defenseModifier(-2)
                    .vampirismModifier(10);

    private static final Weapon.WeaponBuilder KATANA =
            Weapon.builder()
                    .weaponName("KATANA")
                    .instantModifiersApply(true)
                    .healthModifier(-20)
                    .attackModifier(6)
                    .defenseModifier(-5)
                    .vampirismModifier(50);

    private static final Weapon.WeaponBuilder MAGIC_WAND =
            Weapon.builder()
                    .weaponName("MAGIC WAND")
                    .instantModifiersApply(true)
                    .healthModifier(30)
                    .attackModifier(3)
                    .healPowerModifier(3);

    public static Weapon newSword() {
        return SWORD.build();
    }

    public static Weapon newShield() {
        return SHIELD.build();
    }

    public static Weapon newGreatAxe() {
        return GREAT_AXE.build();
    }

    public static Weapon newKatana() {
        return KATANA.build();
    }

    public static Weapon newMagicWand() {
        return MAGIC_WAND.build();
    }

    public void setModifierApplied(boolean modifierApplied) {
        this.modifierApplied = modifierApplied;
    }

    @Override
    public String toString() {
        return "{" + getWeaponName() + ", isModifierApplied = " + isModifierApplied() + " | isInstanceModifier = " + instantModifiersApply + "}";
    }

    @Override
    public Weapon clone() {
        try {
            return (Weapon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
