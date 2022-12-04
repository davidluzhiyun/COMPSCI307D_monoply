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
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoadBoardTest extends TestCase {

    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    List<ParsedProperty> parsedProperties = new ArrayList<>();



    @Override
    public void setUp(){
        // set up here
        gameEventHandler = new GameEventHandler();
        controller = new Controller(gameEventHandler);
        gameEventHandler.addEventListener(controller);
        parsedProperties.add(new ParsedProperty("Go", "Go", -1));
        parsedProperties.add(new ParsedProperty("Street", "Campus Drive", 0));
    }

    public void testBoardSetUp() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_LOAD_BOARD.name());
        gameEventHandler.addEventListener(listener);
        GameEvent gameStart = GameEventHandler.makeGameEventwithCommand(GameEventType.VIEW_TO_CONTROLLER_LOAD_BOARD.name(), new TestCommand(new File("doc/plan/data/TestInitialBoard2.json")));
        gameEventHandler.publish(gameStart);
        System.out.println("LoadBoard event published!");
    }

    public class TestCommand implements Command{

        private final File file;

        public TestCommand(File config){
            this.file = config;
        }

        @Override
        public Object getCommandArgs() {
            return this.file;
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
                assertEquals(GameEventType.CONTROLLER_TO_VIEW_LOAD_BOARD.name(), event.getGameEventType());
                LoadBoardRecord command = (LoadBoardRecord) event.getGameEventCommand().getCommand().getCommandArgs();
                assertEquals(4, command.rows());
                assertEquals(5, command.columns());
                assertEquals(parsedProperties, command.places());
            }
        }
    }
}

