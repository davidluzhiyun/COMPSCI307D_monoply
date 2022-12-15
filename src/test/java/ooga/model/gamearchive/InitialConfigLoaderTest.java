package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ooga.event.GameEventHandler;
import ooga.model.place.Place;
import ooga.model.player.CanBuildOn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;
import java.util.ResourceBundle;

import static ooga.model.GameModel.DEFAULT_RESOURCE_PACKAGE;
import static org.junit.jupiter.api.Assertions.*;

class InitialConfigLoaderTest {

  static InitialConfigLoader loader;
  static ResourceBundle modelResources;

  @BeforeAll
  static void setUpTest() {
    Reader reader;
    File file = new File("." + "/src/main/resources/ooga/model/place/InitialConfigJail.json");
    try {
      reader = new FileReader(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
    loader = new InitialConfigLoader(new Gson().fromJson(reader, Map.class), modelResources, new GameEventHandler());
  }

  @Test
  void testGameConfig() {
    GameConfig gameConfig = loader.getGameConfig();
    System.out.println(gameConfig);
    assertEquals(1500, gameConfig.startingMoney());
    assertEquals(-1, gameConfig.JailIndex());
    assertTrue(gameConfig.colorCheck());
    assertFalse(gameConfig.ifGoJail());
  }

  @Test
  void testCreatePlaceStreet() {
    Place p1 = loader.createPlace("Street", "Shanghai1");
    assertEquals("Shanghai1", p1.getPlaceId());
    assertEquals(100, p1.getHousePrice());
  }

  @Test
  void testCreatePlaceUtility() {
    Place p1 = loader.createPlace("Utility", "FirePlant");
    assertEquals("Fire Plant", p1.getPlaceId());
    assertEquals(150, p1.getPurchasePrice());
  }

  @Test
  void testCreatePlaceRailroad() {
    Place p1 = loader.createPlace("Railroad", "");
    assertEquals("Fire Plant", p1.getPlaceId());
    assertEquals(150, p1.getPurchasePrice());
  }
}