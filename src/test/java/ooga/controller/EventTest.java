package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;

public class EventTest extends TestCase {
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
        GameEvent gameStart = GameEventHandler.makeGameEventwithCommand(GameEventType.VIEW_TO_CONTROLLER_GAME_START.name(), new TestCommand(307));
        gameEventHandler.publish(gameStart);
        System.out.println("GameStart event published!");
    }

    public class TestCommand implements Command{
        private final int d;

        public TestCommand(int d){
            this.d = d;
        }

        @Override
        public Object getCommandArgs() {
            return d;
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
                System.out.println(event.getGameEventCommand().getCommand());
            }
        }
    }
}
