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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.Slider;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Battle;
import no.ntnu.olekel.core.EnumHandler;
import no.ntnu.olekel.ui.DialogsHandler;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller of the warSimulationPage.fxml scene.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class WarSimulationPageController implements Initializable {
  private final Scenes scenes = Facade.getInstance().getScenes();
  private final Battle battle = Facade.getInstance().getBattle();
  private final DialogsHandler dialogs = Facade.getInstance().getDialogsHandler();

  private int simulationSpeed;
  private boolean pause;
  private boolean isSimulationRunning;

  private final IntegerProperty unitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getAllUnits().size());
  private final IntegerProperty totalHealthLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getHealth());
  private final IntegerProperty commanderUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getCommanderUnits().size());
  private final IntegerProperty infantryUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getInfantryUnits().size());
  private final IntegerProperty rangedUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getRangedUnits().size());
  private final IntegerProperty cavalryUnitsLeftA1Value = new SimpleIntegerProperty(battle.getArmyOne().getCavalryUnits().size());

  private final IntegerProperty unitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getAllUnits().size());
  private final IntegerProperty totalHealthLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getHealth());
  private final IntegerProperty commanderUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getCommanderUnits().size());
  private final IntegerProperty infantryUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getInfantryUnits().size());
  private final IntegerProperty rangedUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getRangedUnits().size());
  private final IntegerProperty cavalryUnitsLeftA2Value = new SimpleIntegerProperty(battle.getArmyTwo().getCavalryUnits().size());

  private final StringProperty unitTypeA1StringValue = new SimpleStringProperty("");
  private final IntegerProperty unitHealthA1Value = new SimpleIntegerProperty(0);
  private final IntegerProperty unitAttackBonusA1Value = new SimpleIntegerProperty(0);
  private final IntegerProperty unitResistBonusA1Value = new SimpleIntegerProperty(0);

  private final StringProperty unitTypeA2StringValue = new SimpleStringProperty("");
  private final IntegerProperty unitHealthA2Value = new SimpleIntegerProperty(0);
  private final IntegerProperty unitAttackBonusA2Value = new SimpleIntegerProperty(0);
  private final IntegerProperty unitResistBonusA2Value = new SimpleIntegerProperty(0);

  private final StringProperty winningArmyString = new SimpleStringProperty(battle.getWinningArmy());
  private final IntegerProperty roundValue = new SimpleIntegerProperty(battle.getRounds());
  private final StringProperty attackingArmyStringValue = new SimpleStringProperty("Attacking army");

  private final StringProperty unitA1NameStringValue = new SimpleStringProperty("");
  private final StringProperty unitA2NameStringValue = new SimpleStringProperty("");

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

  @FXML
  private Button pauseButtonID;

  @FXML
  private Button startWarButtonID;

  @FXML
  private Slider simulationSpeedSlider;

  @FXML
  private Label simulationSpeedLabel;

  @FXML
  private Label unitTypeA1;

  @FXML
  private Label unitHealthA1;

  @FXML
  private Label unitAttackBonusA1;

  @FXML
  private Label unitResistBonusA1;

  @FXML
  private Label unitTypeA2;

  @FXML
  private Label unitHealthA2;

  @FXML
  private Label unitAttackBonusA2;

  @FXML
  private Label unitResistBonusA2;

  @FXML
  private Label terrainLabel;

  @FXML
  private Label roundLabel;

  @FXML
  private Label attackingArmyLabel;

  @FXML
  private Label unitArmyOneName;

  @FXML
  private Label unitArmyTwoName;

  @FXML
  private Label armyOneName;

  @FXML
  private Label armyTwoName;

  @FXML
  private Label battleName;

  @FXML
  private Label totalHealthLeftA1;

  @FXML
  private Label totalHealthLeftA2;

  /**
   * Method that is run when the controller is loaded. Initializes fields.
   *
   * @param url             url
   * @param resourceBundle  url
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    terrainLabel.setText(battle.getTerrain());
    battleName.setText(battle.getName());
    this.pause = false;
    simulationSpeedSlider.valueProperty().addListener((observableValue, number, t1) -> {
      simulationSpeed = (int)simulationSpeedSlider.getValue();
      simulationSpeedLabel.setText(String.valueOf(simulationSpeed));
      battle.setSimulationSpeed(simulationSpeed);
    });
    armyOneName.setText(battle.getArmyOne().getName());
    armyTwoName.setText(battle.getArmyTwo().getName());
    battleName.setText(battle.getName());
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
    Facade.getInstance().setBattle(null);
  }

  /**
   * This method performs a real-time simulation of the war, by creating a background thread through which
   * labels and other content regarding the warring armies are updated in the GUI as the simulation
   * progresses.
   */
  private void realtimeSimulation(){
    try {
      Task<Void> task = new Task<>() {
        @Override
        protected Void call() {

          //This is the main loop that runs while it is true that both armies have units left.
          while (battle.getArmyOne().hasUnits() && battle.getArmyTwo().hasUnits() && isSimulationRunning) {

            // Checks whether the boolean pause variable is true.
            if (pause) {
              // While pause is true, it loops a 10 milliseconds delay.
              while (pause) {
                delay(10);
              }
              //sets the delay between battles.
            } else delay(battle.getSimulationSpeed());

            //tasks to be done by the GUI in-between delays.
            if (battle.getRounds() > 0) {
              Platform.runLater(() -> unitsLeftA1Value.setValue(battle.getArmyOne().getAllUnits().size()));
              Platform.runLater(() -> totalHealthLeftA1Value.setValue(battle.getArmyOne().getHealth()));
              Platform.runLater(() -> commanderUnitsLeftA1Value.setValue(battle.getArmyOne().getCommanderUnits().size()));
              Platform.runLater(() -> infantryUnitsLeftA1Value.setValue(battle.getArmyOne().getInfantryUnits().size()));
              Platform.runLater(() -> rangedUnitsLeftA1Value.setValue(battle.getArmyOne().getRangedUnits().size()));
              Platform.runLater(() -> cavalryUnitsLeftA1Value.setValue(battle.getArmyOne().getCavalryUnits().size()));

              Platform.runLater(() -> unitsLeftA2Value.setValue(battle.getArmyTwo().getAllUnits().size()));
              Platform.runLater(() -> totalHealthLeftA2Value.setValue(battle.getArmyTwo().getHealth()));
              Platform.runLater(() -> commanderUnitsLeftA2Value.setValue(battle.getArmyTwo().getCommanderUnits().size()));
              Platform.runLater(() -> infantryUnitsLeftA2Value.setValue(battle.getArmyTwo().getInfantryUnits().size()));
              Platform.runLater(() -> rangedUnitsLeftA2Value.setValue(battle.getArmyTwo().getRangedUnits().size()));
              Platform.runLater(() -> cavalryUnitsLeftA2Value.setValue(battle.getArmyTwo().getCavalryUnits().size()));

              Platform.runLater(() -> unitA1NameStringValue.setValue(battle.getArmyOneUnit().getName()));
              Platform.runLater(() -> unitTypeA1StringValue.setValue(battle.getArmyOneUnit().getType()));
              Platform.runLater(() -> unitHealthA1Value.setValue(battle.getArmyOneUnit().getHealth()));
              Platform.runLater(() -> unitAttackBonusA1Value.setValue(battle.getArmyOneUnit().getAttackBonus()));
              Platform.runLater(() -> unitResistBonusA1Value.setValue(battle.getArmyOneUnit().getResistBonus()));

              Platform.runLater(() -> unitA2NameStringValue.setValue(battle.getArmyTwoUnit().getName()));
              Platform.runLater(() -> unitTypeA2StringValue.setValue(battle.getArmyTwoUnit().getType()));
              Platform.runLater(() -> unitHealthA2Value.setValue(battle.getArmyTwoUnit().getHealth()));
              Platform.runLater(() -> unitAttackBonusA2Value.setValue(battle.getArmyTwoUnit().getAttackBonus()));
              Platform.runLater(() -> unitResistBonusA2Value.setValue(battle.getArmyTwoUnit().getResistBonus()));

              Platform.runLater(() -> winningArmyString.setValue(battle.getWinningArmy()));
              Platform.runLater(() -> roundValue.setValue(battle.getRounds()));
              Platform.runLater(() -> attackingArmyStringValue.setValue(battle.getAttackingArmy().getName()));
            }

            //Simulate one round.
            Platform.runLater(battle::simulateOneRound);
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
      totalHealthLeftA1.textProperty().bind(totalHealthLeftA1Value.asString());
      commanderUnitsLeftA1.textProperty().bind(commanderUnitsLeftA1Value.asString());
      infantryUnitsLeftA1.textProperty().bind(infantryUnitsLeftA1Value.asString());
      rangedUnitsLeftA1.textProperty().bind(rangedUnitsLeftA1Value.asString());
      cavalryUnitsLeftA1.textProperty().bind(cavalryUnitsLeftA1Value.asString());

      totalUnitsLeftA2.textProperty().bind(unitsLeftA2Value.asString());
      totalHealthLeftA2.textProperty().bind(totalHealthLeftA2Value.asString());
      commanderUnitsLeftA2.textProperty().bind(commanderUnitsLeftA2Value.asString());
      infantryUnitsLeftA2.textProperty().bind(infantryUnitsLeftA2Value.asString());
      rangedUnitsLeftA2.textProperty().bind(rangedUnitsLeftA2Value.asString());
      cavalryUnitsLeftA2.textProperty().bind(cavalryUnitsLeftA2Value.asString());

      unitArmyOneName.textProperty().bind(unitA1NameStringValue);
      unitTypeA1.textProperty().bind(unitTypeA1StringValue);
      unitHealthA1.textProperty().bind(unitHealthA1Value.asString());
      unitAttackBonusA1.textProperty().bind(unitAttackBonusA1Value.asString());
      unitResistBonusA1.textProperty().bind(unitResistBonusA1Value.asString());

      unitArmyTwoName.textProperty().bind(unitA2NameStringValue);
      unitTypeA2.textProperty().bind(unitTypeA2StringValue);
      unitHealthA2.textProperty().bind(unitHealthA2Value.asString());
      unitAttackBonusA2.textProperty().bind(unitAttackBonusA2Value.asString());
      unitResistBonusA2.textProperty().bind(unitResistBonusA2Value.asString());

      winningArmyLabel.textProperty().bind(winningArmyString);
      roundLabel.textProperty().bind(roundValue.asString());
      attackingArmyLabel.textProperty().bind(attackingArmyStringValue);

    } catch (Exception e) {
      e.printStackTrace();
    }
    if (!battle.getArmyOne().hasUnits() || !battle.getArmyTwo().hasUnits()) {
      if(battle.getArmyOne().hasUnits())
        winningArmyLabel.setText(battle.getArmyOne().getName() + " won the battle!");
      else winningArmyLabel.setText(battle.getArmyTwo().getName() + " won the battle!");
    }
  }

  /**
   * This method starts the war simulation in the desired format IF it has not already
   * been started AND both of the armies have units left.
   */
  private void simulate() {
    if (battle.getArmyOne().hasUnits()
        && battle.getArmyTwo().hasUnits()){
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
   */
  @FXML
  public void startWarAction(ActionEvent event) throws IOException {
    isSimulationRunning = !isSimulationRunning;
    if (isSimulationRunning) {
      simulate();
      startWarButtonID.setText("Stop war");
    } else {
      if (!dialogs.stopBattleDialog(event)) {
        isSimulationRunning = true;
        simulate();
      } else {
        Facade.getInstance().setBattle(null);
      }
    }
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

  /**
   * Method that pauses the simulation.
   *
   */
  @FXML
  public void pauseButtonAction() {
    pause = !pause;
    if (pause) pauseButtonID.setText("Play");
    else pauseButtonID.setText("Pause");
  }
}
