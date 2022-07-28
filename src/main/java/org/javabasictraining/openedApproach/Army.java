package org.javabasictraining.openedApproach;

import java.util.ArrayList;
import java.util.Collection;

public class Army {
    private Collection<Unit> soldiers = new ArrayList<>();

    public Army() {}

    public Army(Collection<Unit> soldiers) {
        this.soldiers = soldiers;
    }

    public Collection<Unit> getSoldiers() {
        return soldiers;
    }

    public Collection<Unit> addUnits(Unit unit, int quantity) {
        for (int i = 0; i < quantity; i++) {
            unit.joinArmy(this);
        }
        return soldiers;
    }
}
