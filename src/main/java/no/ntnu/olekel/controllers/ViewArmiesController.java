package no.ntnu.olekel.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import no.ntnu.olekel.core.Army;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewArmiesController implements Initializable {

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

}
