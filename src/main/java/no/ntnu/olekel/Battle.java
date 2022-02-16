package no.ntnu.olekel;

/**
 * The class Battle.
 *
 * @author Ole Kristian Elnæs
 * @version 16.02.2022
 */
public class Battle {
    private Army armyOne;
    private Army armyTwo;

    /**
     * Instantiates a new Battle.
     *
     * @param armyOne the army one
     * @param armyTwo the army two
     */
    Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Simulate army.
     *
     * @return the army
     */
    public Army simulate(){
        boolean battle = true;
        int rounds = 0;

        while(battle){
            Unit unitA1 = armyOne.getRandom();
            Unit unitA2 = armyTwo.getRandom();
            try{
                unitA2.attack(unitA1);
            } catch (IllegalArgumentException e) {
                armyOne.remove(unitA1);
            }
            try{
                unitA1.attack(unitA2);
            } catch (IllegalArgumentException e) {
                armyTwo.remove(unitA2);
            }
            if(armyTwo.getAllUnits().size() == 0 || armyOne.getAllUnits().size() == 0) {
                battle = false;
            }
            rounds++;
        }
        System.out.println("rounds: " + rounds);

        if(armyOne.getAllUnits().size() == 0) {
            System.out.println("a2 won");
            return armyTwo;
        } else {
            System.out.println("a1 won");
            return armyOne;
        }
    }
}
