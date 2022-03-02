package no.ntnu.olekel;

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
  void createInfantryUnit(){
    infantryUnit = new InfantryUnit("infantry 1", 12);
  }

  /**
   * Get name test.
   */
  @Test
  void getNameTest(){
    assertEquals("infantry 1", infantryUnit.getName());
  }

  /**
   * Get health test.
   */
  @Test
  void getHealthTest(){
    assertEquals(12, infantryUnit.getHealth());
  }

  /**
   * Get armor test.
   */
  @Test
  void getArmorTest(){
    assertEquals(10, infantryUnit.getArmor());
  }

  /**
   * Get attack test.
   */
  @Test
  void getAttackTest(){
    assertEquals(15, infantryUnit.getAttack());
  }

  /**
   * Set health test.
   */
  @Test
  void setHealthTest(){
    infantryUnit.setHealth(100);
    assertEquals(100, infantryUnit.getHealth());
  }

  /**
   * Testing that exception is thrown when trying to set an out-of-range
   * health value.
   */
  @Test
  void setHealthNegativeTest() {
    IllegalArgumentException thrown =
            assertThrows(IllegalArgumentException.class, () -> {
              infantryUnit.setHealth(-10);
            });
    assertEquals("illegal health: (health should not be less than 0)",
            thrown.getMessage());
  }

  /**
   * Get attack bonus test.
   */
  @Test
  @DisplayName("Testing the getAttackBonus method in the class InfantryUnit")
  void getAttackBonusTest(){
    assertEquals(2, infantryUnit.getAttackBonus());
  }

  /**
   * Get defender bonus test.
   */
  @Test
  void getDefenderBonusTest(){
    assertEquals(1, infantryUnit.getResistBonus());
  }

  /**
   *
   */
  @Test
  void attackTest(){
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
  void attackNegativeTest(){
    InfantryUnit attacker = new InfantryUnit("Attacker unit", 10);
    InfantryUnit defender = new InfantryUnit("Defender unit", 10);
    while (defender.getHealth() > 0) {
      attacker.attack(defender);
      System.out.println("health: " + defender.getHealth());
    }
    assertEquals(0, defender.getHealth());
  }

}
