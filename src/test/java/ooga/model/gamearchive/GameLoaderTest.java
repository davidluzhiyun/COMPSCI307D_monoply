package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.event.GameEventHandler;
import ooga.model.place.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;

import static ooga.model.GameModel.DEFAULT_RESOURCE_PACKAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameLoaderTest {
  static GameLoader loader;
  static ResourceBundle modelResources;

  @BeforeAll
  static void setUp() {
    Reader reader;
    File file = new File("." + "/data/paused_games/loaddata.json");
    try {
      reader = new FileReader(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    TypeToken<Map<String, Object>> mapType = new TypeToken<>() {
    };
    Map<String, Object> map = new Gson().fromJson(reader, mapType);
    System.out.println(map);
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
    GameConfig gameConfig = new GameConfig(10, 1500, true, true);
    loader = new GameLoader(map, modelResources, new GameEventHandler());
  }

  @Test
  void testMetaData() {
    Metadata metadata = loader.getMetadata();
    assertEquals(1, metadata.currentPlayerId());
    assertEquals(2, metadata.playerCount());
  }

  @Test
  void createPlaceStreet() throws InvocationTargetException, InstantiationException, IllegalAccessException {
    Place place1 = loader.createPlace("Street", "Shanghai1", 1, 1);
    assertEquals("Shanghai1", place1.getPlaceId());
  }

  @Test
  void createPlaceRailroad() throws InvocationTargetException, InstantiationException, IllegalAccessException {
    Place place1 = loader.createPlace("Railroad", "North Railroad", 1, 1);
    assertEquals("North Railroad", place1.getPlaceId());
  }

  @Test
  void createUtility() throws InvocationTargetException, InstantiationException, IllegalAccessException {
    Place place1 = loader.createPlace("Utility", "Fire Plant", 1, 1);
    assertEquals("Fire Plant", place1.getPlaceId());
  }

  @Test
  void loadPlaceData() {
  }

  @Test
  void loadPlayerData() {
  }
}