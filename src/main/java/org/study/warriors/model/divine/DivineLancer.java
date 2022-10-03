package org.study.warriors.model.divine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Lancer;
import org.study.warriors.model.interfaces.IWarrior;

public class DivineLancer extends DivineWarrior {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivineLancer.class);

    public DivineLancer(IWarrior decorated) {
        super(decorated);
        if (!(decorated instanceof Lancer)) throw new IllegalStateException("Given object to decorate is not Lancer!");
    }

    @Override
    public void hit(IWarrior target) {
        super.hit(target);
        castSupportSpell((DivineSoldier) getNextInChain());
    }
}
