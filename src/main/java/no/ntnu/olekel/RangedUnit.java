package no.ntnu.olekel;

public class RangedUnit extends Unit {
  private int timesResisted;

  RangedUnit(String name, int health, int attack, int armor){
    super(name, health, attack, armor);
    timesResisted = 0;
  }

  RangedUnit(String name, int health){
    super(name, health, 15, 8);
    timesResisted = 0;
  }

  @Override
  public int getAttackBonus() {
    return 3;
  }

  @Override
  public int getResistBonus() {
    if (timesResisted == 0) {
      timesResisted++;
      return 6;
    } else if (timesResisted < 3) {
      timesResisted++;
      return 4;
    } else {
      timesResisted++;
      return 2;
    }
  }

}
