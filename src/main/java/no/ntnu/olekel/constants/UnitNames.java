package no.ntnu.olekel.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 *
 * @author  Ole Kristian Elnæs.
 */
public class UnitNames {

  private List<String> names;
  private List<String> placeNames;
  private Random random;

  /**
   * Constructor that initializes fields.
   */
  public UnitNames(){
    names = new ArrayList<>();
    placeNames = new ArrayList<>();
    random = new Random();
    addPlaceNames();
    addNames();
  }

  /**
   * Method that adds names (strings) to the placeNames list.
   */
  private void addPlaceNames(){
    Collections.addAll(placeNames, "Ere", "Landon", "Swarthel", "Regina", "Lionas",
        "Olifar", "Thunder", "Reigna", "Bargen", "Gjavik", "Trandhome", "Homestead", "Thare",
        "Limpen", "New Jork", "Oldstone", "Oldtown", "King's Standing", "Lannis", "Thins", "Kandell",
        "Cunlowe", "Ologarche", "Redfall", "Stan", "Beamslong", "Osvalder", "Gryff", "Indor",
        "Gonga", "Yundar", "Winch", "Thal", "Neander", "Mars");
  }

  /**
   * Method that adds names (strings) to the names list.
   */
  private void addNames(){
    Collections.addAll(names, "Superfluous", "Quick", "Trending", "Tramping", "Thundering",
        "Believing", "Liberating", "Tactical", "Martial", "Warlike", "Tantalizing", "Non-understanding",
        "Up-front", "Over-the-top", "Bearlike", "Lionlike", "Tigerlike", "Houndlike", "Thinking",
        "Outgoing", "Maned", "Computer Scientist", "Programming", "Calculating", "Feared",
        "Warmongering", "Propaganda-spreading", "Sharp-witted", "Sharp-speared", "Merciless",
        "Merciful", "Vengeful", "Lazy", "Laughed-at", "Motherless", "Fatherless", "Old-men",
        "Wizards", "Orcs", "Orcas", "Trolls", "");
  }

  /**
   * Method that constructs a name for a unit through the use of the names
   * and placeNames arrayLists, and through the use of random, and then
   * returns it.
   *
   * @return  A unit name.
   */
  public String createUnitName(){
    String placeName = placeNames.get(random.nextInt(placeNames.size()));
    String name = names.get(random.nextInt(names.size()));
    return "The " + name + " Unit of " + placeName;
  }

}
