package no.ntnu.olekel.ui;

import no.ntnu.olekel.core.ArmyRegister;
import no.ntnu.olekel.core.BattleRegister;
import no.ntnu.olekel.core.FileHandler;

import java.lang.module.Configuration;

/**
 * Facade class that implements the Design Pattern Singleton.
 * Holds objects that we only want one of.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class Facade {
    private static volatile Facade instance;
    private final Scenes scenes;
    private BattleRegister battleRegister;
    private ArmyRegister armyRegister;
    private FileHandler fileHandler;


    /**
     * Private constructor.
     * todo: initialize fields.
     */
    private Facade(){
        this.scenes = new Scenes();
        this.armyRegister = new ArmyRegister();
        this.battleRegister = new BattleRegister();
        this.fileHandler = new FileHandler(armyRegister, battleRegister);
    }

    /**
     * Method that checks whether an instance of the class exists or not,
     * if not, it creates the instance. After this, it returns the instance.
     *
     * @return  The instance of the class.
     */
    public static Facade getInstance() {
        if(instance == null) {
            synchronized (Facade.class) {
                instance = new Facade();
            }
        }
        return instance;
    }

    public Scenes getScenes() {
        return scenes;
    }

    public ArmyRegister getArmyRegister() {
        return armyRegister;
    }

    public BattleRegister getBattleRegister() {
        return battleRegister;
    }

    public FileHandler getFileHandler(){
        return fileHandler;
    }
}
