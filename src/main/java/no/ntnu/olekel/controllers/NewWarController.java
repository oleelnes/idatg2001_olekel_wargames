package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;

public class NewWarController {

  Scenes scenes = Facade.getInstance().getScenes();

  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

}
