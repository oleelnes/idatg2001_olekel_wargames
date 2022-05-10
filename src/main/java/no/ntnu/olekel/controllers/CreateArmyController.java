package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author  Ole Kristian Elnæs
 */
public class CreateArmyController implements Initializable {
  Scenes scenes = Facade.getInstance().getScenes();

  Army army;

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
    army = new Army("Temporary");
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
      army.setName(armyNameInput.getText());
      armyNameInput.clear();
      addNewUnitsLabel.setText("Add New Units to " + army.getName());
    } else {
      setAddUnitsContentVisibility(false);
      addNewUnitsLabel.setText("Add New Units");
      //todo: give user information about what went wrong!
    }

  }
}
