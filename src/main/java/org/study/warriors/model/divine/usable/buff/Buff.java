package org.study.warriors.model.divine.usable.buff;

import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.divine.usable.InvocationLimiter;

public interface Buff extends InvocationLimiter {
    void apply(IDamage damageType);
}
