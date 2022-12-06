package ooga.model.gameArchive;

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
          newPlace.setOwner(singlePlaceData.owner());
        if (singlePlaceData.houseCount() != null) newPlace.setHouseCount(singlePlaceData.houseCount());
        //TODO: use constructor to create place instead of setters
        places.add(newPlace);
      } catch (IOException e) {
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
      Player newPlayer = new ConcretePlayer(singlePlayersData.id(), singlePlayersData.money(), singlePlayersData.jail(),
          singlePlayersData.currentPlaceIndex(), singlePlayersData.dicesTotal(), singlePlayersData.hasNextDice(), singlePlayersData.properties());
      players.add(newPlayer);
    }
    return players;
  }

  /**
   * "protected" is for test purpose
   *
   * @param type
   */
  protected Place createPlace(String type, String id) {
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
      newPlace = (Place) makeNewPlace[0].newInstance(id);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      LOG.warn(e);
      throw new RuntimeException(e);
    }
    return newPlace;
  }
}
