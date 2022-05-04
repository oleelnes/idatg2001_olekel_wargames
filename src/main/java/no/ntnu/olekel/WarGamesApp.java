package no.ntnu.olekel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ntnu.olekel.core.*;
import no.ntnu.olekel.core.units.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * The class WarGamesApp.
 *
 * @author Ole Kristian Elnæs.
 * @version 28.02.2022.
 */
public class WarGamesApp extends Application {
  private static final Logger logger = Logger.getLogger("logger");

  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml-files/mainPage.fxml")));
    Scene scene = new Scene(root, 800, 600);
    stage.setMinHeight(600);
    stage.setMinWidth(800);
    stage.setTitle("WarGames");
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() throws IOException {System.exit(0);}

  /**
   * The entry point of the application. This method creates two armies and a
   * battle for as many times as is set as the limit in the for-loop, then
   * executes that number of simulations of a battle.
   *
   * @param args the input arguments.
   */
  public static void main(String[] args) {
    launch();
    int armyOneWins = 0;
    int armyTwoWins = 0;
    for (int i = 0; i < 500; i++){
      Army armyOne = new Army("Army One", createUnits(14, 100, 180, 120));
      Army armyTwo = new Army("Army Two", createUnits(14, 100, 180, 120));
      Battle battle = new Battle(armyOne, armyTwo);
      /*if (battle.simulate() == armyOne) {
        armyOneWins++;
      } else {
        armyTwoWins++;
      }*/
    }
    /*logger.log(Level.INFO,  "army one wins: {0}", armyOneWins);
    logger.log(Level.INFO, "army two wins: {0}", armyTwoWins);
    if (armyOneWins > armyTwoWins) {
      logger.log(Level.INFO, "Army one won the war!");
    } else if (armyOneWins == armyTwoWins) {
      logger.log(Level.INFO, "The war ended in a tie!");
    } else {
      logger.log(Level.INFO, "Army two won the war!");
    }*/
    Army fileArmy = new Army("File Army", createUnits(14, 10, 10, 10));
    FileHandler.writeArmyCSV(fileArmy, Path.of("ArmyOne.csv"));
  }

  /**
   * This method creates units for an army. The number of each of the unit subclasses
   * is given my the method parameters.
   *
   * @param commanderUnits  Number of commander units.
   * @param cavalryUnits    Number of cavalry units.
   * @param infantryUnits   Number of infantry units.
   * @param rangedUnits     Number of ranged units.
   * @return                A List<Unit> with all the units.
   */
  public static List<Unit> createUnits(int commanderUnits, int cavalryUnits, int infantryUnits, int rangedUnits) {
    List<Unit> units = new ArrayList<>();
    for (int i = 0; i < commanderUnits; i++) {
      units.add(new CommanderUnit("Commander Unit " + i, 20));
    }
    for (int i = 0; i < cavalryUnits; i++) {
      units.add(new CavalryUnit("Cavalry Unit " + i, 10));
    }
    for (int i = 0; i < infantryUnits; i++) {
      units.add(new InfantryUnit("Infantry Unit " + i, 10));
    }
    for (int i = 0; i < rangedUnits; i++) {
      units.add(new RangedUnit("Ranged Unit " + i, 10));
    }
    return units;
  }
}
