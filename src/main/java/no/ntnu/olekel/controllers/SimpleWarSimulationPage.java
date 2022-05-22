package no.ntnu.olekel.controllers;


import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

  @FXML
  private Label armyOneUnitsLost;

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
            delay(delay);

            //Simulate one round.
            Platform.runLater(() -> battle.simulateOneRound());

            //tasks to be done by the GUI in-between delays.
            Platform.runLater(() -> unitsLeftA1Value.setValue(battle.getArmyOne().getAllUnits().size()));

          }
          return null;
        }
      };

      //Thread creation and initialization
      Thread th = new Thread(task);
      th.setDaemon(true); //sets the thread as a daemon thread (low-priority/background thread)
      th.start();

      //Binding values as set inside the while-loop and the Task to their appropriate labels/fields.
      armyOneUnitsLost.textProperty().bind(unitsLeftA1Value.asString());

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


  private void setDelay(int delay){
    this.delay = delay;
  }

}
