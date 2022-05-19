package no.ntnu.olekel.core.units;

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
  InfantryUnit infantryUnit;

  /**
   * Create infantry unit.
   */
  @BeforeEach
  public void createInfantryUnit(){
    infantryUnit = new InfantryUnit("infantry 1", 12);
  }

  /**
   * Get name test.
   */
  @Test
  public void getNameTest(){
    assertEquals("infantry 1", infantryUnit.getName());
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

  /**
   * Get defender bonus test.
   */
  @Test
  public void getDefenderBonusTest(){
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
