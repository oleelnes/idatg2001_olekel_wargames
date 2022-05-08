package no.ntnu.olekel;

import no.ntnu.olekel.core.*;
import no.ntnu.olekel.core.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyFileHandlerTest {
    Army army;
    List<Unit> units;

    @BeforeEach
    public void constructArmy(){
        this.units = new ArrayList<Unit>();
        this.units.add(new InfantryUnit("Infantry 1", 12));
        this.units.add(new InfantryUnit("Infantry 2", 20));
        this.units.add(new CavalryUnit("Cavalry 1", 10));
        this.units.add(new CommanderUnit("Commander 1", 30));
        this.army = new Army("test", units);
    }

    @Test
    public void readCSVTest() {
       // Army testArmy = FileHandler.readArmyCSV(Path.of("ArmyOne.csv"));
        //assertNotNull(testArmy);
    }

    @Test
    public void readCSVTest2(){
        //Army testArmy = FileHandler.readArmyCSV(Path.of("ArmyOne.csv"));
        //assertEquals(testArmy.getAllUnits().get(0).getName(), "Infantry Unit 0");
    }

    @Test
    public void writeCSVTest(){
        //FileHandler.writeArmyCSV(army, Path.of("test.csv"));
        //Army testArmy = FileHandler.readArmyCSV(Path.of("test.csv"));
        //assertNotNull(testArmy);
    }

    //todo: assertThrows
    @Test
    public void readCSVTestInvalidType(){
       // Army testArmy = FileHandler.readArmyCSV(Path.of("ererer.csv"));
       // assertEquals(testArmy.getAllUnits().get(0).getName(), "Infantry Unit 0");
    }
}
