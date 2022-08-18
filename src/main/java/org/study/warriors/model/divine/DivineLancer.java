package org.study.warriors.model.divine;

import org.study.warriors.model.Lancer;
import org.study.warriors.model.divine.spell.PierceResistSpell;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.request.IRequest;

public class DivineLancer extends DivineWarrior {

    public DivineLancer(IWarrior decorated) {
        super(decorated);
        if (!(decorated instanceof Lancer)) throw new IllegalStateException("Given object to decorate is not Lancer!");
        this.getSpells().add(new PierceResistSpell(this));
    }

    @Override
    public void hit(IWarrior target) {
        super.hit(target);
        castSupportSpell((DivineSoldier) getNextInChain());
    }

    @Override
    public void processRequest(IRequest request) {
        decorated.processRequest(request);
    }
}
