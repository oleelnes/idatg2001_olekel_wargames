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
  /**
   * The Infantry unit.
   */
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
    assertEquals(infantryUnit.getName(), "infantry 1");
  }

  /**
   * Get health test.
   */
  @Test
  void getHealthTest(){
    assertEquals(infantryUnit.getHealth(), 12);
  }

  /**
   * Get armor test.
   */
  @Test
  void getArmorTest(){
    assertEquals(infantryUnit.getArmor(), 10);
  }

  /**
   * Get attack test.
   */
  @Test
  void getAttackTest(){
    assertEquals(infantryUnit.getAttack(), 15);
  }

  /**
   * Set health test.
   */
  @Test
  void setHealthTest(){
    infantryUnit.setHealth(100);
    assertEquals(infantryUnit.getHealth(), 100);
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
    assertEquals(infantryUnit.getAttackBonus(), 2);
  }

  /**
   * Get defender bonus test.
   */
  @Test
  void getDefenderBonusTest(){
    assertEquals(infantryUnit.getResistBonus(), 1);
  }
}
