package org.study.warriors.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.study.warriors.model.interfaces.IWarlord;
import org.study.warriors.model.interfaces.IWarrior;
import org.study.warriors.model.interfaces.Unit;
import org.study.warriors.model.observer.Observer;

import java.util.*;
import java.util.function.Supplier;

    /** By returning Army in each addUnit type of method we are able to use so-called fluent interface */

@Getter
public class Army implements Observer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Army.class);

    protected final List<IWarrior> soldiers = new LinkedList<>();
    protected IWarlord armyWarlord;

    public Iterator<IWarrior> firstAlive() {
        return new FirstAliveIterator();
    }

    public Army addSingleUnit(IWarrior warrior) {
        soldiers.add(warrior);
        return this;
    }

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

    public void addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            IWarrior warrior = (IWarrior) Unit.newUnit(type);
            soldiers.add(warrior);
        }
    }

    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        if (factory.get() instanceof IWarlord warlord && armyWarlord == null) {
            armyWarlord = warlord;
            soldiers.add(warlord);
            LOGGER.trace("Warlord added to army!");
        }
        if (!(factory.get() instanceof IWarlord)) {
            for (int i = 0; i < quantity; i++) {
                var warrior = factory.get();
                soldiers.add(warrior);
                warrior.registerObserver(this);
            }
        }
        return this;
    }

    public Army addUnits(Warrior prototype, int quantity) {
        for (int i = 0; i < quantity; i++) {
            IWarrior warrior = prototype.clone();
            soldiers.add(warrior);
        }
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
        return !soldiers.isEmpty();
    }

    public IWarrior unitAtPosition(int position) {
        return soldiers.get(position);
    }

    public Map<IWarrior, Integer> getSoldiersAndTheirHp() {
        Map<IWarrior, Integer> result = new LinkedHashMap<>();
        soldiers.forEach(soldier -> result.put(soldier, soldier.getHealth()));
        return result;
    }

    public void removeDeadSoldiersFromArmy() {
        soldiers.removeIf(soldier -> !soldier.isAlive());
    }

    public void lineUp() {
        for (int i = 0; i < soldiers.size(); i++) {
            var soldier = soldiers.get(i);
            if (i < soldiers.size() - 1) {
                var nextSoldier = soldiers.get(i + 1);
                soldier.setNextInChain(nextSoldier);
            } else if (i > 0) {
                var previousSoldier = soldiers.get(i - 1);
                soldier.setPreviousInChain(previousSoldier);
            }
        }
    }

    public void moveUnits() {
        if (armyWarlord != null && armyWarlord.isAlive()) {
            var sortedSoldiers = armyWarlord.sortSoldiers(soldiers);
            soldiers.clear();
            soldiers.addAll(sortedSoldiers);
            lineUp();
        } else {
            LOGGER.trace("There is no Warlord in the army or he is dead!");
        }
    }

    public static void moveUnitsForArmies(Army... armies) {
        for (var army : armies) {
            army.moveUnits();
        }
    }

    @Override
    public void update(IWarrior warrior) {
        if (!warrior.isAlive()) {
            moveUnits();
        }
    }

        private class FirstAliveIterator implements Iterator<IWarrior> {
            int cursor = 0;

            /**
             * Example explanation : if cursor = 0 then 1st condition is satisfied, if first solider is alive 2nd condition is NOT satisfied so
             * we exit while loop and check & return whether cursor is still lower then soldiers size
             */
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
