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
import ooga.model.player.BuildHouseCheckerNoColor;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BoardSetUpTest extends TestCase {

    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    private int rows = 4;

    private int cols = 5;

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
        players.add(new ConcretePlayer(0, new BuildHouseCheckerNoColor()));
        players.add(new ConcretePlayer(1, new BuildHouseCheckerNoColor()));
        places.add(new DummyPlace("Go"));
        places.add(new DummyPlace("New York1"));
        actions.add(StationaryAction.ROLL_DICE);
        parsedProperties.add(new ParsedProperty("Go", "Go", "Go", 0, "path/to/image1", "Go", null, false));
        parsedProperties.add(new ParsedProperty("New York1", "Street", "New York", 0, "path/to/image2", "test upper text", "test lower text", true));
    }

    public void testBoardSetUp() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_START_GAME.name());
        gameEventHandler.addEventListener(listener);
        GameEvent gameStart = GameEventHandler.makeGameEventwithCommand(GameEventType.VIEW_TO_CONTROLLER_GAME_START.name(), new GameStartEventTest.TestCommand(new File("doc/plan/data/TestInitialBoard2.json")));
        gameEventHandler.publish(gameStart);
        GameEvent boardSetUp = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name(), new TestCommand(new ModelOutput() {
            @Override
            public GameState getGameState() {
                return GameState.GAME_SET_UP;
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

            @Override
            public int getQueryIndex() {
                return -1;
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
                assertEquals(GameEventType.CONTROLLER_TO_VIEW_START_GAME.name(), event.getGameEventType());
                InitBoardRecord command = (InitBoardRecord) event.getGameEventCommand().getCommand().getCommandArgs();
                assertEquals(0, command.currentPlayerId());
                assertEquals(actions, command.stationaryActions());
                assertEquals(players, command.players());
                assertEquals(parsedProperties, command.places());
                assertEquals(rows, command.rows());
                assertEquals(cols, command.cols());
            }
        }
    }
}

