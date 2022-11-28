package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.*;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.ControllerPlace;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlayerStartTest extends TestCase {

    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    List<ControllerPlayer> players = new ArrayList<>();

    @Override
    public void setUp(){
        // set up here
        gameEventHandler = new GameEventHandler();
        controller = new Controller(gameEventHandler);
        gameEventHandler.addEventListener(controller);
        players.add(new ConcretePlayer(0));
        players.add(new ConcretePlayer(1));
    }

    public void testGetPlaceActions() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_PLAYER_START.name());
        gameEventHandler.addEventListener(listener);
        GameEvent playerStart = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_PLAYER_START.name(), new TestCommand(new ModelOutput() {
            @Override
            public Point getDiceNum() {
                return null;
            }

            @Override
            public int getCurrentPlayer() {
                return 1;
            }

            @Override
            public List<ControllerPlayer> getPlayers() {
                return players;
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
        gameEventHandler.publish(playerStart);
        System.out.println("PlayerStart event published!");
    }

    public class TestCommand implements Command{

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
                assertEquals(GameEventType.CONTROLLER_TO_VIEW_PLAYER_START.name(), event.getGameEventType());
                assertEquals(players.get(1), event.getGameEventCommand().getCommand().getCommandArgs());
            }
        }
    }
}
