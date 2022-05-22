package no.ntnu.olekel.core;

public final class EnumHandler {

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
