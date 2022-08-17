package org.study.warriors.model.observer;

import org.study.warriors.model.interfaces.CanWieldWeapon;
import org.study.warriors.model.interfaces.IWarrior;

public interface Observer {
    void update(IWarrior warrior);
}
