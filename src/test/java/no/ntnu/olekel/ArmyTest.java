package no.ntnu.olekel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Army test.
 *
 * @author Ole Kristian Elnæs
 * @version 16.02.2022
 */
public class ArmyTest {
    Army army;
    List<Unit> units;

    /**
     * Create army before every test.
     */
    @BeforeEach
    void createArmy() {
        this.units = new ArrayList<Unit>();
        units.add(new InfantryUnit("Infantry 1", 12));
        units.add(new InfantryUnit("Infantry 2", 20));
        this.army = new Army("test", units);
    }

    /**
     * Remove one unit test.
     */
    @Test
    public void removeOneUnitTest() {
        int after = army.getAllUnits().size() - 1;
        army.remove(army.getRandom());
        assertEquals(army.getAllUnits().size(), after);
    }

    /**
     * Get random unit test.
     */
    @Test
    void getRandomUnitTest(){
        assertNotNull(army.getRandom());
    }

    /**
     * Get name test.
     */
    @Test
    void getNameTest(){
        assertEquals(army.getName(), "test");
    }

    /**
     * Add all test.
     */
    @Test
    void addAllTest(){
        ArrayList<Unit> newUnits = new ArrayList();
        newUnits.add(new InfantryUnit("3", 11));
        newUnits.add(new InfantryUnit("4", 23));
        army.addAll(newUnits);
        assertEquals(army.getAllUnits().size(), 4);
    }

    /**
     * Has units test.
     */
    @Test
    void hasUnitsTest(){
        assertTrue(army.hasUnits());
    }

    /**
     * Get all units test.
     */
    @Test
    void getAllUnitsTest(){
        ArrayList<Unit> getTest = new ArrayList<>();
        getTest.addAll(army.getAllUnits());
        assertEquals(getTest.size(), units.size());
    }

    @Test
    void addTest() {
        InfantryUnit iUnit = new InfantryUnit("infantry unit test", 10);
        army.add(iUnit);
        String name;
        assertEquals("infantry unit test", army.getAllUnits().get(army.getAllUnits().size() - 1).getName());
    }

}
