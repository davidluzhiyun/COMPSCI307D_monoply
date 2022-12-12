package ooga.model.gamearchive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    File file = new File("." + "/src/main/resources/loaddata.json");
    try {
      reader = new FileReader(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
    loader = new InitialConfigLoader(new Gson().fromJson(reader, Map.class), modelResources);
  }

  @Test
  void testCreateBuildHouseChecker() {
    CanBuildOn canBuildOn = loader.createHouseBuildChecker(true);
  }
}