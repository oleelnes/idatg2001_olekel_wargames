package no.ntnu.olekel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class NewWarController implements Initializable {

  private Scenes scenes = Facade.getInstance().getScenes();
  private List<String> terrain;

  @FXML
  private ComboBox<String> comboBoxTerrain;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.terrain = new ArrayList<>();
    this.terrain.addAll(getTerrainTypes());
    ObservableList<String> terrainTypes = FXCollections.observableArrayList();
    terrainTypes.addAll(terrain);
    comboBoxTerrain.setItems(terrainTypes);
  }

  private List<String> getTerrainTypes(){
    List<String> terrainTypes = new ArrayList<>();
    terrainTypes.add("Forest");
    terrainTypes.add("Hills");
    terrainTypes.add("Plains"); //todo: more terrains?
    return  terrainTypes;
  }


  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

  public void appointArmyOneAction(ActionEvent event) {

  }
}
