package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;

import java.io.File;

public class GameStartEventTest extends TestCase {
    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;
    @Override
    public void setUp(){
        // set up here
            gameEventHandler = new GameEventHandler();
            controller = new Controller(gameEventHandler);
            gameEventHandler.addEventListener(controller);
    }

    public void testGameStart() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_MODEL_GAME_START.name());
        gameEventHandler.addEventListener(listener);
        GameEvent gameStart = GameEventHandler.makeGameEventwithCommand(GameEventType.VIEW_TO_CONTROLLER_GAME_START.name(), new TestCommand(new File("doc/plan/data/TestInitialBoard.json")));
        gameEventHandler.publish(gameStart);
        System.out.println("GameStart event published!");
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
                ParsedProperty[] properties = (ParsedProperty[]) event.getGameEventCommand().getCommand().getCommandArgs();
                for (ParsedProperty property : properties) {
                    System.out.println(property.id());
                    System.out.println(property.type());
                }
            }
        }
    }
}
