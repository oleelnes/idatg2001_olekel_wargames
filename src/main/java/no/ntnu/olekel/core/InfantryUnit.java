package no.ntnu.olekel.core;

/**
 * The class Infantry unit.
 *
 * @author  Ole Kristian Elnæs
 * @version 16.02.2022
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
  InfantryUnit(String name, int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  /**
   * Instantiates a new Infantry unit.
   *
   * @param name   the name
   * @param health the health
   */
  InfantryUnit(String name, int health){
    super(name, health, 15, 10);
  }

  @Override
  public int getAttackBonus() {
    return 2;
  }

  @Override
  public int getResistBonus() {
    return 1;
  }


}
