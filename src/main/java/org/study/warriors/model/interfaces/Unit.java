package org.study.warriors.model.interfaces;

import org.study.warriors.model.*;

public interface Unit {

    enum UnitType {
        WARRIOR, KNIGHT, DEFENDER, VAMPIRE, LANCER, HEALER
    }

    static Unit newUnit(UnitType type) {
        return switch (type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Knight();
            case DEFENDER -> new Defender();
            case VAMPIRE -> new Vampire();
            case LANCER -> new Lancer();
            case HEALER -> new Healer();
        };
    }
}
