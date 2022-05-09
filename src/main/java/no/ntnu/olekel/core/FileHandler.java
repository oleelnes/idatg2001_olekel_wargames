package no.ntnu.olekel.core;

import no.ntnu.olekel.constants.Constants;
import no.ntnu.olekel.core.units.*;
import no.ntnu.olekel.ui.Facade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
  private ArrayList<Path> armyPaths;
  private ArrayList<Path> battlePaths;

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

  /**
   * This method iterates over all files in a given directory and adds the data
   * to a register given by the register type. Data will not be added to a register
   * if it doesn't match with the requirements.
   * todo: check if this is true (the last sentence).
   *
   * @param path          The path to the directory from which files are to be loaded.
   * @param registerType  The register type into which data from file will be loaded.
   */
  public void loadDirectory(Path path, RegisterType registerType) {
    try {
      List<File> files = Files.list(path)
          .map(Path::toFile)
          .filter(File::isFile)
          .filter(f -> getFileExtension(f.toString()).equals("csv"))
          .collect(Collectors.toList());
      files.forEach(f -> load(registerType, f.toPath()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Method that returns a file's type/extension. This method was found on the internet,
   * but is altered to better fit this project's purpose, see source below.
   *
   * Source: https://stackoverflow.com/a/25299575
   *
   * @param filePathString  The path to the file as a String.
   * @return                The file extension.
   */
  private String getFileExtension(String filePathString) {
    String fileName = new File(filePathString).getName();
    int dotIndex = fileName.lastIndexOf('.');
    return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
  }

}
