package ooga.controller;

import com.google.gson.internal.LinkedTreeMap;
import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.model.ConcretePlayer;
import ooga.model.Model;
import ooga.model.Player;
import ooga.model.colorSet.DummyPlace;
import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        places.add(new DummyPlace(0, 1));
        places.add(new DummyPlace(1, 2));
        players.add(new ConcretePlayer(0));
        players.add(new ConcretePlayer(1));
        setUpLists.add(places);
        setUpLists.add(players);
    }

    public void testBoardSetUp() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name());
        gameEventHandler.addEventListener(listener);
        GameEvent boardSetUp = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_BOARD_SET_UP.name(), new TestCommand(new Model() {
            @Override
            public void publishDice() {

            }

            @Override
            public void publishCurrentPlayer() {

            }

            @Override
            public void playersData() {

            }

            @Override
            public void boardData() {

            }

            @Override
            public void stationaryActions() {

            }

            @Override
            public void boardUpdateData() {

            }

            @Override
            public void endTurn() {

            }

            @Override
            public void buyProperty(Property property) {

            }
        }));
        gameEventHandler.publish(boardSetUp);
        System.out.println("BoardSetUp event published!");
    }

    public class TestCommand implements Command{

        private final Model boardSetUp;

        public TestCommand(Model setUp){
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

