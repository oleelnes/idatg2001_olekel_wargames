package no.ntnu.olekel.core.units;


import no.ntnu.olekel.core.Army;
import no.ntnu.olekel.core.EnumHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ForestTest {
  UnitFactory unitFactory = new UnitFactory();
  List<Unit> unitList;
  Unit infantryUnit;
  Unit cavalryUnit;
  Unit rangedUnit;
  Army armyOne;


  /**
   * Create infantry unit.
   */
  @BeforeEach
  public void createInfantryUnit(){
    infantryUnit = unitFactory.createUnit(UnitFactory.Type.INFANTRY, "infantry 1", 12);
    cavalryUnit = unitFactory.createUnit(UnitFactory.Type.CAVALRY, "cav", 10);
    rangedUnit = unitFactory.createUnit(UnitFactory.Type.RANGED, "ran", 10);
    unitList = new ArrayList<>();
    unitList.add(cavalryUnit);
    unitList.add(infantryUnit);
    unitList.add(rangedUnit);
    armyOne = new Army("army", unitList);

  }


  @Test
  public void getAttackBonusInfantry(){
    int attackBonusDefault = infantryUnit.getAttackBonus();
    armyOne.setTerrain(EnumHandler.TerrainTypes.FOREST);
    int attackBonusForest = infantryUnit.getAttackBonus();
    System.out.println("not in forest: " + attackBonusDefault + ", in forest: " + attackBonusForest);
    assertNotEquals(attackBonusDefault, attackBonusForest);
  }


}
