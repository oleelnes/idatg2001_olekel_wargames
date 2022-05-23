package no.ntnu.olekel.core.units;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitFactoryTest {
  UnitFactory unitFactory;

  @BeforeEach
  public void beforeEach(){
    unitFactory = new UnitFactory();
  }

  @Test
  public void createInfantryUnitTest(){
    Unit unit = unitFactory.createUnit(UnitFactory.Type.INFANTRY, "The name", 12);
    assertEquals(InfantryUnit.class, unit.getClass());
  }

  @Test
  public void infantryUnitNotACavalryUnitTest(){
    Unit unit = unitFactory.createUnit(UnitFactory.Type.INFANTRY, "The name", 12);
    assertNotEquals(CavalryUnit.class, unit.getClass());
  }

  @Test
  public void createCavalryUnitTest(){
    Unit unit = unitFactory.createUnit(UnitFactory.Type.CAVALRY, "The name", 12);
    assertEquals(CavalryUnit.class, unit.getClass());
  }

  @Test
  public void createRangedUnitTest(){
    Unit unit = unitFactory.createUnit(UnitFactory.Type.RANGED, "The name", 12);
    assertEquals(RangedUnit.class, unit.getClass());
  }

  @Test
  public void createInfantryUnitListSizeTest(){
    List<Unit> infantryUnits;
    infantryUnits = unitFactory.createUnitList(UnitFactory.Type.INFANTRY, 12, 100);
    assertEquals(100, infantryUnits.size());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
  public void createInfantryUnitListTypeTest(int number){
    List<Unit> infantryUnits;
    infantryUnits = unitFactory.createUnitList(UnitFactory.Type.INFANTRY, 12, 10);
    assertEquals(InfantryUnit.class, infantryUnits.get(number).getClass());
  }

}
