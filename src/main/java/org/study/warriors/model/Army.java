package org.study.warriors.model;

import org.study.warriors.model.decorator.RequestWarriorDecorator;
import org.study.warriors.model.interfaces.HasHealth;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.Unit;

import java.util.*;
import java.util.function.Supplier;

    /** By returning Army in each addUnit type of method we are able to use so-called fluent interface */

public class Army {
    private final List<IWarrior> soldiers = new LinkedList<>();

    public Iterator<IWarrior> firstAlive() {
        return new FirstAliveIterator();
    }

    public Army addSingleUnit(IWarrior warrior) {
        soldiers.add(warrior);
        lineUp(this);
        return this;
    }

    /** Use of generic class(?) for invoking to type of unit we want to create and add to army. */
    public Army addUnits(Class<? extends IWarrior> clazz, int quantity) {
        try {
            var constructor = clazz.getDeclaredConstructor();
            for (int i = 0; i < quantity; i++) {
                var o = constructor.newInstance();
                soldiers.add(o);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /** Use of Factory Pattern. UnitType has been placed in Unit-interface as well as 'createUnit' method */
    public void addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            IWarrior warrior = (IWarrior) Unit.newUnit(type);
            soldiers.add(warrior);
        }
    }

    /** Use of Functional Interface - Supplier. Its method 'get' generates new Warrior object. */
    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            var warrior = factory.get();
            soldiers.add(warrior);
        }
        return this;
    }

    /** Use of shallowClone. */
    public Army addUnits(Warrior prototype, int quantity) {
        for (int i = 0; i < quantity; i++) {
            IWarrior warrior = prototype.clone();
            soldiers.add(warrior);
        }
        return this;
    }

    /** Use of deepClone for Warrior various decorators */
    public Army addUnits(RequestWarriorDecorator prototype, int quantity) {
        for (int i = 0; i < quantity; i++) {
            soldiers.add(prototype.clone());
        }
        lineUp(this);
        return this;
    }

    public int getArmySize() {
        return soldiers.size();
    }

    public long getAliveSoldiers() {
        return soldiers.stream()
                       .filter(IWarrior::isAlive)
                       .count();
    }

    public boolean isAlive() {
        return soldiers.stream()
                       .anyMatch(soldier -> isAlive());
    }

    public IWarrior getSoldierFromGivenPosition(int position) {
        return soldiers.get(position);
    }

    public IWarrior getFirstAliveSoldier(Army army) {
        return army.soldiers.stream().filter(HasHealth::isAlive).findFirst().get();
    }

    public Map<IWarrior, Integer> getSoldiersAndTheirHp() {
        Map<IWarrior, Integer> result = new LinkedHashMap<>();
        soldiers.forEach(soldier -> result.put(soldier, soldier.getHealth()));
        return result;
    }

    public void removeDeadSoldiersFromArmy() {
        soldiers.forEach(soldier -> {
            if (!soldier.isAlive()) soldiers.remove(soldier);
        });
    }

    private void lineUp(Army army) {
        var soldiers = army.soldiers;

        for (int i = 0; i < army.soldiers.size(); i++) {
            if (soldiers.get(i) instanceof RequestWarriorDecorator soldier) {
                if (i < army.soldiers.size() - 1 && soldiers.get(i + 1) instanceof RequestWarriorDecorator nextSoldier) {
                    soldier.setNextInChain(nextSoldier);
                } else if (i > 0 && soldiers.get(i - 1) instanceof RequestWarriorDecorator previousSoldier) {
                    soldier.setPreviousInChain(previousSoldier);
                }
            }
        }
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
