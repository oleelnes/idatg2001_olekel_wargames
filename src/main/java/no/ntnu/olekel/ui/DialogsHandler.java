package no.ntnu.olekel.ui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import no.ntnu.olekel.WarGamesApp;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class DialogsHandler {

  public void loadFromFileDialog(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose an army csv file!");
    fileChooser.showOpenDialog(Facade.getInstance().getStage());
  }

}
