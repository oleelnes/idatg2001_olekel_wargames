package no.ntnu.olekel;

public class RangedUnit extends Unit {

  RangedUnit(String name, int health, int attack, int armor){
    super(name, health, attack, armor);
  }

  RangedUnit(String name, int health){
    super(name, health, 15, 8);
  }

  @Override
  public int getAttackBonus() {
    return 0;
  }

  @Override
  public int getResistBonus() {
    return 0;
  }

}
