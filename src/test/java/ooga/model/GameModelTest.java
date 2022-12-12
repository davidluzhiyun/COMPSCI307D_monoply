package ooga.model;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import ooga.event.GameEventHandler;
import ooga.model.place.property.Street;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import static ooga.model.GameModel.DEFAULT_RESOURCE_PACKAGE;
import static ooga.model.place.AbstractPlace.DEFAULT_RESOURCE_FOLDER;
import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
  static GameModel model;
  private static ResourceBundle modelResources;
  private Map<String, ?> config;

  @BeforeAll
  static void setUpTest() {
    model = new GameModel(new GameEventHandler());
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
  }


  @Test
  void testInitializeGame() {

    Gson gson = new Gson();
    Reader reader = null;
    try {
      File file = new File("src/main/resources/ooga/model/place/InitialConfig.json");
      reader = new FileReader(file);
      TypeToken<Map<String, ?>> mapType = new TypeToken<>() {
      };
      config = gson.fromJson(reader, mapType);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    model.initializeGame((Map<String, LinkedTreeMap>) config);

    assertEquals(4, model.getPlayers().size());
    assertEquals(121, model.getBoard().get(0).getPlaceId());
  }
}