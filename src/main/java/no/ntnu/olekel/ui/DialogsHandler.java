package no.ntnu.olekel.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.controllers.CreateArmyController;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class DialogsHandler {

  public void loadUnitsFromFile(Army army){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose an army csv file!");
    File file = fileChooser.showOpenDialog(Facade.getInstance().getStage());
    if (file != null) {
      Facade.getInstance().getFileHandler().loadIntoArmy(FileHandler.RegisterType.EDIT_ARMY, file.toPath(), army);
    }
    else {
      System.out.println("yuck!");
    }
  }

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

}
