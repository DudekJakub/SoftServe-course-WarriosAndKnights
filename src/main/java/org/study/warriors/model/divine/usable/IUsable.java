package org.study.warriors.model.divine.usable;

import org.study.warriors.model.divine.DivineSoldier;

public interface IUsable extends InvocationLimiter {
    void setHolder(DivineSoldier holder);
}
