package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.ControllerPlayer;
import ooga.model.GameState;
import ooga.model.ModelOutput;
import ooga.model.StationaryAction;
import ooga.model.place.ControllerPlace;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class RollDiceEventTest extends TestCase {
    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    private Point diceRoll = new Point(4, 5);

    @Override
    public void setUp(){
        // set up here
        gameEventHandler = new GameEventHandler();
        controller = new Controller(gameEventHandler);
        gameEventHandler.addEventListener(controller);
    }

    public void testRollDiceToModel() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_ROLL_DICE.name());
        gameEventHandler.addEventListener(listener);
        GameEvent rollDice = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name(), new TestCommand(new ModelOutput() {
            @Override
            public GameState getGameState() {
                return GameState.DICE_RESULT;
            }

            @Override
            public Point getDiceNum() {
                return diceRoll;
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
                return null;
            }

            @Override
            public Collection<StationaryAction> getStationaryAction() {
                return null;
            }

            @Override
            public int getQueryIndex() {
                return -1;
            }
        }));
        gameEventHandler.publish(rollDice);
        System.out.println("RollDice event published!");
    }

    public class TestCommand implements Command {

        private final ModelOutput boardInfo;

        public TestCommand(ModelOutput info){
            this.boardInfo = info;
        }

        @Override
        public Object getCommandArgs() {
            return this.boardInfo;
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
                 assertEquals(GameEventType.CONTROLLER_TO_VIEW_ROLL_DICE.name(), event.getGameEventType());
                 assertEquals(diceRoll, event.getGameEventCommand().getCommand().getCommandArgs());
            }
        }
    }
}
