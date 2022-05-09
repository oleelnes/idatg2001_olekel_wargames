package no.ntnu.olekel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
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
  private TableColumn<Army, String> healthColumn;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    ObservableList<Army> armyObservableList = FXCollections.observableArrayList();
    armyObservableList.addAll(Facade.getInstance().getArmyRegister().getArmyRegister());
    try {
      tableView.setItems(armyObservableList);
      armyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      armyNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      //armyNameColumn.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).);

      commanderUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("commanderSize"));
      commanderUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      cavalryUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("cavalrySize"));
      cavalryUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      infantryUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("infantrySize"));
      infantryUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      rangedUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("rangedSize"));
      rangedUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      Facade.getInstance().getArmyRegister().update();

      healthColumn.setCellValueFactory(new PropertyValueFactory<>("armyHealthPercentage"));
      healthColumn.setCellFactory(TextFieldTableCell.forTableColumn());

      tableView.setEditable(false);
    } catch(NullPointerException e) {
      System.err.println("Army list is empty"); //will improve exception handling later.
    }
  }

  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

}
