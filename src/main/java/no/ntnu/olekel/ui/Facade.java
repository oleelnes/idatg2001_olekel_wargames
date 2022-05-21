package no.ntnu.olekel.ui;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import no.ntnu.olekel.constants.ClassPaths;
import no.ntnu.olekel.controllers.SimpleWarSimulationPage;
import no.ntnu.olekel.core.*;
import no.ntnu.olekel.core.units.UnitFactory;

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
    private final BattleRegister battleRegister;
    private final ArmyRegister armyRegister;
    private final FileHandler fileHandler;
    private UnitFactory unitFactory;
    private Army army;
    private Stage stage;
    private DialogsHandler dialogs;
    private BattleHandler battleHandler;
    private Battle battle;


    /**
     * Private constructor.
     * todo: initialize fields.
     */
    private Facade(){
        this.scenes = new Scenes();
        this.armyRegister = new ArmyRegister();
        this.battleRegister = new BattleRegister();
        this.fileHandler = new FileHandler(armyRegister, battleRegister);
        this.unitFactory = new UnitFactory();
        this.army = new Army("Temporary");
        this.dialogs = new DialogsHandler();
        this.battleHandler = new BattleHandler();
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

    public void update(){
        //simulationPage.update();
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

    public Army getArmy(){
        return army;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }

    public Stage getStage(){
        try {
            return stage;
        } catch(NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DialogsHandler getDialogsHandler(){
        return dialogs;
    }

    public BattleHandler getBattleHandler(){
        return battleHandler;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle(){
        try {
            return this.battle;
        } catch (NullPointerException e) {
            return null;
        }
    }

}
