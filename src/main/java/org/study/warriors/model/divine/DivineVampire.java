package org.study.warriors.model.divine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Vampire;
import org.study.warriors.model.interfaces.CanDrainLife;
import org.study.warriors.model.interfaces.IWarrior;

public class DivineVampire extends DivineWarrior implements CanDrainLife {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivineVampire.class);

    public DivineVampire(IWarrior decorated) {
        super(decorated);
        if (!(decorated instanceof Vampire)) throw new IllegalArgumentException("Given object to decorate is not Vampire!");
        //HERE HERE HERE
    }

    @Override
    public void hit(IWarrior target) {
        divineModifier.modifyVampirism(this);
        super.hit(target);
        castSupportSpell((DivineSoldier) getNextInChain());
    }

    @Override
    public int getLastDealtDamage() {
        return ((Vampire) decorated).getLastDealtDamage();
    }

    @Override
    public int getVampirism() {
        return ((Vampire) decorated).getVampirism();
    }

    @Override
    public void setVampirism(int vampirism) {
        ((Vampire) decorated).setVampirism(vampirism);
    }

    @Override
    public void setLastDealtDamage(int lastDealtDamage) {
        ((Vampire) decorated).setLastDealtDamage(lastDealtDamage);
    }

    @Override
    public void drainLifeBasedOnDealtAttack(IWarrior target) {
        ((Vampire) decorated).drainLifeBasedOnDealtAttack(target);
    }
}
