package no.ntnu.olekel.ui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import no.ntnu.olekel.WarGamesApp;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.FileHandler;

import java.io.File;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class DialogsHandler {

  public void loadFromFileDialog(Army army){
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

}
