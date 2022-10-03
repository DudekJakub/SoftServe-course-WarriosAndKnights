package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.equipment.weapon.WeaponEquipment;
import org.study.warriors.model.interfaces.CanHeal;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.IRequest;
import org.study.warriors.model.request.type.HealRequest;
import org.study.warriors.model.weapon.Weapon;

public class Healer extends Warrior implements CanHeal {

    private static final Logger LOGGER = LoggerFactory.getLogger(Healer.class);

    static final int INITIAL_HEALTH = 60;
    static final int INITIAL_ATTACK = 0;
    static final int INITIAL_HEAL_POWER = 2;
    static final int INITIAL_HEAL_ESSENCE = 10;

    private int healPower;
    private int healEssence;

    public Healer() {
        this(INITIAL_HEALTH, INITIAL_ATTACK, INITIAL_HEAL_POWER, INITIAL_HEAL_ESSENCE);
    }

    public Healer(int health, int attack, int healPower, int healEssence) {
        super(health, attack);
        this.healPower = healPower;
        this.healEssence = healEssence;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH + weaponEquipment.getWeaponModifiersOfGivenType(Weapon::getHealthModifier);
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    @Override
    public int getHealEssence() {
        return healEssence;
    }

    @Override
    public void setHealPower(int healPower) {
        this.healPower = healPower;
    }

    @Override
    public void setHealEssence(int healEssence) {
        this.healEssence = healEssence;
    }

    @Override
    public void setAttack(int attack) {
        LOGGER.trace("Healer's attack power cannot be change at the moment!");
    }

    @Override
    public void hit(IWarrior target) {
        LOGGER.trace("---- {} IN ACTION ----", this);
        LOGGER.trace("{} would like to attack the target {}, but he is so very pacifist!", this, target);
    }

    @Override
    public void receiveDamage(IDamage damage, IWarrior dealer) {
        LOGGER.trace("{} is receiving damage {}", this, damage.getHitPoints());
        reduceHealthBasedOnDamage(damage.getHitPoints());
    }

    @Override
    public void processRequest(IRequest request) {
        if (request instanceof HealRequest && isAlive()) {
            if (getHealEssence() <= 0) {
                LOGGER.trace("{} has no more essence! Cannot heal!", this);
                return;
            }
            LOGGER.trace("{} is currently process...", request.getRequestName());
            setHealEssence(getHealEssence() - 1);
            heal(request.getRequestSender(), getHealPower());
            LOGGER.trace("{} has been processed!", request.getRequestName());
        } else {
            super.processRequest(request);
        }
    }

    @Override
    public void updateParametersFromWeapons(WeaponEquipment weaponEquipment) {
        super.updateParametersFromWeapons(weaponEquipment);
        setHealPower(Math.max(0, getHealPower() + weaponEquipment.getUnappliedHealthPowerModifiers()));
    }
}
