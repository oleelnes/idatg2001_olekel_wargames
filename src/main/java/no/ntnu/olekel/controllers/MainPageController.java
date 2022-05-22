package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.EnumHandler;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class MainPageController {

  private final Scenes scenes = Facade.getInstance().getScenes();

  /**
   * This method is triggered when the "new war" button is pressed, and changes
   * the displayed scene to the new war page.
   *
   * @param event         Actionevent: when new war button is clicked
   * @throws IOException  Input/output exception
   */
  @FXML
  private void newWarAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.newWarPageURL);
  }

  /**
   * This method is triggered when the "view armies" button is pressed, and changes
   * the displayed scene to the view armies page.
   *
   * @param event         Actionevent: when view armies button is clicked
   * @throws IOException  Input/output exception
   */
  @FXML
  public void viewArmies(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.viewArmiesURL);
  }

  /**
   * This method is triggered when the "new army" button is pressed, and changes
   * the displayed scene to the new army page.
   *
   * @param event         Actionevent: when new war button is clicked
   * @throws IOException  Input/output exception
   */
  @FXML
  public void newArmyAction(ActionEvent event) throws IOException {
    Facade.getInstance().setState(EnumHandler.State.NEW);
    scenes.loadScene(event, ClassPaths.createArmyPageURL);
  }

  /**
   * todo:
   *
   * @param event         Actionevent: when main page button is clicked
   * @throws IOException  Input/output exception
   */
  @FXML
  public void simpleWarSimulationAction(ActionEvent event) throws IOException {
    //scenes.loadScene(event, ClassPaths.simpleWarSimulationPageURL);
    Facade.getInstance().getDialogsHandler().errorAlert("Not implemented");
  }

  /**
   * This method is triggered when the "add army from file" button is pressed, and changes
   * the displayed scene to the add army from file page.
   *
   * @param event         Actionevent: when add army from file button is clicked
   * @throws IOException  Input/output exception
   */
  @FXML
  public void addArmyFromFileAction(ActionEvent event) throws IOException {
    Facade.getInstance().getDialogsHandler().addArmyFromFileDialog(event);
  }
}
