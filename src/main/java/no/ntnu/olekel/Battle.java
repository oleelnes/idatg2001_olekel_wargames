package no.ntnu.olekel;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class Battle.
 *
 * @author Ole Kristian Elnæs
 * @version 16.02.2022
 */
public class Battle {
    private Army armyOne;
    private Army armyTwo;
    private Logger logger;
    private Random random;

    /**
     * Instantiates a new Battle.
     *
     * @param armyOne the army one
     * @param armyTwo the army two
     */
    Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        this.logger = Logger.getLogger(this.getClass().toString());
        this.random = new Random();
    }

    /**
     * Simulate a battle between two armies.
     *
     * @return the winning army.
     */
    public Army simulate(){
        int rounds = 0;

        while(armyOne.hasUnits() && armyTwo.hasUnits()){
            int firstBlood = random.nextInt(2);
           /*
            if(firstBlood == 1) {
                logger.log(Level.INFO, "army two attacks first");
            } else {
                logger.log(Level.INFO, "army one attacks first");
            }*/
            Unit unitA1 = armyOne.getRandom();
            Unit unitA2 = armyTwo.getRandom();
            try { //todo: find a better solution than try-catch here?
                if(firstBlood == 1) unitA2.attack(unitA1);
                else unitA1.attack(unitA2);
            } catch (IllegalArgumentException e) {
                if(firstBlood == 1) armyOne.remove(unitA1);
                else armyTwo.remove(unitA2);
            }
            try {
                if(firstBlood == 1) unitA1.attack(unitA2);
                else unitA2.attack(unitA1);
            } catch (IllegalArgumentException e) {
                if(firstBlood == 1) armyTwo.remove(unitA2);
                else armyOne.remove(unitA1);
            }
            rounds++;
        }
        logger.log(Level.INFO, "rounds: {0}", rounds);

        if(armyOne.getAllUnits().size() == 0) {
            logger.log(Level.INFO, "a2 won");
            return armyTwo;
        } else {
            logger.log(Level.INFO, "a1 won");
            return armyOne;
        }
    }

    @Override
    public String toString(){
        return "Battle";
    }
}
