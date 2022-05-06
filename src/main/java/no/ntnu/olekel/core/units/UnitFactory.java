package no.ntnu.olekel.core.units;

import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

public class UnitFactory {
  private static volatile UnitFactory instance;

  /**
   * Private constructor.
   * todo: initialize fields.
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

}
