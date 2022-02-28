package no.ntnu.olekel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class WarGamesApp.
 *
 * @author Ole Kristian Elnæs.
 * @version 28.02.2022.
 */
public class WarGamesApp {
  private static final Logger logger = Logger.getLogger("logger");;


  /**
   * The entry point of the application. This method creates two armies and a
   * battle for as many times as is set as the limit in the for-loop, then
   * executes that number of simulations of a battle.
   *
   * @param args the input arguments.
   */
  public static void main(String[] args) {
    int armyOneWins = 0;
    int armyTwoWins = 0;
    for (int i = 0; i < 100; i++){
      Army armyOne = new Army("armyOne", createUnits(1, 10, 18, 12));
      Army armyTwo = new Army("armyTwo", createUnits(1, 10, 18, 12));
      Battle battle = new Battle(armyOne, armyTwo);
      if (battle.simulate() == armyOne) {
        armyOneWins++;
      } else {
        armyTwoWins++;
      }
    }
    logger.log(Level.INFO, "army one wins: " + armyOneWins + "\narmy two wins: " + armyTwoWins);
  }

  /**
   * This method creates units for an army. The number of each of the unit subclasses
   * is given my the method parameters.
   *
   * @param commanderUnits  Number of commander units.
   * @param cavalryUnits    Number of cavalry units.
   * @param infantryUnits   Number of infantry units.
   * @param rangedUnits     Number of ranged units.
   * @return                A List<Unit> with all the units.
   */
  private static List<Unit> createUnits(int commanderUnits, int cavalryUnits, int infantryUnits, int rangedUnits) {
    List<Unit> units = new ArrayList<>();
    List<Unit> unita = new ArrayList<>();
    for (int i = 0; i < commanderUnits; i++) {
      units.add(new CommanderUnit("Commander Unit " + i + 1, 100));
    }
    for (int i = 0; i < cavalryUnits; i++) {
      units.add(new CavalryUnit("Cavalry Unit " + i + 1, 100));
    }
    for (int i = 0; i < infantryUnits; i++) {
      units.add(new InfantryUnit("Infantry Unit " + i + 1, 100));
    }
    for (int i = 0; i < rangedUnits; i++) {
      units.add(new RangedUnit("Ranged Unit " + i + 1, 100));
    }

    return units;
  }
}
