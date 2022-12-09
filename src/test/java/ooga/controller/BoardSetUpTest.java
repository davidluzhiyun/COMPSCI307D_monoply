package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.*;
import ooga.model.colorSet.DummyPlace;
import ooga.model.colorSet.DummyStreet;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BoardSetUpTest extends TestCase {

    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    List<ControllerPlayer> players = new ArrayList<>();

    List<ControllerPlace> places = new ArrayList<>();

    Collection<StationaryAction> actions = new ArrayList<>();

    List<ParsedProperty> parsedProperties = new ArrayList<>();



    @Override
    public void setUp(){
        // set up here
        gameEventHandler = new GameEventHandler();
        controller = new Controller(gameEventHandler);
        gameEventHandler.addEventListener(controller);
        players.add(new ConcretePlayer(0));
        players.add(new ConcretePlayer(1));
        places.add(new DummyPlace("0"));
        places.add(new DummyPlace("121"));
        actions.add(StationaryAction.ROLL_DICE);
        parsedProperties.add(new ParsedProperty("Go", "Go", 0));
        parsedProperties.add(new ParsedProperty("Street", "Campus Drive", 0));
    }

    public void testBoardSetUp() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name());
        gameEventHandler.addEventListener(listener);
        GameEvent boardSetUp = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_BOARD_SET_UP.name(), new TestCommand(new ModelOutput() {
            @Override
            public GameState getGameState() {
                return null;
            }

            @Override
            public Point getDiceNum() {
                return null;
            }

            @Override
            public int getCurrentPlayer() {
                return 0;
            }

            @Override
            public List<ControllerPlayer> getPlayers() {
                return players;
            }

            @Override
            public List<ControllerPlace> getBoard() {
                return places;
            }

            @Override
            public Collection<StationaryAction> getStationaryAction() {
                return actions;
            }
        }));
        gameEventHandler.publish(boardSetUp);
        System.out.println("BoardSetUp event published!");
    }

    public static class TestCommand implements Command{

        private final ModelOutput boardSetUp;

        public TestCommand(ModelOutput setUp){
            this.boardSetUp = setUp;
        }

        @Override
        public Object getCommandArgs() {
            return this.boardSetUp;
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
                assertEquals(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name(), event.getGameEventType());
                InitBoardRecord command = (InitBoardRecord) event.getGameEventCommand().getCommand().getCommandArgs();
                assertEquals(0, command.currentPlayerId());
                assertEquals(actions, command.stationaryActions());
                assertEquals(players, command.players());
                assertEquals(parsedProperties, command.places());
            }
        }
    }
}

