package no.ntnu.olekel.core;


/**
 * Method that holds important enums.
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public final class EnumHandler {

  /**
   * The different terrain types as enum
   */
  public enum TerrainTypes{
    FOREST,
    PLAINS,
    HILLS,
    NONE
  }

  /**
   * The state of the army that is being created. It is "NEW" if it has not been
   * added to the register yet; it is "EDIT" if it has.
   */
  public enum State {
    NEW,
    EDIT;
  }
}
