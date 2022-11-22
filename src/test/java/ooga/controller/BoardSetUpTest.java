package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.*;
import ooga.model.colorSet.DummyPlace;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BoardSetUpTest extends TestCase {
    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    private ArrayList<Place> places = new ArrayList<>();

    private ArrayList<Player> players = new ArrayList<>();

    private List<List> setUpLists = new ArrayList<>();

    @Override
    public void setUp(){
        // set up here
        gameEventHandler = new GameEventHandler();
        controller = new Controller(gameEventHandler);
        gameEventHandler.addEventListener(controller);
        places.add(new DummyPlace(0));
        places.add(new DummyPlace(1));
        players.add(new ConcretePlayer(0));
        players.add(new ConcretePlayer(1));
        setUpLists.add(places);
        setUpLists.add(players);
    }

    public void testBoardSetUp() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name());
        gameEventHandler.addEventListener(listener);
        GameEvent boardSetUp = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_GAME_DATA.name(), new TestCommand(new ModelOutput() {
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
                return null;
            }

            @Override
            public List<ControllerPlace> getBoard() {
                return null;
            }

            @Override
            public Collection<StationaryAction> getStationaryAction() {
                return null;
            }
        }));
        gameEventHandler.publish(boardSetUp);
        System.out.println("BoardSetUp event published!");
    }

    public class TestCommand implements Command{

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
                // TODO: check correct information gotten here
            }
        }
    }
}

