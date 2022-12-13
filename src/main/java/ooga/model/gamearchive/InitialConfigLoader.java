package ooga.model.gamearchive;

import com.google.gson.internal.LinkedTreeMap;
import ooga.event.GameEventHandler;
import ooga.model.player.AddOneDiceRollJail;
import ooga.model.player.ConcretePlayer;
import ooga.model.player.Player;
import ooga.model.colorSet.ConcreteColorSet;
import ooga.model.exception.BadDataException;
import ooga.model.exception.MonopolyException;
import ooga.model.place.Place;
import ooga.model.player.CanBuildOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Predicate;

import static ooga.model.gamearchive.ArchiveUtility.createAddOneDiceRollJail;
import static ooga.model.gamearchive.ArchiveUtility.createHouseBuildChecker;
import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;
import static ooga.model.player.CanBuildOn.PLAYER_PACKAGE_NAME;

public class InitialConfigLoader {
  private Map<String, LinkedTreeMap> initialConfig;
  private ResourceBundle myResources;
  private List<Place> places;
  private List<Player> players;
  private final GameEventHandler gameEventHandler;
  private GameConfig gameConfig;
  private static final Logger LOG = LogManager.getLogger(InitialConfigLoader.class);
  public static double DEFAULT_STARTING_MONEY = 1500.0;

  public InitialConfigLoader(Map<String, LinkedTreeMap> initialConfig, ResourceBundle resources, GameEventHandler gameEventHandler) {
    this.initialConfig = initialConfig;
    this.myResources = resources;
    this.gameEventHandler = gameEventHandler;
    LinkedTreeMap meta = initialConfig.get("meta");
    gameConfig = new GameConfig(getJailIndex(), (double) meta.getOrDefault("money", DEFAULT_STARTING_MONEY),
        (boolean) meta.getOrDefault("color", true), (boolean) meta.getOrDefault("jail", true));
    loadData();
  }

  public GameConfig getGameConfig() throws BadDataException {
    return gameConfig;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public List<Place> getPlaces() {
    return places;
  }

  private void loadData() {
    int j = 0;
    places = new ArrayList<>();
    while (initialConfig.containsKey(String.valueOf(j))) {
      places.add(createPlace((String) initialConfig.get(String.valueOf(j)).get("type"), (String) initialConfig.get(String.valueOf(j)).get("id")));
      j++;
    }
    players = new ArrayList<>();
    Map<Integer, Predicate<Collection<Place>>> checkers = new ConcreteColorSet(places).outputCheckers();
    for (int i = 0; i < (int) (double) initialConfig.get("meta").get("players"); i++) {
      Player newPlayer = new ConcretePlayer(i, gameEventHandler, createHouseBuildChecker(gameConfig.colorCheck()));
      newPlayer.setColorSetCheckers(checkers);
      newPlayer.setAddOneDiceRollJail(createAddOneDiceRollJail(gameConfig.ifGoJail(), newPlayer));
      players.add(newPlayer);
    }
  }

  public void check() throws MonopolyException {

  }

  protected Place createPlace(String type, String id) {
    Place newPlace;
    Class<?> placeClass;
    System.out.println(type);
    System.out.println(id);
    try {
      placeClass = Class.forName(PLACE_PACKAGE_NAME + myResources.getString(type));
    } catch (ClassNotFoundException e) {
//      LOG.warn(e);
      throw new IllegalStateException("classNotFound", e);
    }
    Constructor<?>[] makeNewPlace = placeClass.getConstructors();
    try {
      newPlace = (Place) makeNewPlace[0].newInstance(id, gameEventHandler);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
//      LOG.warn(e);
      throw new RuntimeException(e);
    }
    return newPlace;
  }

  /**
   * Get the index of the jail or throws MonopolyException when the index of jail is not valid or consistent.
   *
   * @return
   * @throws MonopolyException
   */
  private int getJailIndex() throws BadDataException {
    int j = 0;
    int jailIndexMeta = (int) (double) initialConfig.get("meta").getOrDefault("jail", -1.0);
    int jailIndex = -1;
    while (initialConfig.containsKey(String.valueOf(j))) {
      if (initialConfig.get(String.valueOf(j)).get("type").equals("jail")) {
        jailIndex = j;
        break;
      }
      j++;
    }
    if (jailIndex != jailIndexMeta) {
      System.out.println(1);
      throw new BadDataException("Bad data file");
    }
    return jailIndex;
  }
}
