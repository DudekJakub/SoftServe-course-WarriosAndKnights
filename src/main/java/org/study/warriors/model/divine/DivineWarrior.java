package org.study.warriors.model.divine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Warrior;
import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.divine.buff.Buff;
import org.study.warriors.model.divine.buff.ResistanceBuff;
import org.study.warriors.model.divine.modifier.DivineModifier;
import org.study.warriors.model.divine.spell.Spell;
import org.study.warriors.model.equipment.weapon.WeaponEquipment;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.Unit;
import org.study.warriors.model.observer.Observer;
import org.study.warriors.model.request.Chain;
import org.study.warriors.model.request.IRequest;
import org.study.warriors.model.weapon.Weapon;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DivineWarrior implements Unit, IWarrior, Chain, Cloneable, DivineSoldier  {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivineWarrior.class);

    private final Set<Buff> buffs;
    private final Set<Spell> spells;
    protected DivineModifier divineModifier;
    protected IWarrior decorated;
    protected boolean isDayTime;
    protected boolean isResistanceActive = false;

    public DivineWarrior(IWarrior decorated) {
        this.decorated = decorated;
        this.buffs = new HashSet<>();
        this.spells = new HashSet<>();
    }

    @Override
    public Set<Buff> getBuffs() {
        return buffs;
    }

    @Override
    public Set<Spell> getSpells() {
        return spells;
    }

    @Override
    public int getAttack() {
        return decorated.getAttack();
    }

    @Override
    public int getHealth() {
        return decorated.getHealth();
    }

    @Override
    public int getInitialHealth() {
        return decorated.getInitialHealth();
    }

    @Override
    public WeaponEquipment getEquipment() {
        return decorated.getEquipment();
    }

    @Override
    public int getLastReceivedDamage() {
        return decorated.getLastReceivedDamage();
    }

    @Override
    public Chain getNextInChain() {
        return decorated.getNextInChain();
    }

    @Override
    public Chain getPreviousInChain() {
        return decorated.getPreviousInChain();
    }

    public DivineModifier getDivineModifier() {
        return divineModifier;
    }

    public boolean isDayTime() {
        return isDayTime;
    }

    @Override
    public void setAttack(int attack) {
        decorated.setAttack(attack);
    }

    @Override
    public void setHealth(int health) {
        decorated.setHealth(health);
    }

    @Override
    public void setDivineModifier(DivineModifier divineModifier) {
        this.divineModifier = divineModifier;
    }

    @Override
    public void setResistanceActive(boolean active) {
        this.isResistanceActive = active;
    }

    @Override
    public void setLastReceivedDamage(int damage) {
        decorated.setLastReceivedDamage(damage);
    }

    @Override
    public void setNextInChain(Chain nextInChain) {
        decorated.setNextInChain(nextInChain);
    }

    @Override
    public void setPreviousInChain(Chain previousInChain) {
        decorated.setPreviousInChain(previousInChain);
    }

    @Override
    public void registerObserver(Observer observer) {
        decorated.registerObserver(observer);
    }

    @Override
    public void notifyObserver() {
        decorated.notifyObserver();
    }

    @Override
    public void removeObserver(Observer observer) {
        decorated.removeObserver(observer);
    }

    @Override
    public void equipWeapons(Weapon... weapons) {
        decorated.equipWeapons(weapons);
    }

    @Override
    public void updateParametersFromWeapons(WeaponEquipment weaponEquipment) {
        decorated.updateParametersFromWeapons(weaponEquipment);
    }

    @Override
    public void updateParameterFromSingleWeapon(Weapon weapon) {
        decorated.updateParameterFromSingleWeapon(weapon);
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        decorated.reduceHealthBasedOnDamage(damage);
    }

    @Override
    public void receiveDamage(IDamage damage, IWarrior dealer) {
        applyResistanceBuffToGivenDamage(damage);
        if (!isResistanceActive) {
            decorated.receiveDamage(damage, dealer);
        }
        isResistanceActive = false;
    }

    @Override
    public void increaseHealthBasedOnHeal(int heal) {
        decorated.increaseHealthBasedOnHeal(heal);
    }

    @Override
    public void hit(IWarrior target) {
        decorated.hit(target);
    }

    @Override
    public void sendRequest(IRequest request, Chain target) {
        decorated.sendRequest(request, target);
    }

    public void updateDayNightCycle(boolean isDayTime) {
        this.isDayTime = isDayTime;
    }

    public void applyResistanceBuffToGivenDamage(IDamage damage) {
        LOGGER.trace("{} is checking resistances...", this);
        buffs.stream()
                .filter(buff -> buff instanceof ResistanceBuff)
                .forEach(buff -> buff.apply(damage));
        cleanBuffs();
    }

    public void castSupportSpell(DivineSoldier target) {
        LOGGER.trace("{} is checking spells...", this);
        spells.forEach(spell -> spell.apply(target));
        cleanSpells();
    }

    public void cleanBuffs() {

    }

    public void cleanSpells() {
    }


    @Override
    public String toString() {
        return decorated.toString();
    }

    @Override
    public DivineWarrior clone() {
        try {
            DivineWarrior clone = (DivineWarrior) super.clone();
            clone.decorated = ((Warrior) this.decorated).clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
