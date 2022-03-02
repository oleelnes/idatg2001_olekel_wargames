package no.ntnu.olekel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles file writing and -reading with regards to the class Army.
 *
 * @author  Ole Kristian Elnæs
 * @version 02.03.2022
 */
public class ArmyFileHandler {
  static Logger logger = Logger.getLogger("logger");

  public static void writeArmyCSV(Army army, Path path) {
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      writer.write(army.getName());
      for (Unit unit : army.getInfantryUnits()) {
        writer.write("Infantry Unit" + "," + unit.getName() + "," + unit.getHealth() + "\n");
      }
      for (Unit unit : army.getCavalryUnits()) {
        writer.write("Cavalry Unit" + "," + unit.getName() + "," + unit.getHealth() + "\n");
      }
      for (Unit unit : army.getCommanderUnits()) {
        writer.write("Commander Unit" + "," + unit.getName() + "," + unit.getHealth() + "\n");
      }
      for (Unit unit : army.getRangedUnits()) {
        writer.write("Ranged Unit" + "," + unit.getName() + "," + unit.getHealth() + "\n");
      }
    } catch (IOException e) {
      logger.log(Level.WARNING, e.getMessage());
    }
  }

  

}
