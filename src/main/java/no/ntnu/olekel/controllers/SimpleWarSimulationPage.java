package no.ntnu.olekel.controllers;


import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Battle;
import no.ntnu.olekel.ui.DialogsHandler;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller of the simpleWarSimulationPage.fxml scene.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class SimpleWarSimulationPage implements Initializable {
  private final Scenes scenes = Facade.getInstance().getScenes();
  private Battle battle = Facade.getInstance().getBattle();
  private DialogsHandler dialogs = Facade.getInstance().getDialogsHandler();

  private boolean simulationRunning = false;
  private int delay;

  private IntegerProperty unitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getAllUnits().size());
  private IntegerProperty commanderUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getCommanderUnits().size());
  private IntegerProperty infantryUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getInfantryUnits().size());
  private IntegerProperty rangedUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getRangedUnits().size());
  private IntegerProperty cavalryUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getCavalryUnits().size());

  private IntegerProperty unitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getAllUnits().size());
  private IntegerProperty commanderUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getCommanderUnits().size());
  private IntegerProperty infantryUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getInfantryUnits().size());
  private IntegerProperty rangedUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getRangedUnits().size());
  private IntegerProperty cavalryUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getCavalryUnits().size());

  private StringProperty winningArmyString = new SimpleStringProperty(battle.getWinningArmy());

  @FXML
  private Label armyOneUnitsLost;

  @FXML
  private Label totalUnitsLeftA1;

  @FXML
  private Label commanderUnitsLeftA1;

  @FXML
  private Label infantryUnitsLeftA1;

  @FXML
  private Label rangedUnitsLeftA1;

  @FXML
  private Label cavalryUnitsLeftA1;

  @FXML
  private Label totalUnitsLeftA2;

  @FXML
  private Label commanderUnitsLeftA2;

  @FXML
  private Label infantryUnitsLeftA2;

  @FXML
  private Label rangedUnitsLeftA2;

  @FXML
  private Label cavalryUnitsLeftA2;

  @FXML
  private Label winningArmyLabel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    armyOneUnitsLost.setText(String.valueOf(Facade.getInstance().getBattleHandler().getArmyOne().getAllUnits().size()));
    this.delay = 10;
  }


  /**
   * This method takes the user back to the main page as a result of pressing the
   * back to main page button.
   *
   * @param event         When back to main page button is pressed.
   * @throws IOException  Input output exception.
   */
  public void backToMainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

  /**
   * This method performs a real-time simulation of the war, by creating a background thread through which
   * labels and other content regarding the warring armies are updated in the GUI as the simulation
   * progresses.
   */
  private void realtimeSimulation(){
    try {
      Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() {

          //This is the main loop that runs while it is true that both armies have units left.
          while (battle.getArmyOne().hasUnits() && battle.getArmyTwo().hasUnits()) {

            //sets the delay
            delay(battle.getSimulationSpeed());

            //Simulate one round.
            Platform.runLater(() -> battle.simulateOneRound());

            //tasks to be done by the GUI in-between delays.
            Platform.runLater(() -> unitsLeftA1Value.setValue(battle.getArmyOne().getAllUnits().size()));
            Platform.runLater(() -> commanderUnitsLeftA1Value.setValue(battle.getArmyOne().getCommanderUnits().size()));
            Platform.runLater(() -> infantryUnitsLeftA1Value.setValue(battle.getArmyOne().getInfantryUnits().size()));
            Platform.runLater(() -> rangedUnitsLeftA1Value.setValue(battle.getArmyOne().getRangedUnits().size()));
            Platform.runLater(() -> cavalryUnitsLeftA1Value.setValue(battle.getArmyOne().getCavalryUnits().size()));

            Platform.runLater(() -> unitsLeftA2Value.setValue(battle.getArmyTwo().getAllUnits().size()));
            Platform.runLater(() -> commanderUnitsLeftA2Value.setValue(battle.getArmyTwo().getCommanderUnits().size()));
            Platform.runLater(() -> infantryUnitsLeftA2Value.setValue(battle.getArmyTwo().getInfantryUnits().size()));
            Platform.runLater(() -> rangedUnitsLeftA2Value.setValue(battle.getArmyTwo().getRangedUnits().size()));
            Platform.runLater(() -> cavalryUnitsLeftA2Value.setValue(battle.getArmyTwo().getCavalryUnits().size()));

            Platform.runLater(() -> winningArmyString.setValue(battle.getWinningArmy()));

          }
          return null;
        }
      };

      //Thread creation and initialization
      Thread th = new Thread(task);
      th.setDaemon(true); //sets the thread as a daemon thread (low-priority/background thread)
      th.start();

      //Binding values as set inside the while-loop and the Task to their appropriate labels/fields.
      totalUnitsLeftA1.textProperty().bind(unitsLeftA1Value.asString());
      commanderUnitsLeftA1.textProperty().bind(commanderUnitsLeftA1Value.asString());
      infantryUnitsLeftA1.textProperty().bind(infantryUnitsLeftA1Value.asString());
      rangedUnitsLeftA1.textProperty().bind(rangedUnitsLeftA1Value.asString());
      cavalryUnitsLeftA1.textProperty().bind(cavalryUnitsLeftA1Value.asString());

      totalUnitsLeftA2.textProperty().bind(unitsLeftA2Value.asString());
      commanderUnitsLeftA2.textProperty().bind(commanderUnitsLeftA2Value.asString());
      infantryUnitsLeftA2.textProperty().bind(infantryUnitsLeftA2Value.asString());
      rangedUnitsLeftA2.textProperty().bind(rangedUnitsLeftA2Value.asString());
      cavalryUnitsLeftA2.textProperty().bind(cavalryUnitsLeftA2Value.asString());

      winningArmyLabel.textProperty().bind(winningArmyString);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method starts the war simulation in the desired format IF it has not already
   * been started AND both of the armies have units left.
   */
  private void simulate() {
    if (!simulationRunning && battle.getArmyOne().hasUnits()
        && battle.getArmyTwo().hasUnits()){
      simulationRunning = true;
      realtimeSimulation();
    } else if (!battle.getArmyOne().hasUnits() || !battle.getArmyTwo().hasUnits())  {
      dialogs.errorAlert("One or more armies don't have any units!");
    } else {
      dialogs.errorAlert("A simulation is already running!");
    }
  }

  /**
   * This method starts the war simulation.
   *
   * @param event When start war button is clicked.
   */
  @FXML
  public void startWarAction(ActionEvent event) {
    simulate();
  }

  /**
   * Method that performs a sleep/wait for a thread.
   *
   * @param millisec  Delay in milliseconds
   */
  private void delay(int millisec) {
    try {
      Thread.sleep(millisec);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
  }



}
