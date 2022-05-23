package no.ntnu.olekel.core;

import no.ntnu.olekel.core.units.UnitFactory;
import org.junit.jupiter.api.*;

public class FileHandlerTest {
  UnitFactory unitFactory;
  Army army;

  @BeforeEach
  public void beforeEach() {
    unitFactory = new UnitFactory();
    army = new Army("The army");
  }

  @AfterEach
  public void cleanUp() {

  }

  @Test
  public void createFile(){

  }

}
