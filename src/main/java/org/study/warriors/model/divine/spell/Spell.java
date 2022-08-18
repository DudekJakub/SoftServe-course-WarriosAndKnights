package org.study.warriors.model.divine.spell;

import org.study.warriors.model.divine.DivineSoldier;
import org.study.warriors.model.divine.InvocationLimiter;

public interface Spell extends InvocationLimiter {
    void apply(DivineSoldier ally);
}
