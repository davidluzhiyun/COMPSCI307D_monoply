package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.event.GameEventHandler;
import ooga.model.colorSet.ConcreteColorSet;
import ooga.model.player.*;
import ooga.model.place.Place;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static ooga.model.gamearchive.ArchiveUtility.createAddOneDiceRollJail;
import static ooga.model.gamearchive.ArchiveUtility.createHouseBuildChecker;
import static ooga.model.place.AbstractPlace.DEFAULT_RESOURCE_FOLDER;
import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;
import static ooga.model.player.CanBuildOn.PLAYER_PACKAGE_NAME;

public class GameLoader {
  private Map<String, Object> gameData;
  private Metadata metadata;
  private static final Logger LOG = LogManager.getLogger(GameLoader.class);
  private ResourceBundle myResources;
  private GameEventHandler gameEventHandler;
  private List<Place> places;
  private List<Player> players;
  private Map<String, Function6<String, String, Integer, Integer, Constructor<?>[], GameEventHandler, Place>> switchMap;
  private final GameConfig gameConfig;

  public GameLoader(Map<String, Object> map, ResourceBundle resources, GameEventHandler gameEventHandler, GameConfig gameConfig) {
    this.gameData = map;
    this.myResources = resources;
    this.gameEventHandler = gameEventHandler;
    Map<String, Double> meta = (Map<String, Double>) gameData.get("meta");
    metadata = new Metadata(meta.get("playerCount").intValue(), meta.get("currentPlayerId").intValue());
    this.gameConfig = gameConfig;
    setUpMap();
    loadPlaceData();
    loadPlayerData();
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public List<Place> getPlaces() {
    return places;
  }

  private void loadPlaceData() {
    places = new ArrayList<>();
    List<PlaceSaver> placesData = (List<PlaceSaver>) gameData.get("places");
    for (PlaceSaver singlePlaceData : placesData) {
      String placeId = singlePlaceData.id();
      try {
        Map<String, ?> config = getConfig(placeId, new Gson());
        String type = (String) config.get("type");
        int ownerId = -1;
        int houseCount = -1;
        if (singlePlaceData.owner() != null && singlePlaceData.owner() != -1) //if the place can be purchased and there is someone who purchased it
          ownerId = singlePlaceData.owner();
        if (singlePlaceData.houseCount() != null)
          houseCount = singlePlaceData.houseCount();
        Place newPlace = createPlace(type, placeId, ownerId, houseCount);
        places.add(newPlace);
      } catch (IOException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private static Map<String, ?> getConfig(String placeId, Gson gson) throws FileNotFoundException {
    Reader reader;
    Map<String, ?> config;
    File file = new File("." + "/src/main/resources" + DEFAULT_RESOURCE_FOLDER + placeId + ".json");
    reader = new FileReader(file);
    TypeToken<Map<String, ?>> mapType = new TypeToken<>() {
    };
    config = gson.fromJson(reader, mapType);
    return config;
  }

  private void loadPlayerData() {
    players = new ArrayList<>();
    List<PlayerSaver> playersData = (List<PlayerSaver>) gameData.get("players");
    Map<Integer, Predicate<Collection<Place>>> checkers = new ConcreteColorSet(places).outputCheckers();
    for (PlayerSaver singlePlayersData : playersData) {
      Player newPlayer = new ConcretePlayer(singlePlayersData.id(), gameEventHandler, singlePlayersData.money(), singlePlayersData.currentPlaceIndex(), singlePlayersData.hasNextDice(), singlePlayersData.jail(),
          singlePlayersData.dicesTotal(), singlePlayersData.properties(), createHouseBuildChecker(gameConfig.colorCheck()), singlePlayersData.isAlive());
      newPlayer.setColorSetCheckers(checkers);
      newPlayer.setAddOneDiceRollJail(createAddOneDiceRollJail(gameConfig.ifGoJail(), newPlayer));
      players.add(newPlayer);
    }
  }

  public void setUpPlayersPropertiesAndPropertyOwner(List<Player> players, List<Place> places) {
    int i = 0;
    for (Player player : players) {
      List<Place> ownedProperties = new ArrayList<>();
      for (int propertyIndex : player.getPropertyIndices()) {
        ownedProperties.add(places.get(propertyIndex));
        places.get(propertyIndex).setOwner(i, player);
      }
      player.setProperties(ownedProperties);
      i++;
    }
  }

  /**
   * "protected" is for test purpose
   */
  private void setUpMap() {
    Function6<String, String, Integer, Integer, Constructor<?>[], GameEventHandler, Place> funcStreet = (type1, id1, ownerId, houseCount, constructor, handler) -> (Place) constructor[1].newInstance(id1, ownerId, houseCount, handler);
    Function6<String, String, Integer, Integer, Constructor<?>[], GameEventHandler, Place> funcRailroad = (type1, id1, ownerId, houseCount, constructor, handler) -> (Place) constructor[1].newInstance(id1, ownerId, handler);
    Function6<String, String, Integer, Integer, Constructor<?>[], GameEventHandler, Place> funcUtility = (type1, id1, ownerId, houseCount, constructor, handler) -> (Place) constructor[1].newInstance(id1, ownerId, handler);
    Function6<String, String, Integer, Integer, Constructor<?>[], GameEventHandler, Place> funcGo = (type1, id1, ownerId, houseCount, constructor, handler) -> (Place) constructor[0].newInstance(id1, handler);
    Function6<String, String, Integer, Integer, Constructor<?>[], GameEventHandler, Place> funcFreeParking = (type1, id1, ownerId, houseCount, constructor, handler) -> (Place) constructor[0].newInstance(id1, handler);
    switchMap = Map.of("Street", funcStreet, "Railroad", funcRailroad, "Utility", funcUtility, "Go", funcGo, "FreeParking", funcFreeParking);
  }

  protected Place createPlace(String type, String id, int ownerId, int houseCount) throws InvocationTargetException, InstantiationException, IllegalAccessException {
    Place newPlace;
    Class<?> placeClass;
    try {
      placeClass = Class.forName(PLACE_PACKAGE_NAME + myResources.getString(type));
    } catch (ClassNotFoundException e) {
      LOG.warn(e);
      throw new IllegalStateException("classNotFound", e);
    }
    Constructor<?>[] makeNewPlace = placeClass.getConstructors();
    try {
      newPlace = switchMap.get(type).apply(type, id, ownerId, houseCount, makeNewPlace, gameEventHandler);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      LOG.warn(e);
      throw new RuntimeException(e);
    }
    return newPlace;
  }

  @FunctionalInterface
  interface Function6<One, Two, Three, Four, Constructor, GameEventHandler, Five> {
    Five apply(One one, Two two, Three three, Four four, Constructor constructor, GameEventHandler handler) throws InvocationTargetException, InstantiationException, IllegalAccessException;
  }
}
