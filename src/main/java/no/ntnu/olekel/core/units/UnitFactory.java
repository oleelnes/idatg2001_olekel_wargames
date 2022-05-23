package no.ntnu.olekel.core.units;

import no.ntnu.olekel.constants.UnitNames;
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

  /**
   * Constructor.
   */
  public UnitFactory(){
  }


  /**
   * Method that creates and returns a unit of a specified type.
   *
   * @param unitType  The type of the unit.
   * @param name      The name of the unit.
   * @param health    The health of the unit.
   * @return          If unitType coincides with an existing unit type, it
   *                  returns a unit of that type, else it returns null.
   */
  public Unit createUnit(Type unitType, String name, int health) {
    return switch (unitType) {
      case INFANTRY -> new InfantryUnit(name, health);
      case COMMANDER -> new CommanderUnit(name, health);
      case CAVALRY -> new CavalryUnit(name, health);
      case RANGED -> new RangedUnit(name, health);
    };
  }

  public enum Type {
    COMMANDER,
    INFANTRY,
    CAVALRY,
    RANGED
  }

  /**
   * Method that returns a list of units in the desired unit type.
   *
   * @param unitType  The type of the unit.
   * @param health    The health of the unit.
   * @param amount    The amount of units in the list to be returned.
   * @return          A list of units in the desired type. Returns null if invalid type.
   */
  public List<Unit> createUnitList(Type unitType, int health, int amount) {
    List<Unit> units = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      String name = Facade.getInstance().getUnitNames().createUnitName();
      //checks whether the input is valid or not
      if (!checkCreateUnitListInput(unitType, name, health, amount))
        return Collections.emptyList();
      units.add(createUnit(unitType, name, health));
      System.out.println("unit " + i + " of type " + unitType + " has been created.");
    }
    return units;
  }

  /**
   * Method that checks whether the input to the createUnitList() method is valid.
   * todo: check if name is already used, if so, return false. (should prob be for createUnit() instead)
   *
   * @param unitType  The type of the unit.
   * @param name      The name of the unit.
   * @param health    The health of the unit.
   * @param amount    The amount of units in the list to be returned.
   * @return          True if all input is valid, false if not.
   */
  private boolean checkCreateUnitListInput(Type unitType, String name, int health, int amount){
    if (amount > 100000 || amount < 0) {
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
