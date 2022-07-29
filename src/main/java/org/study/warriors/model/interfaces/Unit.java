package org.study.warriors.model.interfaces;

import org.study.warriors.model.Defender;
import org.study.warriors.model.Knight;
import org.study.warriors.model.Warrior;

public interface Unit {

    enum UnitType {
        WARRIOR, KNIGHT, DEFENDER
    }

    static Unit newUnit(UnitType type) {
        return switch (type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Knight();
            case DEFENDER -> new Defender();
        };
    }
}
