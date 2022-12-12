package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.model.ConcretePlayer;
import ooga.model.Player;
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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static ooga.model.place.AbstractPlace.DEFAULT_RESOURCE_FOLDER;
import static ooga.model.place.AbstractPlace.PLACE_PACKAGE_NAME;

public class GameLoader {
  Map<String, Object> gameData;
  Metadata metadata;
  private static final Logger LOG = LogManager.getLogger(GameLoader.class);
  ResourceBundle myResources;
  Map<String, Function6<String, String, Integer, Integer, Constructor<?>[], Place>> switchMap;

  public GameLoader(Map<String, Object> map, ResourceBundle resources) {
    this.gameData = map;
    this.myResources = resources;
    Map<String, Double> meta = (Map<String, Double>) gameData.get("meta");
    metadata = new Metadata(meta.get("playerCount").intValue(), meta.get("currentPlayerId").intValue());
    setUpMap();
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public List<Place> loadPlaceData() {
    List<Place> places = new ArrayList<>();
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
    return places;
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

  public List<Player> loadPlayerData() {
    List<Player> players = new ArrayList<>();
    List<PlayerSaver> playersData = (List<PlayerSaver>) gameData.get("players");
    for (PlayerSaver singlePlayersData : playersData) {
      Player newPlayer = new ConcretePlayer(singlePlayersData.id(), singlePlayersData.money(), singlePlayersData.currentPlaceIndex(), singlePlayersData.hasNextDice(), singlePlayersData.jail(),
          singlePlayersData.dicesTotal(), singlePlayersData.properties());
      players.add(newPlayer);
    }
    return players;
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
    Function6<String, String, Integer, Integer, Constructor<?>[], Place> funcStreet = (type1, id1, ownerId, houseCount, constructor) -> (Place) constructor[1].newInstance(id1, ownerId, houseCount);
    Function6<String, String, Integer, Integer, Constructor<?>[], Place> funcRailroad = (type1, id1, ownerId, houseCount, constructor) -> (Place) constructor[1].newInstance(id1, ownerId);
    Function6<String, String, Integer, Integer, Constructor<?>[], Place> funcUtility = (type1, id1, ownerId, houseCount, constructor) -> (Place) constructor[1].newInstance(id1, ownerId);
    Function6<String, String, Integer, Integer, Constructor<?>[], Place> funcGo = (type1, id1, ownerId, houseCount, constructor) -> (Place) constructor[0].newInstance(id1);
    switchMap = Map.of("Street", funcStreet, "Railroad", funcRailroad, "Utility", funcUtility, "Go", funcGo);
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
      newPlace = switchMap.get(type).apply(type, id, ownerId, houseCount, makeNewPlace);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      LOG.warn(e);
      throw new RuntimeException(e);
    }
    return newPlace;
  }

  @FunctionalInterface
  interface Function6<One, Two, Three, Four, Constructor, Five> {
    Five apply(One one, Two two, Three three, Four four, Constructor constructor) throws InvocationTargetException, InstantiationException, IllegalAccessException;
  }
}
