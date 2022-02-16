package no.ntnu.olekel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The class Battle test.
 *
 * @author  Ole Kristian Elnæs
 * @version 16.02.2022
 */
public class BattleTest {
    /**
     * The Battle.
     */
    Battle battle;
    /**
     * The Army one.
     */
    Army armyOne;
    /**
     * The Army two.
     */
    Army armyTwo;
    /**
     * The Units a 1.
     */
    ArrayList<Unit> unitsA1;
    /**
     * The Units a 2.
     */
    ArrayList<Unit> unitsA2;


    /**
     * Create armies.
     */
    @BeforeEach
    void createArmies() {
        unitsA1 = new ArrayList<>();
        unitsA2 = new ArrayList<>();
        unitsA1.add(new InfantryUnit("A1 infantry 1", 100));
        unitsA1.add(new InfantryUnit("A1 infantry 2", 100));
        unitsA1.add(new InfantryUnit("A1 infantry 3", 100));
        unitsA2.add(new InfantryUnit("A2 infantry 1", 150));
        unitsA2.add(new InfantryUnit("A2 infantry 2", 120));

        armyOne = new Army("army one", unitsA1);
        armyTwo = new Army("army two", unitsA2);

        battle = new Battle(armyOne, armyTwo);
    }

    /**
     * Simulate test.
     */
    @Test
    void simulateTest(){
        battle.simulate();
    }

}
