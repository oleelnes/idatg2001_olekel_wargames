package no.ntnu.olekel.core;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @version {@value no.ntnu.olekel.constants.Constants#VERSION}
 * @author Ole Kristian Elnæs
 */
public class BattleRegister {
  private List<Battle> battleRegister;

  public BattleRegister(){
    this.battleRegister = new ArrayList<>();
  }

  public void loadBattleCSV(Path path) {

  }

  public void saveBattleCSV(Path path) {

  }

}
