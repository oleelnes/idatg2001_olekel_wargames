package no.ntnu.olekel.core;

import javafx.beans.property.*;
import no.ntnu.olekel.core.units.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * This class represents the functionality of an army.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class Army {
  private String name;
  private List<Unit> units;
  private final Random random;
  private final Logger logger;
  private final IntegerProperty size = new SimpleIntegerProperty();
  private final IntegerProperty commanderSize = new SimpleIntegerProperty();
  private final IntegerProperty cavalrySize = new SimpleIntegerProperty();
  private final IntegerProperty infantrySize = new SimpleIntegerProperty();
  private final IntegerProperty rangedSize = new SimpleIntegerProperty();
  private final StringProperty armyHealthPercentage = new SimpleStringProperty();



  /**
   * Simplified constructor.
   *
   * @param name The name of the army.
   */
  public Army(String name) {
    this.name = name;
    this.random = new Random();
    this.logger = Logger.getLogger(this.getClass().toString());
    this.units = new ArrayList<>();
    setSize();
  }

  /**
   * Constructor.
   *
   * @param name  The name of the army
   * @param units An arraylist of subclasses of the superclass unit
   */
  public Army(String name, List<Unit> units) {
    this.name = name;
    this.units = units;
    this.random = new Random();
    this.logger = Logger.getLogger(this.getClass().toString());
    setSize();
  }

  /**
   * Method that returns the name of the army.
   *
   * @return the name of the army.
   */
  public String getName() {
    return name;
  }

  /**
   * Adds one unit (instance of class Unit) to the list units.
   *
   * @param unit An instance of the class Unit.
   */
  public void add(Unit unit) {
    units.add(unit);
    setSize();
  }

  /**
   * Adds instances of Unit to the list units.
   *
   * @param units A list of one or more units, which are instances of the class Unit.
   */
  public void addAll(List<Unit> units) {
    this.units.addAll(units);
    setSize();
  }

  /**
   * Removes a unit from the List units
   *
   * @param unit An instance of the object Unit.
   */
  public void remove(Unit unit) {
    units.remove(unit);
    setSize();
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
   * @return A list of units (instances of Unit)
   */
  public List<Unit> getAllUnits() {
    return units;
  }

  /**
   * Method that returns a list sorted with only infantry units.
   *
   * @return  a List<Unit> consisting of instances of the units list that are
   * instances of InfantryUnit
   */
  public List<Unit> getInfantryUnits() {
    return units.stream().filter(InfantryUnit.class::isInstance).collect(Collectors.toList());
  }

  /**
   * Method that returns a list sorted with only cavalry units.
   *
   * @return  a List<Unit> consisting of instances of the units list that are
   * instances of CavalryUnit
   */
  public List<Unit> getCavalryUnits(){
    return units.stream().filter(CavalryUnit.class::isInstance)
        .filter(u -> u.getClass() != CommanderUnit.class).collect(Collectors.toList());
  }


  /**
   * Method that returns a list sorted with only ranged units.
   *
   * @return  a List<Unit> consisting of instances of the units list that are
   * instances of RangedUnit
   */
  public List<Unit> getRangedUnits(){
    return units.stream().filter(RangedUnit.class::isInstance).collect(Collectors.toList());
  }
  
  /**
   * Method that returns a list sorted with only commander units.
   *
   * @return  a List<Unit> consisting of instances of the units list that are
   * instances of CommanderUnit
   */
  public List<Unit> getCommanderUnits(){
    return units.stream().filter(CommanderUnit.class::isInstance).collect(Collectors.toList());
  }

  /**
   * This method returns a random unit from the class' unit list.
   *
   * @return A random unit in the list units.
   */
  public Unit getRandom() {
    try {
      return units.get(random.nextInt(units.size()));
    } catch (NullPointerException e) {
      logger.log(Level.WARNING, () -> "No units in this army. Error: " + e.getMessage());
      return null;
    }
  }

  /**
   * Method that returns the overall health status for the army as a percentage.
   *
   * @return  A float that represents the army's health as a percentage.
   */
  public Integer getPercentHealthArmy(){
    if (!units.isEmpty())
      return units.stream().map(Unit::getPercentHealth)
          .reduce(0, Integer::sum) / units.size();
    else return 0;
  }


  public final Integer getSize(){
    return this.size.get();
  }

  public int getCommanderSize() {
    return commanderSize.get();
  }

  public int getCavalrySize() {
    return cavalrySize.get();
  }

  public int getInfantrySize() {
    return infantrySize.get();
  }

  public int getRangedSize() {
    return rangedSize.get();
  }

  public String getArmyHealthPercentage() {
    return armyHealthPercentage.get();
  }

  public final void updateHealth(){
    this.armyHealthPercentage.set(getPercentHealthArmy() + "%");
  }

  public final void setSize() {
    if (!units.isEmpty()) this.size.set(units.size());
    else this.size.set(0);
    if (!getCommanderUnits().isEmpty()) this.commanderSize.set(getCommanderUnits().size());
    else this.commanderSize.set(0);
    if (!getCavalryUnits().isEmpty()) this.cavalrySize.set(getCavalryUnits().size());
    else this.cavalrySize.set(0);
    if (!getInfantryUnits().isEmpty()) this.infantrySize.set(getInfantryUnits().size());
    else this.infantrySize.set(0);
    if (!getRangedUnits().isEmpty()) this.rangedSize.set(getRangedUnits().size());
    else this.rangedSize.set(0);
  }

  public void setName(String name) {
    this.name = name;
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
