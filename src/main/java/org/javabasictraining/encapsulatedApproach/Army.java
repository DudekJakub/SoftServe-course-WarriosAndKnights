package org.javabasictraining.encapsulatedApproach;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Supplier;

public class Army {
    private final Queue<Warrior> soldiers = new ArrayDeque<>();

    public Queue<Warrior> getSoldiers() {
        return soldiers;
    }

    public void addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            soldiers.add((Warrior) Unit.newUnit(type));
        }
    }

    public void addUnits(Warrior prototype, int quantity) {
        for (int i = 0; i < quantity; i++) {
            soldiers.add(prototype.shallowClone());
        }
    }

    public void addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            soldiers.add(factory.get());
        }
    }

    Warrior getUnit() {
        return this.getSoldiers().peek();
    }

    void removeDeadSolider() {
        this.getSoldiers().poll();
    }

    boolean isArmyEmpty() {
        return this.getSoldiers().isEmpty();
    }
}
