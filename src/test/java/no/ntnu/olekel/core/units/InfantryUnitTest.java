package no.ntnu.olekel.core.units;

import no.ntnu.olekel.core.EnumHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of class InfantryUnit.
 *
 * Of the subclasses of Unit, this is the only one where all methods are tested,
 * as testing all inherited functions in all the subclasses would be redundant.
 *
 * @author Ole Kristian Elnæs
 * @version 16.02.2022
 */
public class InfantryUnitTest {
  UnitFactory unitFactory;
  Unit infantryUnit;

  /**
   * Create infantry unit.
   */
  @BeforeEach
  public void createInfantryUnit(){
    unitFactory = new UnitFactory();
    infantryUnit = unitFactory.createUnit(UnitFactory.Type.INFANTRY, "name", 12);
  }

  /**
   * Get name test.
   */
  @Test
  public void getNameTest(){
    assertEquals("name", infantryUnit.getName());
  }

  /**
   * Get health test.
   */
  @Test
  public void getHealthTest(){
    assertEquals(12, infantryUnit.getHealth());
  }

  /**
   * Get armor test.
   */
  @Test
  public void getArmorTest(){
    assertEquals(10, infantryUnit.getArmor());
  }

  /**
   * Get attack test.
   */
  @Test
  public void getAttackTest(){
    assertEquals(15, infantryUnit.getAttack());
  }

  /**
   * Set health test.
   */
  @Test
  public void setHealthTest(){
    infantryUnit.setHealth(100);
    assertEquals(100, infantryUnit.getHealth());
  }

  /**
   * Get attack bonus test.
   */
  @Test
  @DisplayName("Testing the getAttackBonus method in the class InfantryUnit")
  public void getAttackBonusTest(){
    assertEquals(2, infantryUnit.getAttackBonus());
  }

  @Test
  public void getAttackBonusInForestTest(){
    infantryUnit.setTerrain(EnumHandler.TerrainTypes.FOREST);
    assertEquals(4, infantryUnit.getAttackBonus());
  }

  @Test
  public void getAttackBonusInHillsTest(){
    infantryUnit.setTerrain(EnumHandler.TerrainTypes.HILLS);
    assertEquals(2, infantryUnit.getAttackBonus());
  }

  @Test
  public void getAttackBonusOnPlainsTest(){
    infantryUnit.setTerrain(EnumHandler.TerrainTypes.PLAINS);
    assertEquals(2, infantryUnit.getAttackBonus());
  }

  @Test
  public void getResistBonusInForestTest(){
    infantryUnit.setTerrain(EnumHandler.TerrainTypes.FOREST);
    assertEquals(3, infantryUnit.getResistBonus());
  }

  @Test
  public void getResistBonusInHillsTest(){
    infantryUnit.setTerrain(EnumHandler.TerrainTypes.HILLS);
    assertEquals(1, infantryUnit.getResistBonus());
  }

  @Test
  public void getResistBonusOnPlainsTest(){
    infantryUnit.setTerrain(EnumHandler.TerrainTypes.PLAINS);
    assertEquals(1, infantryUnit.getResistBonus());
  }

  /**
   * Get defender bonus test.
   */
  @Test
  public void getResistBonusTest(){
    assertEquals(1, infantryUnit.getResistBonus());
  }

  /**
   *
   */
  @Test
  public void attackTest(){
    InfantryUnit attacker = new InfantryUnit("Attacker unit", 10);
    InfantryUnit defender = new InfantryUnit("Defender unit", 10);
    int beforeHealth = defender.getHealth();
    attacker.attack(defender);
    assertNotEquals(beforeHealth, defender.getHealth());
  }

  /**
   *
   */
  @Test
  public void attackNegativeTest(){
    InfantryUnit attacker = new InfantryUnit("Attacker unit", 10);
    InfantryUnit defender = new InfantryUnit("Defender unit", 10);
    while (defender.getHealth() > 0) {
      attacker.attack(defender);
      System.out.println("health: " + defender.getHealth());
    }
    assertEquals(0, defender.getHealth());
  }

}
