package org.study.warriors.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.CanDefense;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.IWarlord;
import org.study.warriors.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Warlord extends Defender implements CanDefense, IWarlord {

    private static final Logger LOGGER = LoggerFactory.getLogger(Warlord.class);

    static final int INITIAL_HEALTH = 100;
    static final int INITIAL_ATTACK = 4;
    static final int DEFENSE = 2;

    public Warlord() {
        this(INITIAL_HEALTH, INITIAL_ATTACK, DEFENSE);
    }

    public Warlord(int health, int attack, int defense) {
        super(health, attack, defense);
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH + weaponEquipment.getWeaponModifiersOfGivenType(Weapon::getHealthModifier);
    }

    public List<IWarrior> sortSoldiers(Iterable<IWarrior> warlordSoldiers) {
        if (warlordSoldiers == null) {
            throw new NoSuchElementException("Warlord has no soldiers to rearrange their positions!");
        }
        LOGGER.debug("Warlord is sorting soldiers...");

        var sortedSoldiers = new ArrayList<IWarrior>();
        warlordSoldiers.forEach(sortedSoldiers::add);

        var sortedSoldiersSize = sortedSoldiers.size();

        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, Warlord.class, sortedSoldiersSize - 1);
        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, Lancer.class, 0);
        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, Healer.class, 1);
        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, Warlord.class, sortedSoldiersSize - 1);

        LOGGER.trace("Warlord has moved dead soldiers behind him");
        LOGGER.debug("Warlord has sorted all of his soldiers!");

        return sortedSoldiers;
    }
}
