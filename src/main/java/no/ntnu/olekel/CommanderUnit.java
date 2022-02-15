package no.ntnu.olekel;

public class CommanderUnit extends CavalryUnit {

  CommanderUnit(String name, int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  CommanderUnit(String name, int health) {
    super(name, health, 20, 12);
  }
}
