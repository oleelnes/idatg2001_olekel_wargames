package no.ntnu.olekel.core.units;

import no.ntnu.olekel.core.EnumHandler;

/**
 * The type Ranged unit.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class RangedUnit extends Unit {
  private int timesResisted;


  /**
   * Instantiates a new Ranged unit.
   *
   * @param name   the name
   * @param health the health
   * @param attack the attack
   * @param armor  the armor
   */
  public RangedUnit(String name, int health, int attack, int armor){
    super(name, health, attack, armor);
    timesResisted = 0;
    this.attackManipulator = 0;
    this.resistManipulator = 0;
  }

  /**
   * Instantiates a new Ranged unit.
   *
   * @param name   the name
   * @param health the health
   */
  public RangedUnit(String name, int health){
    super(name, health, 15, 8);
    timesResisted = 0;
    this.attackManipulator = 0;
    this.resistManipulator = 0;
  }

  /**
   * Returns the attack bonus for the unit.
   *
   * @return  The attack bonus.
   */
  @Override
  public int getAttackBonus() {
    return 3 + attackManipulator;
  }

  /**
   * Returns the resist bonus for the unit.
   *
   * @return  The resist bonus.
   */
  @Override
  public int getResistBonus() {
    if (timesResisted == 0) {
      timesResisted++;
      return 6+ resistManipulator;
    } else if (timesResisted < 3) {
      timesResisted++;
      return 4+ resistManipulator;
    } else {
      timesResisted++;
      return 2 + resistManipulator;
    }
  }

  /**
   * Method that sets the attackManipulator and resistManipulator
   * for the unit depending on what terrain is set.
   *
   * @param terrain The terrain.
   */
  @Override
  public void setTerrain(EnumHandler.TerrainTypes terrain){
    switch (terrain) {
      case FOREST -> attackManipulator = 1;
      case HILLS -> attackManipulator = 2;
      default -> {
        attackManipulator = 0;
        resistManipulator = 0;
      }
    }
  }

  @Override
  public String getType() {
    return "Ranged Unit";
  }

}
