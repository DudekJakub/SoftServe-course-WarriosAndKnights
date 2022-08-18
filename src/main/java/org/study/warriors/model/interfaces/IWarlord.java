package org.study.warriors.model.interfaces;

import org.study.warriors.model.interfaces.IWarrior;

import java.util.List;

public interface IWarlord extends IWarrior {

    List<IWarrior> sortSoldiers(Iterable<IWarrior> warlordSoldiers);

    default void placeGivenTypeOfSoldiersInGivenPositionOfArmy(List<IWarrior> givenArmy, Class<?> clazz, int position) {
        var temporaryListOfGivenTypeSoldiers = givenArmy.stream().filter(clazz::isInstance).toList();
        givenArmy.removeIf(clazz::isInstance);
        givenArmy.addAll(position, temporaryListOfGivenTypeSoldiers);
        LOGGER.trace("Warlord has sorted soldiers of type: {}", clazz.getSimpleName());
        placeDeadSoldiersAtTailOfArmy(givenArmy);
    }

    default void placeDeadSoldiersAtTailOfArmy(List<IWarrior> givenArmy) {
        var temporaryListOfDeadSoldiers = givenArmy.stream().filter(warrior -> !warrior.isAlive()).toList();
        givenArmy.removeAll(temporaryListOfDeadSoldiers);
        givenArmy.addAll(givenArmy.size(), temporaryListOfDeadSoldiers);
    }
}
