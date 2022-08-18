package org.study.warriors.model.divine.buff;

import org.study.warriors.model.divine.DivineSoldier;

import java.util.Objects;

public abstract class AbstractBuff implements Buff {

    protected final DivineSoldier holder;
    protected final int invocationLimit = 0;
    protected int invocationCounter;
    protected boolean isReadyToUse;

    public AbstractBuff(DivineSoldier holder) {
        this.holder = holder;
        this.isReadyToUse = true;
        this.invocationCounter = 0;
    }

    @Override
    public int getInvocationLimit() {
        return invocationLimit;
    }

    @Override
    public int getInvocationCounter() {
        return invocationCounter;
    }

    @Override
    public boolean isReadyToUse() {
        return isReadyToUse;
    }

    @Override
    public void setReadyToUse(boolean isReadyToUse) {
        this.isReadyToUse = isReadyToUse;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBuff that = (AbstractBuff) o;
        return Objects.equals(holder, that.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holder);
    }
}
