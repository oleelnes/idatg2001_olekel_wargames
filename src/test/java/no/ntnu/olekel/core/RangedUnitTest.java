package no.ntnu.olekel.core;

import no.ntnu.olekel.core.RangedUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Ranged unit test.
 *
 * @author Ole Kristian Elnæs.
 * @version 16.02.2022.
 */
class RangedUnitTest {
  RangedUnit rangedUnit;

  /**
   * Creates a ranged unit.
   */
  @BeforeEach
  void createRangedUnit() {
    rangedUnit = new RangedUnit("ranged", 100);
  }

  /**
   * Get resist bonus test.
   */
  @Test
  void getResistBonusTest() {
    assertEquals(rangedUnit.getResistBonus(), 6);
    assertEquals(rangedUnit.getResistBonus(), 4);
    rangedUnit.getResistBonus();
    assertEquals(rangedUnit.getResistBonus(), 2);
  }

  /**
   * Get attack bonus test.
   */
  @Test
  void getAttackBonusTest() {
    assertEquals(rangedUnit.getAttackBonus(), 3);
  }
}