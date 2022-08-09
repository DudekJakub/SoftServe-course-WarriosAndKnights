package org.study.warriors.model.damage;

public class SimpleDamage implements IDamage {
    private int hitPoints;

    public SimpleDamage(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
