package no.ntnu.olekel.controllers;


import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.core.Battle;
import no.ntnu.olekel.core.BattleHandler;
import no.ntnu.olekel.ui.Facade;
import no.ntnu.olekel.ui.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadFactory;

public class SimpleWarSimulationPage implements Initializable {
  private final Scenes scenes = Facade.getInstance().getScenes();
  private Battle battle = Facade.getInstance().getBattle();

  private IntegerProperty value = new SimpleIntegerProperty(battle.getArmyOne().getAllUnits().size());

  @FXML
  private Label armyOneUnitsLost;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    armyOneUnitsLost.setText("units: " + Facade.getInstance().getBattleHandler().getArmyOne().getAllUnits().size());
  }


  public void backToMainPageAction(ActionEvent event) throws IOException {
    scenes.loadScene(event, ClassPaths.mainPageURL);
  }

  public void update(){
    armyOneUnitsLost.setText("units: " + Facade.getInstance().getBattleHandler().getArmyOne().getAllUnits().size());

    delay(10);
  }

  private void simulate() throws IOException {
    while (battle.getArmyOne().hasUnits() && battle.getArmyTwo().hasUnits()) {
      battle.simulateOneRound();
      update();


    }
  }

  @FXML
  public void startWarAction(ActionEvent event) throws IOException{
    try {
        Task<Void> task = new Task<Void>() {
          @Override
          protected Void call() {

            while (battle.getArmyOne().hasUnits() && battle.getArmyTwo().hasUnits()) {

              try {
                Thread.sleep(100);
              } catch (InterruptedException ignored) {
              }
              // Update the GUI on the JavaFX Application Thread
              Platform.runLater(() -> battle.simulateOneRound());
              Platform.runLater(() -> value.setValue(battle.getArmyOne().getAllUnits().size()));

            }
            return null;
          }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();


      armyOneUnitsLost.textProperty().bind(value.asString());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void delay(int millisec) {
    try {
      Thread.sleep(millisec);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
  }

}
