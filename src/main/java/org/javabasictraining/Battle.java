package org.javabasictraining;

public class Battle {

    private Battle() {}

    public static boolean fight(Warrior attacker, Warrior defender) {
        while (attacker.isAlive() && defender.isAlive()) {
            attacker.hit(defender);
            if (!defender.isAlive()) {
                return false;
            } else {
                defender.hit(attacker);
            }
        }
        return true;
    }
}
