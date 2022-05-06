package no.ntnu.olekel.ui;

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

    /**
     * Private constructor.
     * todo: initialize fields.
     */
    private Facade(){
        this.scenes = new Scenes();
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
}
