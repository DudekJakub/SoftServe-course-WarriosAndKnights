package org.study.warriors.model.observer;

import org.study.warriors.model.interfaces.CanWieldWeapon;

public interface Observer {
    void update(CanWieldWeapon warrior);
}
