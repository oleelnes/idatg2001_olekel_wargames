package no.ntnu.olekel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;


/**
 * This class represents the functionality of an army.
 *
 * @author  Ole Kristian Elnæs
 * @version 16.02.2022
 */
public class Army {
    private String name;
    private List<Unit> units;
    private final Random random;
    private final Logger logger;

    /**
     * Constructor.
     *
     * @param name  The name of the army.
     */
    Army(String name){
        this.name = name;
        this.random = new Random();
        this.logger = Logger.getLogger(this.getClass().toString());
    }

    /**
     * Constructor.
     *
     * @param name  The name of the army
     * @param units An arraylist of subclasses of the superclass unit
     */
    Army(String name, List<Unit> units) {
        this.name = name;
        this.units = units;
        this.random = new Random();
        this.logger = Logger.getLogger(this.getClass().toString());
    }

    /**
     * Method that returns the name of the army.
     *
     * @return  the name of the army.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds one unit (instance of class Unit) to the list units.
     *
     * @param unit  An instance of the class Unit.
     */
    public void add(Unit unit) {
        units.add(unit);
    }

    /**
     * Adds instances of Unit to the list units.
     *
     * @param units A list of one or more units, which are instances of the class Unit.
     */
    public void addAll(List<Unit> units) {
        this.units.addAll(units);
    }

    /**
     *
     * todo: mulig exception?
     *
     * @param unit  An instance of the object Unit.
     */
    public void remove(Unit unit) {
        units.remove(unit);
    }

    /**
     * Method that returns a boolean expression. Returns whether the list units has any units or not.
     *
     * @return @true if the list units has units, @false if it doesn't.
     */
    public boolean hasUnits() {
        try {
            return !units.isEmpty();
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Methods that returns all the units in this instance of the class Army.
     *
     * @return  A list of units (instances of Unit)
     */
    public List<Unit> getAllUnits() {
        return units;
    }

    /**
     *
     * todo: try-catch/throw exception?
     *
     * @return  A random unit in the list units.
     */
    public Unit getRandom() {
        return units.get(random.nextInt(units.size()));
    }

    @Override
    public String toString() {
        return "Army{" +
                "name='" + name + '\'' +
                ", units=" + units +
                ", random=" + random +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) &&
                Objects.equals(units, army.units) &&
                Objects.equals(random, army.random) &&
                Objects.equals(logger, army.logger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units, random, logger);
    }
}
