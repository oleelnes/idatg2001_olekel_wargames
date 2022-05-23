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
import no.ntnu.olekel.core.EnumHandler;
import no.ntnu.olekel.ui.DialogsHandler;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The controller class for the view armies fxml scene.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class ViewArmiesController implements Initializable {

  private Scenes scenes = Facade.getInstance().getScenes();
  private DialogsHandler dialogs = Facade.getInstance().getDialogsHandler();

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

  @FXML
  private TableColumn<Army, String> filePathColumn;

  @FXML
  private TableColumn<Army, Integer> totalUnitsColumn;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    ObservableList<Army> armyObservableList = FXCollections.observableArrayList();
    armyObservableList.addAll(Facade.getInstance().getArmyRegister().getArmyRegister());
    try {
      tableView.setItems(armyObservableList);
      armyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      armyNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

      commanderUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("commanderSize"));
      commanderUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      cavalryUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("cavalrySize"));
      cavalryUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      infantryUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("infantrySize"));
      infantryUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      rangedUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("rangedSize"));
      rangedUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      totalUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
      totalUnitsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

      Facade.getInstance().getArmyRegister().update();

      healthColumn.setCellValueFactory(new PropertyValueFactory<>("armyHealthPercentage"));
      healthColumn.setCellFactory(TextFieldTableCell.forTableColumn());

      filePathColumn.setCellValueFactory(new PropertyValueFactory<>("filePath"));
      filePathColumn.setCellFactory(TextFieldTableCell.forTableColumn());

      tableView.setEditable(false);
    } catch(NullPointerException e) {
      dialogs.errorAlert("Army list is empty.");
    }
  }

  @FXML
  public void mainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);

  }

  public void editArmyAction(ActionEvent event) throws IOException {
    Army selectedArmy = tableView.getSelectionModel().getSelectedItem();
    if (selectedArmy != null) {
      Facade.getInstance().setArmy(selectedArmy);
      Facade.getInstance().setState(EnumHandler.State.EDIT);
      scenes.loadScene(event, ClassPaths.createArmyPageURL);
    } else {
      dialogs.errorAlert("No army selected!");
    }
  }

  /**
   * This army replenishes the selected army by reloading it from the file it was stored to.
   *
   * @param event
   */
  public void replenishArmyAction(ActionEvent event) throws IOException {
    Army selectedArmy = tableView.getSelectionModel().getSelectedItem();
    if (selectedArmy != null) {
      Facade.getInstance().getFileHandler().replenishArmy(selectedArmy, Path.of(selectedArmy.getFilePath()));
      scenes.loadScene(event, ClassPaths.viewArmiesURL);
    } else {
      dialogs.errorAlert("No army selected.");
    }
  }
}
