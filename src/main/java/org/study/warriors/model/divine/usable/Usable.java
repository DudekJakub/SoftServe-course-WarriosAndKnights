package org.study.warriors.model.divine.usable;

import org.study.warriors.model.divine.DivineSoldier;

public abstract class Usable implements IUsable {

    protected DivineSoldier holder;
    protected int invocationLimit;
    protected int invocationCounter;
    protected boolean isReadyToUse;

    protected Usable(DivineSoldier holder) {
        this.holder = holder;
        this.isReadyToUse = true;
    }

    @Override
    public boolean isReadyToUse() {
        return isReadyToUse;
    }

    @Override
    public void setHolder(DivineSoldier holder) {
        this.holder = holder;
    }

    @Override
    public void setReadyToUse(boolean isReadyToUse) {
        this.isReadyToUse = isReadyToUse;
    }

    protected void checkActiveStatus() {
        if (invocationCounter == invocationLimit) {
            setReadyToUse(false);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
