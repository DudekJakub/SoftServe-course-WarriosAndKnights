package org.study.warriors.model;

import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.Unit;

import java.util.*;
import java.util.function.Supplier;

public class Army {
    private final List<IWarrior> soldiers = new ArrayList<>();

    public Iterator<IWarrior> firstAlive() {
        return new FirstAliveIterator();
    }

    /** Use of generic class(?) for invoking to type of unit we want to create and add to army. */
    public void addUnits(Class<? extends IWarrior> clazz, int quantity) {
        try {
            var constructor = clazz.getDeclaredConstructor(); //we pull class constructor
            for (int i = 0; i < quantity; i++) {
                var o = constructor.newInstance(); //we create new object
                soldiers.add(o);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }


    /** Use of Factory Pattern. UnitType has been placed in Unit-interface as well as 'createUnit' method */
    public void addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            soldiers.add((IWarrior) Unit.newUnit(type));
        }
    }

    /** Use of Functional Interface - Supplier. Its method 'get' generates new Warrior object. */
    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            soldiers.add(factory.get());
        }
        return this;
    }

    /** Use of shallowClone. */
    public void addUnits(Warrior prototype, int quantity) {
        for (int i = 0; i < quantity; i++) {
            soldiers.add(prototype.shallowClone());
        }
    }

    public int getArmySize() {
        return soldiers.size();
    }

    public List<IWarrior> getSoldiers() {
        return soldiers;
    }

    private class FirstAliveIterator implements Iterator<IWarrior> {
        int cursor = 0;

        /** Example explanation : if cursor = 0 then 1st condition is satisfied, if first solider is alive 2nd condition is NOT satisfied so
         *                        we exit while loop and check & return whether cursor is still lower then soldiers size */
        @Override
        public boolean hasNext() {
            while (cursor < soldiers.size() && !soldiers.get(cursor).isAlive()) {
                cursor++;
            }
            return cursor < soldiers.size();
        }

        @Override
        public IWarrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more soldiers in the army");
            }
            return soldiers.get(cursor);
        }
    }
}
