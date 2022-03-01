package no.ntnu.olekel;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class Battle. This class is responsible for simulating a battle between
 * two warring armies.
 *
 * @author Ole Kristian Elnæs
 * @version 16.02.2022
 */
public class Battle {
  private Army armyOne;
  private Army armyTwo;
  private final Logger logger;
  private final Random random;

  /**
   * Instantiates a new Battle.
   *
   * @param armyOne the army one
   * @param armyTwo the army two
   */
  Battle(Army armyOne, Army armyTwo) {
    this.armyOne = armyOne;
    this.armyTwo = armyTwo;
    this.logger = Logger.getLogger(this.getClass().toString());
    this.random = new Random();
  }

  /**
   * Simulate a battle between two armies.
   *
   * todo: create another version of simulate that has a more realistic battle structure
   *
   * @return  the winning army.
   */
  public Army simulate() {
    int rounds = 0;

    logger.log(Level.INFO, () -> "A battle between the two armies " + armyOne.getName() +
        " and " + armyTwo.getName() + " is about to start!");

    //This is the main loop which runs as long as both of the armies still have armies left
    while (armyOne.hasUnits() && armyTwo.hasUnits()) {
      int attacker = random.nextInt(2);
      Unit unitA1 = armyOne.getRandom();
      Unit unitA2 = armyTwo.getRandom();

      //This if-else todo:->
      if (attacker == 0) attackUnit(unitA1, unitA2, armyTwo);
      else attackUnit(unitA2, unitA1, armyOne);

      rounds++;
    }
    logger.log(Level.INFO, "rounds: {0}", rounds);

    if (armyTwo.hasUnits()) {
      logger.log(Level.INFO, "a2 won");
      return armyTwo;
    } else {
      logger.log(Level.INFO, "a1 won");
      return armyOne;
    }
  }

  /**
   * Method that performs an attack between an attacker- and defender instance
   * of Unit. It also nests the functionality inside a try-catch; if an exception is
   * caught, it means that the defender's health is out of bounds and that the defender
   * Unit is dead, and is thus removed from its respective Army instance.
   *
   * @param attacker     The instance of Unit that is attacking.
   * @param defender     The instance of Unit that is defending.
   * @param defenderArmy The instance of Army that is defending.
   */
  private void attackUnit(Unit attacker, Unit defender, Army defenderArmy) {
    try {
      attacker.attack(defender);
    } catch (IllegalArgumentException e) {
      defenderArmy.remove(defender);
    }
  }

  @Override
  public String toString() {
    return "Battle{" +
            "armyOne=" + armyOne +
            ", armyTwo=" + armyTwo +
            ", random=" + random +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Battle battle = (Battle) o;
    return Objects.equals(armyOne, battle.armyOne) &&
            Objects.equals(armyTwo, battle.armyTwo) &&
            Objects.equals(logger, battle.logger) &&
            Objects.equals(random, battle.random);
  }

  @Override
  public int hashCode() {
    return Objects.hash(armyOne, armyTwo, logger, random);
  }
}
