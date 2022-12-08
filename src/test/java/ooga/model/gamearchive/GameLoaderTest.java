package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.ResourceBundle;

import static ooga.model.GameModel.DEFAULT_RESOURCE_PACKAGE;

class GameLoaderTest {
  static GameLoader loader;
  static ResourceBundle modelResources;

  @BeforeAll
  static void setUp() {
    TypeToken<Map<String, Object>> mapType = new TypeToken<>() {
    };
    Map<String, Object> map = new Gson().fromJson("", mapType);
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
    loader = new GameLoader(map, modelResources);
  }

  @Test
  void getMetadata() {
    Metadata metadata = loader.getMetadata();
    System.out.println(metadata);
  }

  @Test
  void loadPlaceData() {
  }

  @Test
  void loadPlayerData() {
  }
}