package no.ntnu.olekel.core.units;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class for the CommanderUnit class.
 *
 * @author Ole Kristian Elnæs
 * @version 01.03.2022
 */
public class CommanderUnitTest {
  CommanderUnit commanderUnit;

  /**
   * Constructs a commander unit before each test.
   */
  @BeforeEach
  public void constructCommanderUnit() {
    commanderUnit = new CommanderUnit("commander unit", 100);
  }

  /**
   * getAttack method test.
   */
  @Test
  public void getAttackTest() {
    assertEquals(20, commanderUnit.getAttack());
  }

  @Test
  public void getAttackBonusTest() {
    assertEquals(6, commanderUnit.getAttackBonus());
    assertEquals(2, commanderUnit.getAttackBonus());
  }

  /**
   * getArmor method test.
   */
  @Test
  public void getArmorTest() {
    assertEquals(12, commanderUnit.getArmor());
  }

  /**
   * getArmor negative test.
   */
  @Test
  public void getArmorNegativeTest() {
    assertNotEquals(100, commanderUnit.getArmor());
  }
}
