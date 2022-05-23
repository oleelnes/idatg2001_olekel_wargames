package no.ntnu.olekel.core.units;

import no.ntnu.olekel.core.EnumHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CavalryUnitTest {
  UnitFactory unitFactory;
  Unit cavalryUnit;

  @BeforeEach
  public void createCavalryUnit() {
    unitFactory = new UnitFactory();
    cavalryUnit = unitFactory.createUnit(UnitFactory.Type.CAVALRY, "name", 100);
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

  @Test
  public void getAttackBonusOnPlainsTest(){
    cavalryUnit.setTerrain(EnumHandler.TerrainTypes.PLAINS);
    assertEquals(9, cavalryUnit.getAttackBonus());
    assertEquals(5, cavalryUnit.getAttackBonus());
  }
}
