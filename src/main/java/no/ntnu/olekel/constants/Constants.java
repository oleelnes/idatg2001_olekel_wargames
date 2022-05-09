package no.ntnu.olekel.constants;

/**
 * Class that contains vital constants.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public final class Constants {

  private Constants() {
    throw new IllegalStateException("Utility class");
  }

  public static final String VERSION = "0.5-SNAPSHOT";

  public static final String ROOT_DIRECTORY = "user.home";
  public static final String WARGAMES_FOLDER_LOCATION = "/wargames_olekel";
  public static final String ARMIES_FOLDER_LOCATION = "/armies";
  public static final String BATTLES_FOLDER_LOCATION = "/battles";

  public static final String ARMIES_CSV_NAME = "/armies.csv";
  public static final String BATTLES_CSV_NAME = "/battles.csv";

  public static final String ROOT_FOLDER_PATH = System.getProperty(ROOT_DIRECTORY) + WARGAMES_FOLDER_LOCATION;
  public static final String ARMIES_FOLDER_PATH = ROOT_FOLDER_PATH + ARMIES_FOLDER_LOCATION;
  public static final String BATTLES_FOLDER_PATH = ROOT_FOLDER_PATH + BATTLES_FOLDER_LOCATION;

  public static final String ARMIES_CSV_PATH = ARMIES_FOLDER_PATH + ARMIES_CSV_NAME;
  public static final String BATTLES_CSV_PATH = BATTLES_FOLDER_PATH + BATTLES_CSV_NAME;
}
