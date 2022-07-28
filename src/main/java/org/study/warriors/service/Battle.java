package org.study.warriors.service;

import org.study.warriors.model.Army;
import org.study.warriors.model.Warrior;

public class Battle {

    private Battle() {}

    public static boolean fight(Warrior attacker, Warrior defender) {
        while (attacker.isAlive() && defender.isAlive()) {
            attacker.hit(defender);
            if (!defender.isAlive()) {
                return defender.isAlive();
            } else {
                defender.hit(attacker);
            }
        }
        return defender.isAlive();
    }

    public static void fight(Army attackers, Army defenders) {
        while (!attackers.isArmyEmpty() && !defenders.isArmyEmpty()) {
            var currentAttacker = attackers.getUnit();
            var currentDefender = defenders.getUnit();

            if (fight(currentAttacker, currentDefender)) {
                attackers.removeDeadSolider();
            } else {
                defenders.removeDeadSolider();
            }
        }
    }
}
