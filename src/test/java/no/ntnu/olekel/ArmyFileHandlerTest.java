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

}
