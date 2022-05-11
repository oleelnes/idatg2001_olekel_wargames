package no.ntnu.olekel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.units.Unit;
import no.ntnu.olekel.core.units.UnitFactory;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author  Ole Kristian Elnæs
 */
public class CreateArmyController implements Initializable {
  private Scenes scenes = Facade.getInstance().getScenes();
  private Army army = Facade.getInstance().getArmy();
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

  public enum State {
    NEW,
    EDIT;
  }

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
    //updateListContent();
  }

  @FXML
  public void addCommanderUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.COMMANDER, Integer.parseInt(commanderUnitsAmountInput.getText()),
        Integer.parseInt(commanderUnitsHealthInput.getText()));
  }

  @FXML
  public void addInfantryUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.INFANTRY, Integer.parseInt(infantryUnitsAmountInput.getText()),
        Integer.parseInt(infantryUnitsHealthInput.getText()));
  }

  @FXML
  public void addRangedUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.RANGED, Integer.parseInt(rangedUnitsAmountInput.getText()),
        Integer.parseInt(rangedUnitsHealthInput.getText()));
  }

  @FXML
  public void addCavalryUnitsAction(ActionEvent event) {
    addUnits(UnitFactory.Type.CAVALRY, Integer.parseInt(cavalryUnitsAmountInput.getText()),
        Integer.parseInt(cavalryUnitsHealthInput.getText()));
  }

  private boolean checkDigit(){
    return true;
  }

  private void addUnits(UnitFactory.Type unitType, int amount, int health){
    newArmy.addUnitList(Facade.getInstance().getUnitFactory().createUnitList(unitType, "name", health, amount));
    updateListContent();
  }

  private void updateListContent(){
    listContent.clear();
    if(state == State.EDIT) setListContent(army);
    else setListContent(newArmy);
    ObservableList<Text> stringObservableList = FXCollections.observableArrayList(listContent);
    existingUnitsListView.setItems(stringObservableList);
  }

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
   * @param event
   * @throws IOException
   */
  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }


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
    } else if (!armyName.equals("Temporary")) {
      //dialog window opens!!!
    }
    else {
      setAddUnitsContentVisibility(false);
      addNewUnitsLabel.setText("Add New Units");
      //todo: give user information about what went wrong!
    }
  }

  @FXML
  public void saveArmyAction(ActionEvent event) {
    switch(state) {
      case NEW -> newArmy();
      case EDIT -> editArmy();
      default -> System.err.println("not valid input");
    }
  }

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
      //todo: dialog window!
    }
    // in viewArmies, when edit is pressed, also set Facade army to be the selected army.
  }

  private void editArmy(){
    if (Facade.getInstance().getArmyRegister().getArmyRegister().stream()
        .map(a -> a.getName().toLowerCase(Locale.ROOT))
        .anyMatch(army.getName().toLowerCase(Locale.ROOT)::equals)) {
      Facade.getInstance().getArmyRegister().getArmyRegister().remove(army);
    }
    //army.addUnitList(newArmy.getAllUnits());
    Facade.getInstance().getArmyRegister().getArmyRegister().add(army);
  }

  public void setState(State state) {
    this.state = state;
    updateName();
  }

  private void updateName(){
    switch (state) {
      case NEW -> armyName = "";
      case EDIT -> armyName = Facade.getInstance().getArmy().getName();
      default -> System.err.println("not valid input");
    }
  }

}
