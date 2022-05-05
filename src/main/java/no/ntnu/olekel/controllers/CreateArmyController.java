package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author  Ole Kristian Elnæs
 */
public class CreateArmyController {
  Scenes scenes = Facade.getInstance().getScenes();

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
}
