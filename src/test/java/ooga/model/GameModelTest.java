package ooga.model;

import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.controller.PlayerRecord;
import ooga.event.GameEventHandler;
import ooga.model.place.property.Street;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import static ooga.model.GameModel.DEFAULT_RESOURCE_PACKAGE;
import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
  static GameModel model;
  private static ResourceBundle modelResources;

  @BeforeAll
  static void setUpTest() {
    model = new GameModel(new GameEventHandler());
    modelResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
  }

  @Test
  void testCreatePlaceStreet() {
    Street street = (Street) model.createPlace("Street", 121);
    assertEquals(100, street.getHousePrice());
  }

  @Test
  void testInitializeGame() {
    List<ParsedProperty> parsedPropertyList = List.of(new ParsedProperty(121, "Street", 1), new ParsedProperty(121, "Street", 1));
    Collection<PlayerRecord> players = List.of(new PlayerRecord(0, 0, false, 0, new ArrayList<>(), 1500), new PlayerRecord(0, 0, false, 0, new ArrayList<>(), 1500));
    InitBoardRecord initBoardRecord = new InitBoardRecord(parsedPropertyList, new ArrayList<>(), players, 0);
    model.initializeGame(initBoardRecord);

    assertEquals(2, model.getPlayers().size());

    assertEquals(121, model.getPlaces().get(0).getPlaceId());
  }
}