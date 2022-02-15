package no.ntnu.olekel;

/**
 * The class for the cavalry unit, a subclass of Unit, and defines the
 * abstract classes from Unit
 *
 * @author Ole Kristian Elnæs
 * @version 15.02.2022
 */
public class CavalryUnit extends Unit {
  private int timesAttacking;
  private static final int CHARGE_BONUS = 4; //todo: figure out if having constants is the right thing here.
  private static final int MELEE_BONUS = 2;
  private static final int RESIST_BONUS = 1;

  /**
   * Full constructor for the CavalryUnit class.
   *
   * @param name    the cavalry unit's name.
   * @param health  the cavalry unit's health.
   * @param attack  the cavalry unit's attack value.
   * @param armor   the cavalry unit's armor value.
   */
  CavalryUnit(String name, int health, int attack, int armor){
    super(name, health, attack, armor);
    timesAttacking = 0;
  }

  /**
   * Simplified constructor for the CavalryUnit class.
   *
   * @param name    the cavalry unit's name.
   * @param health  the cavalry unit's health.
   */
  CavalryUnit(String name, int health){
    super(name, health, 20, 12);
    timesAttacking = 0;
  }

  /**
   * Method that returns the cavalry unit's attack bonus.
   *
   * @return  attack bonus.
   */
  @Override
  public int getAttackBonus() {
    if (timesAttacking == 0) {
      timesAttacking++;
      return CHARGE_BONUS + MELEE_BONUS;
    } else {
      timesAttacking++;
      return MELEE_BONUS;
    }
  }

  /**
   * Method that returns cavalry unit's resist bonus.
   *
   * @return  resist bonus.
   */
  @Override
  public int getResistBonus() {
    return RESIST_BONUS;
  }
}
