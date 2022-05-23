package no.ntnu.olekel.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.FileChooser;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * This class handles GUI dialog windows.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class DialogsHandler {

  /**
   * Method that opens a filechooser dialog window that prompts the user to
   * select an army file. This army file will be used to load its units
   * into another army.
   *
   * @param army  The army into which the units from the selected army file will be loaded.
   */
  public void loadUnitsFromFile(Army army){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose an army csv file!");
    File file = fileChooser.showOpenDialog(Facade.getInstance().getStage());
    if (file != null) {
      Facade.getInstance().getFileHandler().loadIntoArmy(FileHandler.RegisterType.EDIT_ARMY, file.toPath(), army);
    }
    else {
      errorAlert("Could not load units from this file.");
    }
  }

  /**
   * Method that opens a filechooser and prompts the user to select an army file
   * to add to the army register of the program.
   *
   * @param event         When the "add army from file" button is clicked.
   * @throws IOException  Exception.
   */
  public void addArmyFromFileDialog(ActionEvent event) throws IOException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose an army csv file!");
    File file = fileChooser.showOpenDialog(Facade.getInstance().getStage());
    if (file != null) {

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to add the army from file "
      + file.getName() + " to the army register?");
      alert.setTitle("Please confirm!");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.isEmpty()){
        alert.close();
      } else if (result.get() == ButtonType.OK) {
        Facade.getInstance().getFileHandler().load(FileHandler.RegisterType.ARMIES, file.toPath());
        Facade.getInstance().getScenes().loadScene(event, ClassPaths.viewArmiesURL);
      } else if (result.get() == ButtonType.CANCEL) {
        alert.close();
      }
    }
    else {
      System.out.println("yuck!");
    }
  }

  /**
   * Method that opens a dialog window with a choice box from which
   * the user is to select an army to appoint to the war they are
   * currently creating.
   *
   * @param armyOne Whether the army to appoint is army one or not.
   * @return        The selected army.
   */
  public Army appointArmyDialog(boolean armyOne) {
    ChoiceDialog<Army> armyChoiceDialog = new ChoiceDialog<>(Facade.getInstance().getArmyRegister().getArmyRegister().get(0),
        Facade.getInstance().getArmyRegister().getArmyRegister());
    armyChoiceDialog.setTitle("Army selection");
    armyChoiceDialog.setHeaderText("Select army to appoint!");
    Optional<Army> result = armyChoiceDialog.showAndWait();
    if (result.isPresent()) {
      return armyChoiceDialog.getSelectedItem();
     // Facade.getInstance().getBattleHandler().setArmyOne(armyChoiceDialog.getSelectedItem());
      //armyOneName.setText(Facade.getInstance().getBattleHandler().getArmyOne().getName());
    } else {
      armyChoiceDialog.close();
      return null;
    }
  }

  /**
   * Method that opens an error alert.
   *
   * @param errorText The text of the error alert.
   */
  public void errorAlert(String errorText) {
    Alert alert = new Alert(Alert.AlertType.ERROR, errorText);
    alert.setTitle("ERROR");
    alert.showAndWait();
  }

}
