package org.study.warriors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Army;
import org.study.warriors.model.interfaces.IWarrior;

public class Battle {

    private static final Logger LOGGER = LoggerFactory.getLogger(Battle.class);

    private Battle() {
        throw new IllegalStateException("Utility class! Do not invoke this constructor!");
    }

    public static boolean fight(IWarrior attacker, IWarrior defender) {
        LOGGER.debug("Duel between {} (AP {} | HP {}) and {} (AP {} | HP {}) begins!", attacker, attacker.getAttack(), attacker.getHealth(), defender, defender.getAttack(), defender.getHealth());

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

        LOGGER.debug("ArmyBattle has begun!");
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
            LOGGER.debug("Alive soldiers: AttackerSide = {} | DefenderSide = {}\n", attackers.getAliveSoldiers(), defenders.getAliveSoldiers());
        }
        LOGGER.debug("ArmyBattle has ended!");
        return it1.hasNext();
    }
}
