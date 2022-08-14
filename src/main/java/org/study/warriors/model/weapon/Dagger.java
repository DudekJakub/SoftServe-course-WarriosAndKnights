package org.study.warriors.model.weapon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.CanWieldWeapon;
import org.study.warriors.model.observer.Observer;

public class Dagger extends Weapon implements Observer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dagger.class);

    public Dagger() {
        super("DAGGER", false, false,0, 3, 0, 0, 0);
    }

    @Override
    public void update(CanWieldWeapon warrior) {
        if (warrior.getHealth() < 15 && !modifierApplied) {
            warrior.updateParameterFromSingleWeapon(this);
            LOGGER.trace("{}'s modifiers have been triggered! {}'s attack increased by {} | NEW AP : {}", this, warrior, this.attackModifier, warrior.getAttack());
        }
    }
}
