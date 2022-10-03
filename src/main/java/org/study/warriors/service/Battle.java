package org.study.warriors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Army;
import org.study.warriors.model.divine.DivineArmy;
import org.study.warriors.model.divine.goddess.Goddess;
import org.study.warriors.model.divine.goddess.Sun;
import org.study.warriors.model.interfaces.IWarrior;

public class Battle {

    private static final Logger LOGGER = LoggerFactory.getLogger(Battle.class);

    private Battle() {
        throw new IllegalStateException("Utility class! Do not invoke this constructor!");
    }

    public static boolean fight(IWarrior attacker, IWarrior defender) {
        LOGGER.debug("Duel between {} (AP {} | HP {}) ATTACKER vs. DEFENDER {} (AP {} | HP {}) begins!", attacker, attacker.getAttack(), attacker.getHealth(), defender, defender.getAttack(), defender.getHealth());

        while (attacker.isAlive() && defender.isAlive()) {
            attacker.hit(defender);
            if (!defender.isAlive()) {
                LOGGER.debug("Duel between {} (HP left {}) and {} (HP left {}) is over!", attacker, attacker.getHealth(), defender, defender.getHealth());
                return attacker.isAlive();
            } else {
                defender.hit(attacker);
            }
        }
        LOGGER.debug("Duel between {} (HP left {}) and {} (HP left {}) is over!", attacker, attacker.getHealth(), defender, defender.getHealth());
        return attacker.isAlive();
    }

    /** We assigned two variables which reference to our Iterator method. This method gives us firstAlive unit.
     *  Then in while loop we check if both armies still have next alive unit.
     *  While-loop body contains fight method & return statement == info if there is somebody still alive from army 1st.*/
    public static boolean fight(Army attackers, Army defenders) {
        var it1 = attackers.firstAlive();
        var it2 = defenders.firstAlive();
        int roundCounter = 0;
        int dayNightCycleCounter = setDayNightCycleAccordingToArmy(attackers);

        updateArmiesWithDayNightCycleChange(dayNightCycleCounter, attackers, defenders);
        attackers.lineUp();
        defenders.lineUp();
        Army.moveUnitsForArmies(attackers, defenders);
        LOGGER.debug("Army before battle (with HP & insertion order): " + "ATTACKERS: " + attackers.getSoldiersAndTheirHp() + " | DEFENDERS: " + defenders.getSoldiersAndTheirHp());
        LOGGER.debug("ArmyBattle has begun!");
        while (it1.hasNext() && it2.hasNext()) {
            LOGGER.trace("---- ROUND: " + ++roundCounter + " ----");
            fight(it1.next(), it2.next());
            Army.moveUnitsForArmies(attackers, defenders);
            dayNightCycleCounter++;
            updateArmiesWithDayNightCycleChange(dayNightCycleCounter, attackers, defenders);
            LOGGER.debug("Alive soldiers: AttackerSide = {} | DefenderSide = {}\n", attackers.getAliveSoldiers(), defenders.getAliveSoldiers());
        }
        LOGGER.debug("ArmyBattle has ended!");
        LOGGER.debug("Army after battle (with HP): " + "ATTACKERS: " + attackers.getSoldiersAndTheirHp() + " | DEFENDERS: " + defenders.getSoldiersAndTheirHp());

        return it1.hasNext();
    }

    public static boolean straightFight(Army leftArmy, Army rightArmy) {
        LOGGER.debug("The straight fight between {} and {} has begun!", leftArmy.getSoldiersAndTheirHp(), rightArmy.getSoldiersAndTheirHp());
        while (leftArmy.isAlive() && rightArmy.isAlive()) {
            int smallerArmySize = Math.min(leftArmy.getArmySize(), rightArmy.getArmySize());
            for (int i = 0; i < smallerArmySize; i++) {
                fight(leftArmy.unitAtPosition(i), rightArmy.unitAtPosition(i));
            }
            LOGGER.trace("Removing dead soldiers from armies...");
            leftArmy.removeDeadSoldiersFromArmy();
            rightArmy.removeDeadSoldiersFromArmy();
        }
        LOGGER.debug("The straight fight between {} and {} has ended!", leftArmy.getSoldiersAndTheirHp(), rightArmy.getSoldiersAndTheirHp());
        return leftArmy.isAlive();
    }

    private static void updateArmiesWithDayNightCycleChange(int dayNightCycleCounter, Army... armies) {
        boolean isDayTime = dayNightCycleCounter == 0 || (dayNightCycleCounter + 3) % 3 != 0;

        LOGGER.trace("[DAY/NIGHT CYCLE TIME: {}]", isDayTime ? "DAY" : "NIGHT");
        for (var army : armies) {
            if (army instanceof DivineArmy divineArmy) {
                divineArmy.updateSoldiersIfItsDayTime(isDayTime);
            }
        }
    }

    private static int setDayNightCycleAccordingToArmy(Army army) {
        if (army instanceof DivineArmy divineArmy && divineArmy.getDivineType() == Goddess.GoddessType.SUN) {
            return 0;
        } else {
            return 3;
        }
    }
}
