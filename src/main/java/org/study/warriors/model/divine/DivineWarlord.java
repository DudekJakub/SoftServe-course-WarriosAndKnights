package org.study.warriors.model.divine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.Warlord;
import org.study.warriors.model.interfaces.CanDefense;
import org.study.warriors.model.interfaces.IWarlord;
import org.study.warriors.model.interfaces.IWarrior;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DivineWarlord extends DivineWarrior implements CanDefense, IWarlord {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivineWarlord.class);

    public DivineWarlord(IWarrior decorated) {
        super(decorated);
        if (!(decorated instanceof Warlord)) throw new IllegalStateException("Given object to decorate is not Warlord!");
    }

    @Override
    public int getDefense() {
        return ((Warlord) decorated).getDefense();
    }

    @Override
    public void setDefense(int defense) {
        ((Warlord) decorated).setDefense(defense);
    }

    @Override
    public List<IWarrior> sortSoldiers(Iterable<IWarrior> warlordSoldiers) {
        if (warlordSoldiers == null) {
            throw new NoSuchElementException("Warlord has no soldiers to rearrange their positions!");
        }
        LOGGER.debug("Warlord is sorting soldiers...");

        var sortedSoldiers = new ArrayList<IWarrior>();
        warlordSoldiers.forEach(sortedSoldiers::add);

        var sortedSoldiersSize = sortedSoldiers.size();

        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, DivineWarlord.class, sortedSoldiersSize - 1);
        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, DivineLancer.class, 0);
        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, DivineHealer.class, 1);
        placeGivenTypeOfSoldiersInGivenPositionOfArmy(sortedSoldiers, DivineWarlord.class, sortedSoldiersSize - 1);

        LOGGER.trace("Warlord has moved dead soldiers behind him");
        LOGGER.debug("Warlord has sorted all of his soldiers!");

        return sortedSoldiers;
    }
}
