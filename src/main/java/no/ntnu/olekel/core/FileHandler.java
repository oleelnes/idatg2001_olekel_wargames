package no.ntnu.olekel.core;

import no.ntnu.olekel.core.units.*;
import no.ntnu.olekel.ui.Facade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles file writing and -reading with regards to the class Army.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class FileHandler {
  static Logger logger = Logger.getLogger("logger");
  private ArmyRegister armyRegister = Facade.getInstance().getArmyRegister();

  enum RegisterType {
    ARMIES,
    BATTLES
  }

  /**
   * This method handles all loading into registers from files.
   *
   * @param registerType  The type of register that data will be loaded into.
   * @param path          The path of the file to read from.
   */
  public void load(RegisterType registerType, Path path) {
    switch (registerType) {
      case ARMIES -> armyRegister.loadArmyCSV(path);
      case BATTLES -> System.out.println("placeholder");
      default -> System.out.println("default");
    }
  }


}
