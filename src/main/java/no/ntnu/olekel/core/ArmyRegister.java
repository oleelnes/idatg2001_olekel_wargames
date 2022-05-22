package no.ntnu.olekel.core;

import javafx.scene.control.Alert;
import no.ntnu.olekel.core.units.*;
import no.ntnu.olekel.ui.DialogsHandler;
import no.ntnu.olekel.ui.Facade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class ArmyRegister {
  private List<Army> armyRegister;
  static Logger logger = Logger.getLogger("logger");
  private final UnitFactory unitFactory = new UnitFactory();

  public ArmyRegister() {
    this.armyRegister = new ArrayList<>();
  }


  public List<Army> getArmyRegister() {
    return armyRegister;
  }

  /**
   * This method writes information about an army to a csv file.
   *
   * @param army  The army whose information that will be written to file.
   * @param path  The path of the csv file to which information will be written.
   */
  public static void writeArmyCSV(Army army, Path path) {
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      writer.write(army.getName() + "\n");
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
      for (Unit unit : army.getAllUnits()) {
        //todo:
      }
    } catch (IOException e) {
      logger.log(Level.WARNING, e.getMessage());
    }
  }

  /**
   * This method reads information about an army in a CSV file,
   * then creates an army with those specifications and returns
   * that army.
   *
   * @param path  The path to the csv file to read
   * @return      An instance of the class Army
   */
  public Army loadArmyCSV(Path path) {
    Army armyFromFile;

    try (BufferedReader reader = Files.newBufferedReader(path)) {
      String lineOfText;
      lineOfText = reader.readLine();
      armyFromFile = new Army(lineOfText);
      while ((lineOfText = reader.readLine()) != null) {
        String[] words = lineOfText.split(",");
        switch (words[0]) {
          case "Infantry Unit" -> armyFromFile.add(unitFactory.createUnit(UnitFactory.Type.INFANTRY, words[1], Integer.parseInt(words[2])));
          case "Cavalry Unit" -> armyFromFile.add(unitFactory.createUnit(UnitFactory.Type.CAVALRY, words[1], Integer.parseInt(words[2])));
          case "Commander Unit" -> armyFromFile.add(unitFactory.createUnit(UnitFactory.Type.COMMANDER, words[1], Integer.parseInt(words[2])));
          case "Ranged Unit" -> armyFromFile.add(unitFactory.createUnit(UnitFactory.Type.RANGED, words[1], Integer.parseInt(words[2])));
          default -> logger.log(Level.INFO, "Invalid type was attempted to be read");
        }
      }
      if (!armyFromFile.getAllUnits().isEmpty())
        return armyFromFile;
      else {
        Facade.getInstance().getDialogsHandler().errorAlert("Could not load file!");
        return null;
      }
    } catch (IOException e) {
      logger.log(Level.WARNING, e.getMessage());
      Facade.getInstance().getDialogsHandler().errorAlert("Could not load file!");
      return null;
    }
  }

  public boolean loadArmyToRegister(Path path) {
    Army newArmy = loadArmyCSV(path);
    if (newArmy != null) {
      armyRegister.add(newArmy);
      newArmy.setFilePath(path.toString());
      return true;
    } else {
      return false;
    }
  }

  public boolean loadArmyFileContentToArmy(Army army, Path path) {
    Army newArmy = loadArmyCSV(path);
    if (newArmy != null) {
      army.getAllUnits().addAll(newArmy.getAllUnits());
      return true;
    } else {
      return false;
    }
  }

  public void update(){
    if (!armyRegister.isEmpty()) armyRegister.forEach(Army::updateHealth);
  }

}
