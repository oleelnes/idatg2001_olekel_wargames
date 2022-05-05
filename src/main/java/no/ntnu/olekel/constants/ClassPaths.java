package no.ntnu.olekel.constants;

import java.net.URL;

import static java.util.Objects.requireNonNull;

public class ClassPaths {

  /**
   * Private constructor to hide the public one.
   */
  private ClassPaths() {
    throw new IllegalStateException("Utility class");
  }

  private static final String MAIN_PAGE_PATH = "fxml-files/mainPage.fxml";
  private static final String NEW_WAR_PAGE_PATH = "fxml-files/newWarPage.fxml";
  private static final String VIEW_ARMIES_PAGE_PATH = "fxml-files/viewArmiesPage.fxml";

  /**
   * URLs to all the fxml files.
   */
  public static final URL newWarPageURL = ClassPaths.class.getClassLoader().getResource(NEW_WAR_PAGE_PATH);
  public static final URL mainPageURL = ClassPaths.class.getClassLoader().getResource(MAIN_PAGE_PATH);
  public static final URL viewArmiesURL = ClassPaths.class.getClassLoader().getResource(VIEW_ARMIES_PAGE_PATH);


}
