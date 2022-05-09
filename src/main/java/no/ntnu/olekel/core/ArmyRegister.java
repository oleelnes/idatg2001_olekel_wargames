package no.ntnu.olekel.core;

import no.ntnu.olekel.core.units.*;

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
  public void loadArmyCSV(Path path) {
    Army armyFromFile;

    try (BufferedReader reader = Files.newBufferedReader(path)) {
      String lineOfText;
      lineOfText = reader.readLine();
      armyFromFile = new Army(lineOfText);
      while ((lineOfText = reader.readLine()) != null) {
        String[] words = lineOfText.split(",");
        switch (words[0]) {
          case "Infantry Unit" -> armyFromFile.add(new InfantryUnit(words[1], Integer.parseInt(words[2])));
          case "Cavalry Unit" -> armyFromFile.add(new CavalryUnit(words[1], Integer.parseInt(words[2])));
          case "Commander Unit" -> armyFromFile.add(new CommanderUnit(words[1], Integer.parseInt(words[2])));
          case "Ranged Unit" -> armyFromFile.add(new RangedUnit(words[1], Integer.parseInt(words[2])));
          default -> logger.log(Level.INFO, "Invalid type was attempted to be read");
        }
      }
      armyRegister.add(armyFromFile);
      System.out.println(armyFromFile.getCavalryUnits().get(0).getName());
    } catch (IOException e) {
      logger.log(Level.WARNING, e.getMessage());
    }
  }


}
