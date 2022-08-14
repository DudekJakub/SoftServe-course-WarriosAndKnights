package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.equipment.weapon.WeaponEquipment;
import org.study.warriors.model.interfaces.CanHeal;
import org.study.warriors.model.interfaces.IWarrior;

public class Healer extends Warrior implements CanHeal {

    private static final Logger LOGGER = LoggerFactory.getLogger(Healer.class);

    static final int INITIAL_HEALTH = 60;
    static final int INITIAL_ATTACK = 0;
    static final int INITIAL_HEAL_POWER = 2;

    private int healPower;

    public Healer() {
        this(INITIAL_HEALTH, INITIAL_ATTACK, INITIAL_HEAL_POWER);
    }

    public Healer(int health, int attack, int healPower) {
        super(health, attack);
        this.healPower = healPower;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    @Override
    public void setHealPower(int healPower) {
        this.healPower = healPower;
    }

    @Override
    public void setAttack(int attack) {
        LOGGER.trace("Healer's attack power cannot be change at the moment!");
    }

    @Override
    public void hit(IWarrior target) {
        LOGGER.trace("{} would like to attack the target {}, but he is so very pacifist!", this, target);
    }

    @Override
    public void updateParametersFromWeapons(WeaponEquipment weaponEquipment) {
        super.updateParametersFromWeapons(weaponEquipment);
        setHealPower(Math.max(0, getHealPower() + weaponEquipment.getUnappliedHealthPowerModifiers()));
    }
}
