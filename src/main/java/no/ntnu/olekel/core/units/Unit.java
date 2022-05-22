package no.ntnu.olekel.core.units;

import no.ntnu.olekel.core.EnumHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The abstract superclass Unit.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION} todo: is this good practice?
 * @author Ole Kristian Elnæs
 */
public abstract class Unit {
  private String name;
  private int health;
  private int initialHealth;
  private int attack;
  private int armor;
  protected int resistManipulator;
  protected int attackManipulator;
  private static final Logger logger = Logger.getLogger(Unit.class.toString());


  /**
   * Construct new Unit.
   *
   * @param name    The name of the unit.
   * @param health  The health of the unit.
   * @param attack  The attack strength(?) of the unit.
   * @param armor   A number that represents the value of the unit's armor.
   */
  public Unit(String name, int health, int attack, int armor) {
    this.name = name;
    try {
      setHealth(health);
      this.initialHealth = health;
    } catch (IllegalArgumentException e) {
      setHealth(10);
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
    try {
      opponent.setHealth(opponent.getHealth() - (this.getAttack() + this.getAttackBonus())
              + (opponent.getArmor() + opponent.getResistBonus()));
    } catch (IllegalArgumentException e) {
      opponent.setHealth(0);
    }
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
   * Returns a float that represents the percentage of health left compared
   * to the initial value for health.
   *
   * @return  The float representing the percentage of health left.
   */
  public Integer getPercentHealth(){
    return (health / initialHealth) * 100;
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


  public int getInitialHealth() {
    return initialHealth;
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
      throw new IllegalArgumentException("Illegal health: (health should not be less than 0)");
    }
  }

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

  public abstract void setTerrain(EnumHandler.TerrainTypes terrain);

  public abstract String getType();

}
