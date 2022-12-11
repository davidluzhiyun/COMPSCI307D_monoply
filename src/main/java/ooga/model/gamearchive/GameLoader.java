package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.model.ConcretePlayer;
import ooga.model.Player;
import ooga.model.place.Place;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
    metadata = (Metadata) map.get("meta");
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public List<Place> loadPlaceData(Map<String, Object> map) {
    List<Place> places = new ArrayList<>();
    List<PlaceSaver> placesData = (List<PlaceSaver>) map.get("places");
    for (PlaceSaver singlePlaceData : placesData) {
      String placeId = singlePlaceData.id();
      try {
        Map<String, ?> config = getConfig(placeId, new Gson());
        String type = (String) config.get("type");
        Place newPlace = createPlace(type, placeId);
        if (singlePlaceData.owner() != null && singlePlaceData.owner() != -1) //if the place can be purchased and there is someone who purchased it
//          newPlace.setOwner(singlePlaceData.owner());
          // TODO: Load owner and ownerid
          if (singlePlaceData.houseCount() != null) newPlace.setHouseCount(singlePlaceData.houseCount());
        //TODO: use constructor to create place instead of setters
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

  public List<Player> loadPlayerData(Map<String, Object> map) {
    List<Player> players = new ArrayList<>();
    List<PlayerSaver> playersData = (List<PlayerSaver>) map.get("players");
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
   *
   */
  private void setUpMap() {
    switchMap = new HashMap<>();
    Function6<String, String, Integer, Integer, Constructor<?>[], Place> funcStreet = (type1, id1, ownerId, houseCount, constructor) -> (Place) constructor[0].newInstance(id1, ownerId, houseCount);
    Function6<String, String, Integer, Integer, Constructor<?>[], Place> funcRailroad = (type1, id1, ownerId, houseCount, constructor) -> (Place) constructor[0].newInstance(id1);
    switchMap.put("Street", funcStreet);
    switchMap.put("Railroad", funcRailroad);
  }
  protected Place createPlace(String type, String id) throws InvocationTargetException, InstantiationException, IllegalAccessException {
    Place newPlace;
    Class<?> placeClass;
    try {
      placeClass = Class.forName(PLACE_PACKAGE_NAME + myResources.getString(type));
    } catch (ClassNotFoundException e) {
      LOG.warn(e);
      throw new IllegalStateException("classNotFound", e);
    }
    Constructor<?>[] makeNewPlace = placeClass.getConstructors();
    newPlace = switchMap.get("type").apply(type, id, 1, 1, makeNewPlace);
    try {
      newPlace = (Place) makeNewPlace[0].newInstance(id);
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
