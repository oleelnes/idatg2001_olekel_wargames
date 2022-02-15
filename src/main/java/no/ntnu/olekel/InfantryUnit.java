package no.ntnu.olekel;

public class InfantryUnit extends Unit{

  InfantryUnit(String name, int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  InfantryUnit(String name, int health){
    super(name, health, 15, 10);
  }

  @Override
  public int getAttackBonus() {
    return 2;
  }

  @Override
  public int getResistBonus() {
    return 1;
  }


}
