package no.ntnu.olekel.core.units;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CavalryUnitTest {
  CavalryUnit cavalryUnit;

  @BeforeEach
  public void createCavalryUnit() {
    cavalryUnit = new CavalryUnit("cavalry", 100);
  }

  @Test
  public void getAttackBonusTest() {
    assertEquals(cavalryUnit.getAttackBonus(), 6);
    assertEquals(cavalryUnit.getAttackBonus(), 2);
  }

  @Test
  public void getResistBonusTest() {
    assertEquals(cavalryUnit.getResistBonus(), 1);
  }
}
