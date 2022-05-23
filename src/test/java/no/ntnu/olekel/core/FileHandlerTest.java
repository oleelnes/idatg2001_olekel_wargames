package no.ntnu.olekel.core;

import no.ntnu.olekel.constants.Constants;
import no.ntnu.olekel.core.units.InfantryUnit;
import no.ntnu.olekel.core.units.Unit;
import no.ntnu.olekel.core.units.UnitFactory;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileHandlerTest {
  ArmyRegister armyRegister;
  BattleRegister battleRegister;
  FileHandler fileHandler;
  UnitFactory unitFactory;
  Army army;

  @BeforeEach
  public void beforeEach() {
    armyRegister = new ArmyRegister();
    battleRegister = new BattleRegister();
    fileHandler = new FileHandler(armyRegister, battleRegister);
    unitFactory = new UnitFactory();
    army = new Army("The army");
    army.addAll(unitFactory.createUnitList(UnitFactory.Type.INFANTRY, 12, 12));
  }

  @Test
  public void addArmyToFile() {
    try {
      fileHandler.saveArmyToFile(army);
      fileHandler.load(FileHandler.RegisterType.ARMIES, Path.of(Constants.ARMIES_FOLDER_PATH + "/" + army.getName() + ".csv"));
      assertEquals(1, armyRegister.getArmyRegister().size());
      fileHandler.deleteArmy(Path.of(Constants.ARMIES_FOLDER_PATH + "/" + army.getName() + ".csv"));
    } catch(Exception e) {
      fail();
    }
  }

}
