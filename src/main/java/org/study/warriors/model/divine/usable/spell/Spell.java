package org.study.warriors.model.divine.usable.spell;

import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.usable.IUsable;

public interface Spell extends IUsable {
    void apply(DivineSoldier target);
}
