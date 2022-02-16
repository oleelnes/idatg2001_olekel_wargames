package no.ntnu.olekel;

/**
 * The class Commander unit.
 *
 * @author Ole Kristian Elnæs
 * @version 16.02.2022
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
  CommanderUnit(String name, int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  /**
   * Instantiates a new Commander unit.
   *
   * @param name   the name
   * @param health the health
   */
  CommanderUnit(String name, int health) {
    super(name, health, 20, 12);
  }
}
