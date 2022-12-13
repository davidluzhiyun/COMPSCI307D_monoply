package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.*;
import ooga.model.colorSet.DummyPlace;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GetPlaceActionsToViewTest extends TestCase {

  private GameEventHandler gameEventHandler;

  private Controller controller;

  private MockListener listener;

  private ArrayList<PlaceAction> expectedActions = new ArrayList<>();

  List<ControllerPlace> places = new ArrayList<>();


  @Override
  public void setUp() {
    // set up here
    gameEventHandler = new GameEventHandler();
    controller = new Controller(gameEventHandler);
    gameEventHandler.addEventListener(controller);
    expectedActions.add(PlaceAction.VIEW_INFO);
    expectedActions.add(PlaceAction.BUILD_HOUSE);
    places.add(new ControllerDummyPlace("0"));
    places.add(new ControllerDummyPlace("121"));
    Collections.sort(expectedActions);
  }

  public void testGetPlaceActions() {
    listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS.name());
    gameEventHandler.addEventListener(listener);
    GameEvent getPlaceActions = GameEventHandler.makeGameEventwithCommand(
        GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name(), new TestCommand(new ModelOutput() {
          @Override
          public GameState getGameState() {
            return GameState.GET_PLACE_ACTIONS;
          }

          @Override
          public Point getDiceNum() {
            return null;
          }

          @Override
          public int getCurrentPlayerId() {
            return 0;
          }

          @Override
          public List<ControllerPlayer> getPlayers() {
            return null;
          }

          @Override
          public List<ControllerPlace> getBoard() {
            return places;
          }

          @Override
          public Collection<StationaryAction> getStationaryAction() {
            return null;
          }

          @Override
          public int getQueryIndex() {
            return 0;
          }
        }));
    gameEventHandler.publish(getPlaceActions);
    System.out.println("GetPlaceActions event published!");
  }

  public class TestCommand implements Command {

    private final ModelOutput modelOutput;

    public TestCommand(ModelOutput modelOutput) {
      this.modelOutput = modelOutput;
    }


      @Override
      public Object getCommandArgs() {
          return this.modelOutput;
      }
  }

  public class MockListener implements GameEventListener {

    private String eventToTest;

    public MockListener(String testEvent) {
      eventToTest = testEvent;
    }

    @Override
    public void onGameEvent(GameEvent event) {
      if (event.getGameEventType().equals(eventToTest)) {
        System.out.println("Got game event:");
        System.out.println(event.getGameEventType());
        assertEquals(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS.name(),
            event.getGameEventType());
        assertEquals(expectedActions, event.getGameEventCommand().getCommand().getCommandArgs());
      }
    }
  }
}
