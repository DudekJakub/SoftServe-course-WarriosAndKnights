package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.equipment.weapon.WeaponEquipment;
import org.study.warriors.model.interfaces.CanDrainLife;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.weapon.Weapon;

public class Vampire extends Warrior implements CanDrainLife {

    private static final Logger LOGGER = LoggerFactory.getLogger(Vampire.class);

    static final int INITIAL_HEALTH = 40;
    static final int ATTACK = 4;
    static final int VAMPIRISM = 50;

    private int vampirism;
    private int lastDealtDamage;

    public Vampire() {
        this(INITIAL_HEALTH, ATTACK, VAMPIRISM);
    }

    public Vampire(int health, int attack, int vampirism) {
        super(health, attack);
        this.vampirism = vampirism;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH + weaponEquipment.getWeaponModifiersOfGivenType(Weapon::getHealthModifier);
    }

    @Override
    public int getVampirism() {
        return vampirism;
    }

    @Override
    public int getLastDealtDamage() {
        return lastDealtDamage;
    }

    @Override
    public void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public void setLastDealtDamage(int lastDealtDamage) {
        this.lastDealtDamage = lastDealtDamage;
    }

    @Override
    public void hit(IWarrior target) {
        super.hit(target);
        drainLifeBasedOnDealtAttack(target);
        setLastDealtDamage(target.getLastReceivedDamage());
    }

    @Override
    public void drainLifeBasedOnDealtAttack(IWarrior target) {
        var healthValueAfterDrainLife = getHealth() > 0 ? getHealth() + ((target.getLastReceivedDamage() * getVampirism())/100) : 0;
        setHealth(Math.min(getInitialHealth(), healthValueAfterDrainLife));
        LOGGER.trace("{} drains life from his final damage dealt {} and heals himself by ({} * {})/100 | HP = {}", this, target.getLastReceivedDamage(), target.getLastReceivedDamage(), getVampirism(), getHealth());
    }

    @Override
    public void updateParametersFromWeapons(WeaponEquipment weaponEquipment) {
        super.updateParametersFromWeapons(weaponEquipment);
        setVampirism(Math.max(0, getVampirism() + weaponEquipment.getUnappliedVampirismModifiers()));
    }
}
