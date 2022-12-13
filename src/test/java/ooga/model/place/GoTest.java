package ooga.model.place;

import ooga.event.GameEventHandler;
import ooga.model.StationaryAction;
import ooga.model.place.property.ConcreteStreet;
import ooga.model.place.property.Street;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoTest {

  static Go go;

  @BeforeAll
  static void setUpTest() {
    go = new Go("Go", new GameEventHandler());
  }

  @Test
  void getMoney() {
  }

  @Test
  void getPlaceActions() {
//    assertEquals(List.of(StationaryAction.ROLL_DICE), go.getStationaryActions());
  }
}