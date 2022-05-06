package no.ntnu.olekel.core.units;

/**
 * The class Commander unit.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class CommanderUnit extends CavalryUnit {

  /**
   * Instantiates a new Commander unit.
   *
   * @param name   the name
   * @param health the health
   * @param attack the attack
   * @param armor  the armor
   */
  public CommanderUnit(String name, int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  /**
   * Instantiates a new Commander unit.
   *
   * @param name   the name
   * @param health the health
   */
  public CommanderUnit(String name, int health) {
    super(name, health, 20, 12);
  }
}
