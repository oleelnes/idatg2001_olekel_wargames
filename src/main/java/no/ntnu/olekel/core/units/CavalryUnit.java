package no.ntnu.olekel.core.units;

import no.ntnu.olekel.core.EnumHandler;

/**
 * The class for the cavalry unit, a subclass of Unit, and defines the
 * abstract classes from Unit
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class CavalryUnit extends Unit {
  private int timesAttacking;
  private static final int CHARGE_BONUS = 4;
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
  public CavalryUnit(String name, int health, int attack, int armor){
    super(name, health, attack, armor);
    this.timesAttacking = 0;
    this.attackManipulator = 0;
    this.resistManipulator = 1;
  }

  /**
   * Simplified constructor for the CavalryUnit class.
   *
   * @param name    the cavalry unit's name.
   * @param health  the cavalry unit's health.
   */
  public CavalryUnit(String name, int health){
    super(name, health, 20, 12);
    this.timesAttacking = 0;
    this.attackManipulator = 0;
    this.resistManipulator = 1;
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
      return CHARGE_BONUS + MELEE_BONUS + attackManipulator;
    } else {
      timesAttacking++;
      return MELEE_BONUS + attackManipulator;
    }
  }

  /**
   * Method that returns cavalry unit's resist bonus.
   *
   * @return  resist bonus.
   */
  @Override
  public int getResistBonus() {
    return RESIST_BONUS * resistManipulator;
  }

  @Override
  public void setTerrain(EnumHandler.TerrainTypes terrain){
    switch (terrain) {
      case PLAINS -> attackManipulator = 2;
      case FOREST -> {
        resistManipulator = 0;
        attackManipulator = 0;
      }
      default -> {
        resistManipulator = 1;
        attackManipulator = 0;
      }
    }
  }

  @Override
  public String getType() {
    return "Cavalry Unit";
  }
}
