package no.ntnu.olekel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.units.Unit;
import no.ntnu.olekel.core.units.UnitFactory;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author  Ole Kristian Elnæs
 */
public class CreateArmyController implements Initializable {
  private Scenes scenes = Facade.getInstance().getScenes();
  private Army army = Facade.getInstance().getArmy();
  private UnitFactory unitFactory = new UnitFactory();
  private Army newArmy;
  private List<Unit> units;
  private List<Text> listContent;
  private String armyName;
  private State state;

  @FXML
  private TextField commanderUnitsAmountInput;

  @FXML
  private TextField cavalryUnitsAmountInput;

  @FXML
  private TextField infantryUnitsAmountInput;

  @FXML
  private TextField rangedUnitsAmountInput;

  @FXML
  private TextField commanderUnitsHealthInput;

  @FXML
  private TextField cavalryUnitsHealthInput;

  @FXML
  private TextField infantryUnitsHealthInput;

  @FXML
  private TextField rangedUnitsHealthInput;

  @FXML
  private Label commanderUnitsAmountLabel;

  @FXML
  private Label cavalryUnitsAmountLabel;

  @FXML
  private Label infantryUnitsAmountLabel;

  @FXML
  private Label rangedUnitsAmountLabel;

  @FXML
  private Label commanderUnitsHealthLabel;

  @FXML
  private Label cavalryUnitsHealthLabel;

  @FXML
  private Label infantryUnitsHealthLabel;

  @FXML
  private Label rangedUnitsHealthLabel;

  @FXML
  private Label addNewUnitsLabel;

  @FXML
  private Button commanderUnitsButton;

  @FXML
  private Button cavalryUnitsButton;

  @FXML
  private Button infantryUnitsButton;

  @FXML
  private Button rangedUnitsButton;

  @FXML
  private TextField armyNameInput;

  @FXML
  private ListView<Text> existingUnitsListView;

  /**
   * This method initializes the necessary fields and other content
   * when the createArmyPage.fxml is loaded.
   *
   * @param url             url
   * @param resourceBundle  url
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setAddUnitsContentVisibility(false);
    this.armyName = ""; //this might work but take a closer look at it.
    this.units = new ArrayList<>();
    this.state = State.NEW;
    this.listContent = new ArrayList<>();
    this.newArmy = new Army("");

    ObservableList<Text> stringObservableList = FXCollections.observableArrayList(listContent);
    existingUnitsListView.setItems(stringObservableList);
  }

  /**
   * Method that opens a filechooser for the user to select a file from, from which
   * units will be imported into the existing army file, given that the content of the file
   * is in line with the requirements for an army file.
   *
   * @param event When "import units from file" button is clicked.
   */
  @FXML
  public void loadFromFileAction(ActionEvent event) {
    if (!army.getAllUnits().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "The units of the army file selected will be added to this army");
      alert.setTitle("Please confirm");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.isEmpty()){
        alert.close();
      } else if (result.get() == ButtonType.OK) {
        if(state == State.NEW)
          Facade.getInstance().getDialogsHandler().loadUnitsFromFile(newArmy);
        else if (state == State.EDIT)
          Facade.getInstance().getDialogsHandler().loadUnitsFromFile(army);
        updateListContent();
      } else if (result.get() == ButtonType.CANCEL) {
        alert.close();
      }
    } else {
      if(state == State.NEW)
        Facade.getInstance().getDialogsHandler().loadUnitsFromFile(newArmy);
      else if (state == State.EDIT)
        Facade.getInstance().getDialogsHandler().loadUnitsFromFile(army);
      updateListContent();
    }

  }

  /**
   * The state of the army that is being created. It is "NEW" if it has not been
   * added to the register yet; it is "EDIT" if it has.
   */
  public enum State {
    NEW,
    EDIT;
  }

  /**
   * Method that adds units to the army being created according to user input.
   *
   * @param event When the "add" button for commander units has been pressed.
   */
  @FXML
  public void addCommanderUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.COMMANDER, Integer.parseInt(commanderUnitsAmountInput.getText()),
        Integer.parseInt(commanderUnitsHealthInput.getText()));
  }

  /**
   * Method that adds units to the army being created according to user input.
   *
   * @param event When the "add" button for infantry units has been pressed.
   */
  @FXML
  public void addInfantryUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.INFANTRY, Integer.parseInt(infantryUnitsAmountInput.getText()),
        Integer.parseInt(infantryUnitsHealthInput.getText()));
  }

  /**
   * Method that adds units to the army being created according to user input.
   *
   * @param event When the "add" button for ranged units has been pressed.
   */
  @FXML
  public void addRangedUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.RANGED, Integer.parseInt(rangedUnitsAmountInput.getText()),
        Integer.parseInt(rangedUnitsHealthInput.getText()));
  }

  /**
   * Method that adds units to the army being created according to user input.
   *
   * @param event When the "add" button for cavalry units has been pressed.
   */
  @FXML
  public void addCavalryUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.CAVALRY, Integer.parseInt(cavalryUnitsAmountInput.getText()),
        Integer.parseInt(cavalryUnitsHealthInput.getText()));
  }

  /**
   * TODO:
   * @return true or false depending on whether the input is valid.
   */
  private boolean checkDigit(){
    return true;
  }

  /**
   * This method adds units to the army that is being created, depending on
   * parameters that is passed after being inputted by the user.
   *
   * @param unitType  The type of unit (Commander, infantry, etc.)
   * @param amount    The amount of units to add.
   * @param health    The health of the units to add.
   */
  private void addUnits(UnitFactory.Type unitType, int amount, int health){
    newArmy.addUnitList(unitFactory.createUnitList(unitType, "name", health, amount));
    updateListContent();
  }

  /**
   * This method updates the contents of the existingUnitsListView ListView, which displays the
   * currently existing units in the army that is being created.
   */
  private void updateListContent(){
    listContent.clear();
    if(state == State.EDIT) setListContent(army);
    else setListContent(newArmy);
    ObservableList<Text> stringObservableList = FXCollections.observableArrayList(listContent);
    existingUnitsListView.setItems(stringObservableList);
  }

  /**
   * This method updates the listContent ArrayList which contain all the units that exist in the
   * army that is being created. The listContent ArrayList is being used by the
   * existingUnitsListView ListView to display the currently existing units.
   *
   * @param listArmy  Which army to fetch units from.
   */
  private void setListContent(Army listArmy){
    for (int i = 0; i < listArmy.getAllUnits().size(); i++) {
      Text text = new Text(listArmy.getAllUnits().get(i).getType() + ": " + listArmy.getAllUnits().get(i).toString());
      text.setWrappingWidth(existingUnitsListView.getWidth());
      listContent.add(text);
    }
  }

  /**
   * Method that loads the main page through the loadScene() method in Scenes.
   *
   * @param event         When back to main page button is pressed.
   * @throws IOException  Input/output exception.
   */
  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

  /**
   * This method sets the visibility of the buttons that are part of
   * "add units to [army name]"
   *
   * @param visibility  Boolean variable that decides whether the buttons that are part of
   *                    "add units to [army name]" should be visible.
   */
  private void setAddUnitsContentVisibility(boolean visibility){
    commanderUnitsAmountInput.setVisible(visibility);
    cavalryUnitsAmountInput.setVisible(visibility);
    infantryUnitsAmountInput.setVisible(visibility);
    rangedUnitsAmountInput.setVisible(visibility);

    commanderUnitsHealthInput.setVisible(visibility);
    cavalryUnitsHealthInput.setVisible(visibility);
    infantryUnitsHealthInput.setVisible(visibility);
    rangedUnitsHealthInput.setVisible(visibility);

    commanderUnitsAmountLabel.setVisible(visibility);
    cavalryUnitsAmountLabel.setVisible(visibility);
    infantryUnitsAmountLabel.setVisible(visibility);
    rangedUnitsAmountLabel.setVisible(visibility);

    commanderUnitsHealthLabel.setVisible(visibility);
    cavalryUnitsHealthLabel.setVisible(visibility);
    infantryUnitsHealthLabel.setVisible(visibility);
    rangedUnitsHealthLabel.setVisible(visibility);

    commanderUnitsButton.setVisible(visibility);
    cavalryUnitsButton.setVisible(visibility);
    infantryUnitsButton.setVisible(visibility);
    rangedUnitsButton.setVisible(visibility);
  }

  /**
   * Method that checks whether user input for army name is valid/unique
   * when "Apply" button in createArmyPage.fxml is clicked, and hides or shows
   * content under "Add New Units" depending on validity of the input.
   *
   * @param event ActionEvent that is triggered when button is clicked.
   */
  @FXML
  public void applyArmyNameAction(ActionEvent event) {
    if(Facade.getInstance().getArmyRegister().getArmyRegister().stream()
        .map(a -> a.getName().toLowerCase(Locale.ROOT))
        .noneMatch(armyNameInput.getText().toLowerCase(Locale.ROOT)::equals)
        && !armyNameInput.getText().isEmpty() && armyNameInput.getText().length() < 30) {
      setAddUnitsContentVisibility(true);
      armyName = armyNameInput.getText();
      addNewUnitsLabel.setText("Add New Units to " + armyName);
      armyNameInput.clear();
      if (state == State.EDIT) Facade.getInstance().getArmy().setName(armyName);
    } else if (!armyName.equals("Temporary")) { //?
      //dialog window opens!!!
    }
    else {
      setAddUnitsContentVisibility(false);
      addNewUnitsLabel.setText("Add New Units");
      //todo: give user information about what went wrong!
    }
  }

  /**
   * This method saves the army that is being created/edited to the army register.
   *
   * @param event When "save army" button is pressed.
   */
  @FXML
  public void saveArmyAction(ActionEvent event) {
    switch(state) {
      case NEW -> newArmy();
      case EDIT -> editArmy();
      default -> System.err.println("not valid input"); //todo: logger
    }
  }

  /**
   * This method saves an army that has not yet been saved to the
   * facade (ui/Facade.java) to the facade and the army register.
   */
  private void newArmy(){
    if (!armyName.equals("")) {
      newArmy.setName(armyName);
      units.clear();
      newArmy.addUnitList(units);
      Facade.getInstance().getArmyRegister().getArmyRegister().add(newArmy);
      Facade.getInstance().setArmy(newArmy);
      army = Facade.getInstance().getArmy();
      setState(State.EDIT); //state is set to EDIT,
      // as the army is now created and set to be the tournament present in the Facade
    } else {
      System.err.println("Please enter a name for the army!");
      //todo: alert window!
    }
    // in viewArmies, when edit is pressed, also set Facade army to be the selected army.
  }

  /**
   * This method saves an army that already is stored as the army in the facade (ui/Facade.java)
   * to the army register.
   */
  private void editArmy(){
    if (Facade.getInstance().getArmyRegister().getArmyRegister().stream()
        .map(a -> a.getName().toLowerCase(Locale.ROOT))
        .anyMatch(army.getName().toLowerCase(Locale.ROOT)::equals)) {
      Facade.getInstance().getArmyRegister().getArmyRegister().remove(army);
    }
    //army.addUnitList(newArmy.getAllUnits());
    Facade.getInstance().getArmyRegister().getArmyRegister().add(army);
  }

  /**
   * This method changes the state of the army --> whether or not it has been stored as the army instance
   * in the facade (ui/Facade.java).
   *
   * @param state The state of the army being created ("NEW" or "EDIT")
   */
  public void setState(State state) {
    this.state = state;
    updateName();
  }

  /**
   * This method updates the name of the army being created.
   */
  private void updateName(){
    switch (state) {
      case NEW -> armyName = "";
      case EDIT -> armyName = Facade.getInstance().getArmy().getName();
      default -> System.err.println("not valid input"); //todo: logger.
    }
  }

}
