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
 * Class that handles file saving and loading.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class FileHandler {
  static Logger logger = Logger.getLogger("logger");
  private ArmyRegister armyRegister;
  private BattleRegister battleRegister;

  public FileHandler(ArmyRegister armyRegister, BattleRegister battleRegister) {
    this.armyRegister = armyRegister;
    this.battleRegister = battleRegister;
  }

  public enum RegisterType {
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
      //todo: loadArmyCSV doesn't have to return a list, it can simply add it to the list internally, fix this!
      case ARMIES -> armyRegister.loadArmyCSV(path);
      case BATTLES -> battleRegister.loadBattleCSV(path);
      default -> logger.log(Level.WARNING, "Invalid enum type!");
    }

  }




}
