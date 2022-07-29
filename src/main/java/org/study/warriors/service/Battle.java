package org.study.warriors.service;

import org.study.warriors.model.Army;
import org.study.warriors.model.interfaces.IWarrior;

public class Battle {

    private Battle() {
        throw new IllegalStateException("Utility class! Do not invoke this constructor!");
    }

    public static boolean fight(IWarrior attacker, IWarrior defender) {
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

    /** We assigned two variables which reference to our Iterator method. This method gives us firstAlive unit.
     *  Then in while loop we check if both armies still have next alive unit.
     *  While body contains fight method & return statement == info if there is somebody still alive from army 1st.*/
    public static boolean fight(Army attackers, Army defenders) {
        var it1 = attackers.firstAlive();
        var it2 = defenders.firstAlive();

        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it2.hasNext();
    }
}
