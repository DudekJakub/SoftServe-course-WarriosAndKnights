package org.study.warriors.model.divine.buff;

import org.study.warriors.model.damage.IDamage;
import org.study.warriors.model.divine.InvocationLimiter;

public interface Buff extends InvocationLimiter {
    void apply(IDamage damageType);
}
