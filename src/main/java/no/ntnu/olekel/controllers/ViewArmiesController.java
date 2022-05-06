package no.ntnu.olekel.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class ViewArmiesController implements Initializable {

  Scenes scenes = Facade.getInstance().getScenes();

  @FXML
  private TableView<Army> tableView;

  @FXML
  private TableColumn<Army, String> armyNameColumn;

  @FXML
  private TableColumn<Army, Integer> commanderUnitsColumn;

  @FXML
  private TableColumn<Army, Integer> rangedUnitsColumn;

  @FXML
  private TableColumn<Army, Integer> infantryUnitsColumn;

  @FXML
  private TableColumn<Army, Integer> cavalryUnitsColumn;

  @FXML
  private TableColumn<Army, Integer> healthColumn;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

}
