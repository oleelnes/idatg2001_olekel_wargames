package no.ntnu.olekel.core;

import no.ntnu.olekel.constants.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class ArmyRegister {
  private List<Army> armyRegister;

  public ArmyRegister() {
    this.armyRegister = new ArrayList<>();
  }

  /**
   *
   * Todo: error handling for filename
   * @param filename  The name of the file (should start with "/").
   */
  public void load(String filename) {
    FileHandler.readArmyCSV(Path.of(System.getProperty(Constants.ROOT_DIRECTORY)
        + Constants.WARGAMES_FOLDER_PATH + Constants.ARMIES_FOLDER_PATH + filename));
  }
}
