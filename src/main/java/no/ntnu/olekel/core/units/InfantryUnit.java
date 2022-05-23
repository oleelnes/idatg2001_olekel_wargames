package no.ntnu.olekel.core.units;

import no.ntnu.olekel.core.EnumHandler;

/**
 * The class Infantry unit.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class InfantryUnit extends Unit{


  /**
   * Instantiates a new Infantry unit.
   *
   * @param name   the name
   * @param health the health
   * @param attack the attack
   * @param armor  the armor
   */
  public InfantryUnit(String name, int health, int attack, int armor) {
    super(name, health, attack, armor);
    attackManipulator = 0;
    resistManipulator = 0;
  }

  /**
   * Instantiates a new Infantry unit.
   *
   * @param name   the name
   * @param health the health
   */
  public InfantryUnit(String name, int health){
    super(name, health, 15, 10);
    attackManipulator = 0;
    resistManipulator = 0;

  }

  @Override
  public int getAttackBonus() {
    return 2 + attackManipulator;
  }

  @Override
  public int getResistBonus() {
    return 1 + resistManipulator;
  }

  /**
   * Method that sets the attackManipulator and resistManipulator
   * for the unit depending on what terrain is set.
   *
   * @param terrain The terrain.
   */
  @Override
  public void setTerrain(EnumHandler.TerrainTypes terrain){
    if (terrain == EnumHandler.TerrainTypes.FOREST) {
      attackManipulator = 2;
      resistManipulator = 2;
    } else {
      attackManipulator = 0;
      resistManipulator = 0;
    }
  }

  @Override
  public String getType() {
    return "Infantry Unit";
  }

}
