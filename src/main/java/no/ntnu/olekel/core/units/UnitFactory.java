package no.ntnu.olekel.core.units;

import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UnitFactory class that abstracts and simplifies the unit creation process.
 * Follows both the singleton and the factory design patterns.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class UnitFactory {
  private static volatile UnitFactory instance;

  /**
   * Private constructor.
   */
  private UnitFactory(){

  }

  /**
   * Method that checks whether an instance of the class exists or not,
   * if not, it creates the instance. After this, it returns the instance.
   *
   * @return  The instance of the class.
   */
  public static UnitFactory getInstance() {
    if(instance == null) {
      synchronized (Facade.class) {
        instance = new UnitFactory();
      }
    }
    return instance;
  }

  /**
   *
   *
   * @param unitType  The type of the unit.
   * @param name      The name of the unit.
   * @param health    The health of the unit.
   * @return          If unitType coincides with an existing unit type, it
   *                  returns a unit of that type, else it returns null.
   */
  public Unit createUnit(String unitType, String name, int health) {
    return switch (unitType) {
      case "infantry" -> new InfantryUnit(name, health);
      case "commander" -> new CommanderUnit(name, health);
      case "cavalry" -> new CavalryUnit(name, health);
      case "ranged" -> new RangedUnit(name, health);
      default -> null;
    };
  }

  /**
   * Method that returns a list of units in the desired unit type.
   *
   * @param unitType  The type of the unit.
   * @param name      The name of the unit. Naming convention: name + index (ex: "name 1", "name 2")
   * @param health    The health of the unit.
   * @param amount    The amount of units in the list to be returned.
   * @return          A list of units in the desired type. Returns null if invalid type.
   */
  public List<Unit> createUnitList(String unitType, String name, int health, int amount) {
    if (!checkCreateUnitListInput(unitType, name, health, amount)) return Collections.emptyList();
    List<Unit> units = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      units.add(createUnit(unitType, name + " " + i, health));
    }
    return units;
  }

  /**
   * Method that checks whether the input to the createUnitList() method is valid.
   * todo: check if name is already used, if so, return false.
   *
   * @param unitType  The type of the unit.
   * @param name      The name of the unit.
   * @param health    The health of the unit.
   * @param amount    The amount of units in the list to be returned.
   * @return          True if all input is valid, false if not.
   */
  private boolean checkCreateUnitListInput(String unitType, String name, int health, int amount){
    if (amount < 100000 && amount > 0) {
      //logger error here/possibly trigger a dialog window?
      return false;
    }
    if (createUnit(unitType, name, health) == null) {
      //logger error here
      return false;
    }
    return true;
  }

}
