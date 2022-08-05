package org.study.warriors.model;

public class RequestLancerPierceAttack extends Request {

    private final int pierceDamage;

    public RequestLancerPierceAttack() {
        super(1);
        this.pierceDamage = 3;
    }

    public RequestLancerPierceAttack(int pierceDamage, int maxCallValue) {
        super(maxCallValue);
        this.pierceDamage = pierceDamage;
    }

    public int getPierceDamage() {
        compareHandlersSizeToMaxCallValue();
        return pierceDamage;
    }

}
