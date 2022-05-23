package no.ntnu.olekel.core;

import no.ntnu.olekel.ui.Facade;

import java.util.Random;
import java.util.logging.Logger;

/**
 * The purpose of this class is to hold vital information about a battle that is to be held.
 * When the user attempts to start a battle, whether it starts depends upon the state of the battleState
 * enum (enum of BattleState).
 *
 * This class manages the handling of a battle by storing information about a battle before it is held,
 * and by starting it when the battle is ready (i.e. there are two armies set).
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class BattleHandler {
  private final Logger logger;
  private BattleState battleState;
  private Battle battle;
  private Army armyOne;
  private Army armyTwo;

  public BattleHandler(){
    this.battleState = BattleState.NO_ARMIES;
    this.logger = Logger.getLogger(this.getClass().toString());
  }

  /**
   * An enum that holds information about the state of the battle to be held.
   */
  public enum BattleState {
    NO_ARMIES,
    ONE_ARMY,
    READY,
    CREATED
  }

  public Battle getBattle() {
    if (battleState == BattleState.READY)
      return battle;
    else return null;
  }

  /**
   * This method will create
   */
  public String createBattle(String battleName){
    if (battleState == BattleState.READY
        && armyOne != null && armyTwo != null) {
      this.battle = new Battle(armyOne, armyTwo);
      this.battle.setName(battleName);
      Facade.getInstance().setBattle(battle);
      battleState = BattleState.CREATED;
      return "The battle was successfully created";
    }
    return "The battle is not ready to be started";
  }


  /**
   * Method that returns army one.
   *
   * @return  Army one or null.
   */
  public Army getArmyOne() {
    try {
      return armyOne;
    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * Method that sets army one andalters the battleState variable.
   *
   * @param armyOne The army to set as armyOne.
   */
  public void setArmyOne(Army armyOne) {
    if (battleState == BattleState.NO_ARMIES) battleState = BattleState.ONE_ARMY;
    else if (armyTwo != null) battleState = BattleState.READY;
    this.armyOne = armyOne;
  }

  /**
   * Method that returns army two.
   *
   * @return  Army two or null.
   */
  public Army getArmyTwo() {
    try {
      return armyTwo;
    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * Method that sets army two and alters the battleState variable.
   *
   * @param armyTwo The army to set as armyTwo.
   */
  public void setArmyTwo(Army armyTwo) {
    if (battleState == BattleState.NO_ARMIES) battleState = BattleState.ONE_ARMY;
    else if (armyOne != null) battleState = BattleState.READY;
    this.armyTwo = armyTwo;
  }

  /**
   * Method that returns the battleState.
   *
   * @return  the battleState.
   */
  public BattleState getBattleState(){
    return battleState;
  }
}
