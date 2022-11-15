package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;

public class RollDiceEventTest extends TestCase {
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

    public void testRollDiceToModel() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_MODEL_ROLL_DICE.name());
        gameEventHandler.addEventListener(listener);
        GameEvent rollDice = GameEventHandler.makeGameEventwithCommand(GameEventType.VIEW_TO_CONTROLLER_ROLL_DICE.name(), null);
        gameEventHandler.publish(rollDice);
        System.out.println("RollDice event published!");
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
                assertEquals(GameEventType.CONTROLLER_TO_MODEL_ROLL_DICE.name(), event.getGameEventType());
                assertNull(event.getGameEventCommand().getCommand());
            }
        }
    }
}
