package no.ntnu.olekel.core.units;

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
  }

  @Override
  public int getAttackBonus() {
    return 3;
  }

  @Override
  public int getResistBonus() {
    if (timesResisted == 0) {
      timesResisted++;
      return 6;
    } else if (timesResisted < 3) {
      timesResisted++;
      return 4;
    } else {
      timesResisted++;
      return 2;
    }
  }

  @Override
  public String getType() {
    return "Ranged Unit";
  }

}
