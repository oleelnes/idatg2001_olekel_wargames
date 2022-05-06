package no.ntnu.olekel.core.units;

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
  }

  /**
   * Instantiates a new Infantry unit.
   *
   * @param name   the name
   * @param health the health
   */
  public InfantryUnit(String name, int health){
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
