package no.ntnu.olekel.core;

import no.ntnu.olekel.core.units.Unit;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class Battle. This class is responsible for simulating a battle between
 * two warring armies.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class Battle {
  private Army armyOne;
  private Army armyTwo;
  private final Logger logger;
  private final Random random;


  private String terrain;
  private String simulationMode;
  private int simulationSpeed;


  /**
   * Instantiates a new Battle.
   *
   * @param armyOne the army one
   * @param armyTwo the army two
   */
  public Battle(Army armyOne, Army armyTwo) {
    this.armyOne = armyOne;
    this.armyTwo = armyTwo;
    this.logger = Logger.getLogger(this.getClass().toString());
    this.random = new Random();
    this.terrain = "";
    this.simulationMode = "";
    this.simulationSpeed = 1000;
  }


  /**
   * This method simulates a battle between two armies.
   *
   * @return the winning army.
   */
  public Army simulateAllRounds() {
    int rounds = 0;
    logger.log(Level.INFO, () -> "A battle between the two armies " + armyOne.getName() +
            " and " + armyTwo.getName() + " is about to start!");

    //This is the main loop which runs as long as both of the armies still have armies left.
    while (armyOne.hasUnits() && armyTwo.hasUnits()) {
      boolean attacker = random.nextBoolean();
      Unit unitA1 = armyOne.getRandom();
      Unit unitA2 = armyTwo.getRandom();

      //This if-else statement decides which army and unit gets to attack
      //depending on the random int attacker.
      if (attacker) attackUnit(unitA1, unitA2, armyTwo);
      else attackUnit(unitA2, unitA1, armyOne);

      rounds++;
      //SimpleWarSimulationPage.getInstance().update();
    }
    logger.log(Level.INFO, "rounds: {0}", rounds);

    if (armyTwo.hasUnits()) {
      logger.log(Level.INFO, () -> armyTwo.getName() + " won");
      return armyTwo;
    } else {
      logger.log(Level.INFO, () -> armyOne.getName() + " won");
      return armyOne;
    }
  }

  /**
   * This method simulates one round of a battle betweeen two armies.
   *
   * @return  Boolean todo:
   */
  public boolean simulateOneRound() {
    //This is the main loop which runs as long as both of the armies still have armies left.
    if (armyOne.hasUnits() && armyTwo.hasUnits()) {
      boolean attacker = random.nextBoolean();
      Unit unitA1 = armyOne.getRandom();
      Unit unitA2 = armyTwo.getRandom();

      //This if-else statement decides which army and unit gets to attack
      //depending on the random int attacker.
      if (attacker) attackUnit(unitA1, unitA2, armyTwo);
      else attackUnit(unitA2, unitA1, armyOne);
    }

    return armyOne.hasUnits() && armyTwo.hasUnits();
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
    attacker.attack(defender);
    if (defender.getHealth() <= 0) defenderArmy.remove(defender);
  }

  public Army getArmyOne(){
    return armyOne;
  }

  public Army getArmyTwo(){
    return armyTwo;
  }

  public void setSimulationSpeed(int simulationSpeed) {
    if (simulationSpeed > 0 && simulationSpeed <= 100)
      this.simulationSpeed = 1000 / simulationSpeed;
    else this.simulationSpeed = 1000;
  }

  public int getSimulationSpeed(){
    return simulationSpeed;
  }


  public String getTerrain() {
    return terrain;
  }

  public void setTerrain(String terrain) {
    this.terrain = terrain;
  }

  public String getSimulationMode() {
    return simulationMode;
  }

  public void setSimulationMode(String simulationMode) {
    this.simulationMode = simulationMode;
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
