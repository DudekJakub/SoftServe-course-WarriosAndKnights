package org.study.warriors.model;

public class RequestHealerCureAlly extends Request {

    private final int healValue;

    public RequestHealerCureAlly() {
        super(10);
        this.healValue = 2;
    }

    public RequestHealerCureAlly(int healValue, int maxCallValue) {
        super(maxCallValue);
        this.healValue = healValue;
    }

    public int getHealValue() {
        return healValue;
    }

    @Override
    public boolean isNotFullyHandled() {
        return super.isNotFullyHandled();
    }

    @Override
    protected void compareHandlersSizeToMaxCallValue() {
        super.compareHandlersSizeToMaxCallValue();
    }
}
