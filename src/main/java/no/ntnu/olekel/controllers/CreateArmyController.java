package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.units.Unit;
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
  private List<Unit> units;
  String armyName;
  State state;

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



  public enum State {
    NEW,
    EDIT;

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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setAddUnitsContentVisibility(false);
    armyName = ""; //this might work but take a closer look at it.
    units = new ArrayList<>();
    state = State.NEW;
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
      Army newArmy = new Army(armyName);
      units.clear();
      newArmy.getAllUnits().addAll(units);
      Facade.getInstance().getArmyRegister().getArmyRegister().add(newArmy);
      Facade.getInstance().setArmy(newArmy);
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
