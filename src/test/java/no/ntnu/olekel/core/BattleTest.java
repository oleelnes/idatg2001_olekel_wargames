package no.ntnu.olekel.core;

import no.ntnu.olekel.core.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Battle test.
 *
 * @author Ole Kristian Elnæs
 * @version 02.03.2022
 */
public class BattleTest {
  Battle battle;
  Army armyOne;
  Army armyTwo;
  List<Unit> unitsA1;
  List<Unit> unitsA2;

  /**
   * Create armies and fill them with various units.
   */
  @BeforeEach
  void createArmies() {
    unitsA1 = new ArrayList<>();
    unitsA2 = new ArrayList<>();

    unitsA1.add(new CommanderUnit("A1 commander", 200));
    unitsA1.add(new InfantryUnit("A1 infantry 1", 100));
    unitsA1.add(new InfantryUnit("A1 infantry 2", 100));
    unitsA1.add(new InfantryUnit("A1 infantry 3", 100));
    unitsA1.add(new CavalryUnit("A1 cavalry 1", 100));
    unitsA1.add(new RangedUnit("A1 ranged 1", 100));
    unitsA1.add(new RangedUnit("A1 ranged 2", 200));

    unitsA2.add(new CommanderUnit("A2 Commander", 200));
    unitsA2.add(new InfantryUnit("A2 infantry 1", 150));
    unitsA2.add(new InfantryUnit("A2 infantry 2", 200));
    unitsA2.add(new CavalryUnit("A2 cavalry 1", 100));
    unitsA2.add(new CavalryUnit("A2 cavalry 2", 100));

    armyOne = new Army("army one", unitsA1);
    armyTwo = new Army("army two", unitsA2);

    battle = new Battle(armyOne, armyTwo);
  }

  /**
   * Simulate test.
   */
  @Test
  void simulateTest() {
    Army winningArmy = battle.simulate();
    assertTrue(winningArmy.hasUnits());
  }

}
