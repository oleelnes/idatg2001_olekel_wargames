package no.ntnu.olekel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The abstract superclass Unit Todo: more here.
 *
 * @version 2022.02.08
 * @author Ole Kristian Elnæs
 */
public abstract class Unit {
  private String name;
  private int health;
  private int attack;
  private int armor;
  private final Logger logger;

  /**
   * Construct new Unit.
   *
   * @param name    The name of the unit.
   * @param health  The health of the unit.
   * @param attack  The attack strength(?) of the unit.
   * @param armor   A number that represents the value of the unit's armor.
   */
  Unit(String name, int health, int attack, int armor) {
    this.name = name;
    this.logger = Logger.getLogger(this.getClass().toString());
    //this.health = health;
    try { //todo: flytt try-catch til ui
      setHealth(health);
    } catch (IllegalArgumentException e) {
      logger.log(Level.SEVERE, "caught by setHealth in constructor: {0} ",  e.getMessage());
    }
    this.attack = attack;
    this.armor = armor;
  }

  /**
   * Method whose content is an equation that calculates the damage
   * value of an attack.
   *
   * @param opponent  The Unit object opponent.
   */
  public void attack(Unit opponent) {
    opponent.setHealth(opponent.getHealth() - (this.getAttack() + this.getAttackBonus())
            + (opponent.getArmor() + opponent.getResistBonus()));
  }

  /**
   * Returns the name.
   *
   * @return the String name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the health.
   *
   * @return the health.
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Returns attack.
   *
   * @return the attack.
   */
  public int getAttack() {
    return this.attack;
  }

  /**
   * Returns the armor.
   *
   * @return  armor.
   */
  public int getArmor() {
    return this.armor;
  }

  /**
   * Sets the health of the unit. Checks whether the value sent to it is within bounds, if not,
   * an exception is thrown.
   *
   * @param health  The unit's health represented as an int.
   */
  public void setHealth(int health) {
    if (health >= 0){
      this.health = health;
    } else {
      throw new IllegalArgumentException("illegal health: (health should not be less than 0)");
    }
  }

  //TODO: correct return value on this method
  /**
   * Returns toString.
   *
   * @return  toString.
   */
  @Override
  public String toString() {

    return ("Unit: " + getName() +
            ", health: " + getHealth() +
            ", attack: " + getAttack() +
            ", armor: " + getArmor());
  }

  protected abstract int getAttackBonus();

  protected abstract int getResistBonus();

 }
