package org.study.warriors.model.divine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Defender;
import org.study.warriors.model.Healer;
import org.study.warriors.model.interfaces.CanDefense;
import org.study.warriors.model.interfaces.IWarrior;

public class DivineDefender extends DivineWarrior implements CanDefense {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivineDefender.class);

    public DivineDefender(IWarrior decorated) {
        super(decorated);
        if (!(decorated instanceof Defender)) throw new IllegalStateException("Given object to decorate is not Defender!");
    }


    @Override
    public int getDefense() {
        return ((Defender) decorated).getDefense();
    }

    @Override
    public void setDefense(int defense) {
        ((Defender) decorated).setDefense(defense);
    }


}
