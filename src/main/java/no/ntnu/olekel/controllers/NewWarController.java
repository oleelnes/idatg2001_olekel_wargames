package no.ntnu.olekel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.ArmyRegister;
import no.ntnu.olekel.core.BattleHandler;
import no.ntnu.olekel.core.units.Unit;
import no.ntnu.olekel.ui.DialogsHandler;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class NewWarController implements Initializable {

  private final Scenes scenes = Facade.getInstance().getScenes();
  private String terrain;
  private String simulationMode;
  private BattleHandler battleHandler;
  private final DialogsHandler dialogs = Facade.getInstance().getDialogsHandler();


  @FXML
  private ComboBox<String> comboBoxTerrain;

  @FXML
  private ComboBox<String> comboBoxSimulationMode;

  @FXML
  private Slider simulationSpeedSlider;

  @FXML
  private Label armyOneName;

  @FXML
  private Label armyTwoName;

  @FXML
  private Label commanderUnitsAmountA1;

  @FXML
  private Label commanderUnitsAmountA2;

  @FXML
  private Label infantryUnitsAmountA1;

  @FXML
  private Label infantryUnitsAmountA2;

  @FXML
  private Label rangedUnitsAmountA1;

  @FXML
  private Label rangedUnitsAmountA2;

  @FXML
  private Label cavalryUnitsAmountA1;

  @FXML
  private Label cavalryUnitsAmountA2;

  @FXML
  private Label totalUnitAmountA1;

  @FXML
  private Label totalUnitAmountA2;

  @FXML
  private Label commanderUnitsHealthA1;

  @FXML
  private Label commanderUnitsHealthA2;

  @FXML
  private Label infantryUnitsHealthA1;

  @FXML
  private Label infantryUnitsHealthA2;

  @FXML
  private Label rangedUnitsHealthA1;

  @FXML
  private Label rangedUnitsHealthA2;

  @FXML
  private Label cavalryUnitsHealthA1;

  @FXML
  private Label cavalryUnitsHealthA2;

  @FXML
  private Label totalUnitHealthA1;

  @FXML
  private Label totalUnitHealthA2;


  /**
   * This method initializes the necessary fields and other content
   * when the newWarPage.fxml is loaded.
   *
   * @param url             url
   * @param resourceBundle  url
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.terrain = getTerrainTypes().get(0); //none
    this.simulationMode = getSimulationModes().get(0); //real-time simulation
    this.battleHandler = Facade.getInstance().getBattleHandler();
    if (battleHandler.getArmyOne() != null) armyOneInit();
    if (battleHandler.getArmyTwo() != null) armyTwoInit();

    ObservableList<String> terrainTypes = FXCollections.observableArrayList();
    terrainTypes.addAll(getTerrainTypes());
    comboBoxTerrain.setItems(terrainTypes);

    ObservableList<String> simulationModes = FXCollections.observableArrayList();
    simulationModes.addAll(getSimulationModes());
    comboBoxSimulationMode.setItems(simulationModes);
  }

  /**
   * This method returns a list with the selectable terrain types.
   *
   * @return  A list containing the various terrain types.
   */
  private List<String> getTerrainTypes(){
    List<String> terrainTypes = new ArrayList<>();
    terrainTypes.add("none");
    terrainTypes.add("forest");
    terrainTypes.add("hills");
    terrainTypes.add("plains");
    terrainTypes.add("random");
    return terrainTypes;
  }

  /**
   * This method returns a list with the selectable simulation modes.
   *
   * @return  A list with simulation modes.
   */
  private List<String> getSimulationModes(){
    List<String> simulationModes = new ArrayList<>();
    simulationModes.add("real-time simulation");
    simulationModes.add("instant");
    simulationModes.add("step-by-step");
    return simulationModes;
  }

  /**
   * This method is triggered when the "back to main page" button is pressed, and changes
   * the displayed scene to the main page.
   *
   * @param event         Actionevent: when main page button is clicked
   * @throws IOException  Input/output exception
   */
  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

  /**
   * Method that lets the user select which army to appoint to the first army in the war
   * through a dialog window.
   *
   * @param event When appoint army button is pressed.
   */
  @FXML
  public void appointArmyOneAction(ActionEvent event)  {
    Army returnedArmy = dialogs.appointArmyDialog(true);
    if (returnedArmy != null) {
      if (returnedArmy == battleHandler.getArmyTwo()) {
        dialogs.errorAlert("The opposing armies cannot be the same!");
      } else {
        battleHandler.setArmyOne(returnedArmy);
        armyOneInit();
        //armyOneName.setText(battleHandler.getArmyOne().getName());
      }
    }
  }

  /**
   * Method that lets the user select which army to appoint to the second army in the war
   * through a dialog window.
   *
   * @param event When appoint army button is pressed.
   */
  @FXML
  public void appointArmyTwoAction(ActionEvent event) {
    Army returnedArmy = dialogs.appointArmyDialog(false);
    if (returnedArmy != null) {
      if (returnedArmy == battleHandler.getArmyOne()) {
        dialogs.errorAlert("The opposing armies cannot be the same!");
      } else {
        battleHandler.setArmyTwo(returnedArmy);
        //armyTwoName.setText(battleHandler.getArmyTwo().getName());
        armyTwoInit();
      }
    }
  }

  /**
   * Method that takes the user to the war simulation page IF two armies are appointed.
   *
   * @param event         When start war button is clicked
   * @throws IOException  Input output exception.
   */
  @FXML
  public void startWarAction(ActionEvent event) throws IOException {
    if (battleHandler.getBattleState() == BattleHandler.BattleState.READY) {
      battleHandler.createBattle();
      scenes.loadScene(event, ClassPaths.simpleWarSimulationPageURL);
    } else {
      dialogs.errorAlert("Battle cannot be started.");
    }
  }

  /**
   * Method that initializes the fields for army one in the scene.
   */
  private void armyOneInit(){
    armyOneName.setText(battleHandler.getArmyOne().getName());

    commanderUnitsAmountA1.setText(String.valueOf(battleHandler.getArmyOne().getCommanderUnits().size()));
    infantryUnitsAmountA1.setText(String.valueOf(battleHandler.getArmyOne().getInfantryUnits().size()));
    rangedUnitsAmountA1.setText(String.valueOf(battleHandler.getArmyOne().getRangedUnits().size()));
    cavalryUnitsAmountA1.setText(String.valueOf(battleHandler.getArmyOne().getCavalryUnits().size()));
    totalUnitAmountA1.setText(String.valueOf(battleHandler.getArmyOne().getAllUnits().size()));

    commanderUnitsHealthA1.setText(String.valueOf(battleHandler.getArmyOne().
        getCommanderUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    infantryUnitsHealthA1.setText(String.valueOf(battleHandler.getArmyOne().
        getInfantryUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    rangedUnitsHealthA1.setText(String.valueOf(battleHandler.getArmyOne().
        getRangedUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    cavalryUnitsHealthA1.setText(String.valueOf(battleHandler.getArmyOne().
        getCavalryUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    totalUnitHealthA1.setText(String.valueOf(battleHandler.getArmyOne().
        getAllUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
  }

  /**
   * Method that initializes the fields for army two in the scene.
   */
  private void armyTwoInit(){
    armyTwoName.setText(battleHandler.getArmyTwo().getName());

    commanderUnitsAmountA2.setText(String.valueOf(battleHandler.getArmyTwo().getCommanderUnits().size()));
    infantryUnitsAmountA2.setText(String.valueOf(battleHandler.getArmyTwo().getInfantryUnits().size()));
    rangedUnitsAmountA2.setText(String.valueOf(battleHandler.getArmyTwo().getRangedUnits().size()));
    cavalryUnitsAmountA2.setText(String.valueOf(battleHandler.getArmyTwo().getCavalryUnits().size()));
    totalUnitAmountA2.setText(String.valueOf(battleHandler.getArmyTwo().getAllUnits().size()));

    commanderUnitsHealthA2.setText(String.valueOf(battleHandler.getArmyTwo().
        getCommanderUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    infantryUnitsHealthA2.setText(String.valueOf(battleHandler.getArmyTwo().
        getInfantryUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    rangedUnitsHealthA2.setText(String.valueOf(battleHandler.getArmyTwo().
        getRangedUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    cavalryUnitsHealthA2.setText(String.valueOf(battleHandler.getArmyTwo().
        getCavalryUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
    totalUnitHealthA2.setText(String.valueOf(battleHandler.getArmyTwo().
        getAllUnits().stream().map(Unit::getHealth).reduce(0, Integer::sum)));
  }

  public void updateBattleSettingsAction(ActionEvent event) {
    updateBattleSettings();
  }

  /**+
   * Updates the settings for the simulation (to-be-generated).
   */
  private void updateBattleSettings() {
    String selectedTerrain = comboBoxTerrain.getSelectionModel().getSelectedItem();
    String selectedSimulationMode = comboBoxSimulationMode.getSelectionModel().getSelectedItem();
    if (selectedTerrain != null) {
      this.terrain = selectedTerrain;
    } else {

    }
    if (selectedSimulationMode != null) {
      this.simulationMode = selectedSimulationMode;

    }
  }
}
