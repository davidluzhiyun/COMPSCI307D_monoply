package ooga.model.gamearchive;

import com.google.gson.internal.LinkedTreeMap;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.ConcreteCommand;
import ooga.model.ConcretePlayer;
import ooga.model.Player;
import ooga.model.colorSet.ConcreteColorSet;
import ooga.model.exception.BadDataException;
import ooga.model.exception.MonopolyException;
import ooga.model.place.Place;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Predicate;

import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;

public class InitialConfigLoader {
  Map<String, LinkedTreeMap> initialConfig;
  ResourceBundle myResources;
  List<Place> places;
  List<Player> players;
  private final GameEventHandler gameEventHandler;

  public InitialConfigLoader(Map<String, LinkedTreeMap> initialConfig, ResourceBundle resources) {
    this.initialConfig = initialConfig;
    this.myResources = resources;
    this.gameEventHandler = new GameEventHandler();
    loadData();
  }

  public GameConfig getGameConfig() throws BadDataException {
//    int jailIndex;
//    try {
//      jailIndex = getJailIndex();
//    } catch (MonopolyException e) {
//      Command<MonopolyException> command = new ConcreteCommand<>(e);
//      GameEvent event = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_VIEW_EXCEPTION.name(), command);
//      gameEventHandler.publish(event);
//      throw new RuntimeException();
//    }

    return new GameConfig(getJailIndex(), false, false);
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
      Player newPlayer = new ConcretePlayer(i);
      newPlayer.setColorSetCheckers(checkers);
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
      newPlace = (Place) makeNewPlace[0].newInstance(id);
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
    int jailIndexMeta = (int) initialConfig.get("meta").getOrDefault("jail", -1);
    int jailIndex = -1;
    while (initialConfig.containsKey(String.valueOf(j))) {
      if (initialConfig.get(String.valueOf(j)).get("type").equals("jail")) {
        jailIndex = j;
        break;
      }
      j++;
    }
    if (jailIndex != jailIndexMeta) {
      throw new BadDataException("Bad data file");
    }
    return jailIndex;
  }
}
