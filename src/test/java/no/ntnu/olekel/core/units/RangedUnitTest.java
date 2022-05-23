package no.ntnu.olekel.core.units;

import no.ntnu.olekel.core.EnumHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Ranged unit test.
 *
 * @author Ole Kristian Elnæs.
 * @version 16.02.2022.
 */
public class RangedUnitTest {
  Unit rangedUnit;
  UnitFactory unitFactory;

  /**
   * Creates a ranged unit.
   */
  @BeforeEach
  public void createRangedUnit() {
    unitFactory = new UnitFactory();
    rangedUnit = unitFactory.createUnit(UnitFactory.Type.RANGED, "Name", 10);
  }

  /**
   * Get resist bonus test.
   */
  @Test
  public void getResistBonusTest() {
    assertEquals(6, rangedUnit.getResistBonus());
    assertEquals(4, rangedUnit.getResistBonus());
    rangedUnit.getResistBonus();
    assertEquals(2, rangedUnit.getResistBonus());
  }

  /**
   * Get attack bonus test.
   */
  @Test
  public void getAttackBonusTest() {
    assertEquals(3, rangedUnit.getAttackBonus());
  }

  @Test
  public void getAttackBonusInForestTest(){
    rangedUnit.setTerrain(EnumHandler.TerrainTypes.FOREST);
    assertEquals(4, rangedUnit.getAttackBonus());
  }

  @Test
  public void getAttackBonusInHillsTest(){
    rangedUnit.setTerrain(EnumHandler.TerrainTypes.HILLS);
    assertEquals(5, rangedUnit.getAttackBonus());
  }

}