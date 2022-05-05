package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;

public class MainPageController {

  private final Scenes scenes = Facade.getInstance().getScenes();

  @FXML
  private void newWarAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.newWarPageURL);
  }

  @FXML
  public void viewArmies(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.viewArmiesURL);
  }

  @FXML
  public void newArmyAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.createArmyPageURL);
  }
}
