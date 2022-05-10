package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author  Ole Kristian Elnæs
 */
public class CreateArmyController implements Initializable {
  Scenes scenes = Facade.getInstance().getScenes();

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
  private Button commanderUnitsButton;

  @FXML
  private Button cavalryUnitsButton;

  @FXML
  private Button infantryUnitsButton;

  @FXML
  private Button rangedUnitsButton;


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
    setCreateUnitVisibility(false);
  }

  private void setCreateUnitVisibility(boolean visibility){
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

  @FXML
  public void applyArmyNameAction(ActionEvent event) {
    setCreateUnitVisibility(true);
  }
}
