package ooga.controller;

import com.google.gson.internal.LinkedTreeMap;
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
import java.util.List;
import java.util.Map;

public class GetPlaceInfoTest extends TestCase {

    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    List<ControllerPlace> places = new ArrayList<>();

    @Override
    public void setUp(){
        // set up here
        gameEventHandler = new GameEventHandler();
        controller = new Controller(gameEventHandler);
        gameEventHandler.addEventListener(controller);
        places.add(new ControllerPlace() {
            @Override
            public String getPlaceId() {
                return "0";
            }

            @Override
            public Collection<ControllerPlayer> getPlayers() {
                return null;
            }

            @Override
            public double getMoney(Player player) {
                return 0;
            }

            @Override
            public Collection<PlaceAction> getPlaceActions() {
                return null;
            }

            @Override
            public int getHouseCount() throws CannotBuildHouseException {
                return 0;
            }

            @Override
            public int getColorSetId() throws NoColorAttributeException {
                return 0;
            }

            @Override
            public int getOwnerId() {
                return 0;
            }
        });
        places.add(new ControllerPlace() {
            @Override
            public String getPlaceId() {
                return "121";
            }

            @Override
            public Collection<ControllerPlayer> getPlayers() {
                return null;
            }

            @Override
            public double getMoney(Player player) {
                return 0;
            }

            @Override
            public Collection<PlaceAction> getPlaceActions() {
                return null;
            }

            @Override
            public int getHouseCount() throws CannotBuildHouseException {
                return 0;
            }

            @Override
            public int getColorSetId() throws NoColorAttributeException {
                return 0;
            }

            @Override
            public int getOwnerId() {
                return 0;
            }
        });
    }

    public void testGetPlaceInfo() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_INFO.name());
        gameEventHandler.addEventListener(listener);
        GameEvent boardSetUp = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name(), new BoardSetUpTest.TestCommand(new ModelOutput() {
            @Override
            public GameState getGameState() {
                return GameState.GAME_SET_UP;
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
                return null;
            }

            @Override
            public List<ControllerPlace> getBoard() {
                return places;
            }

            @Override
            public Collection<StationaryAction> getStationaryAction() {
                return null;
            }
        }));
        gameEventHandler.publish(boardSetUp);
        GameEvent getPlaceInfo = GameEventHandler.makeGameEventwithCommand(GameEventType.VIEW_TO_CONTROLLER_GET_PLACE_INFO.name(), new TestCommand(0));
        gameEventHandler.publish(getPlaceInfo);
        System.out.println("GetPlaceInfo event published!");
    }

    public class TestCommand implements Command {

        private final int index;

        public TestCommand(int index){
            this.index = index;
        }

        @Override
        public Object getCommandArgs() {
            return this.index;
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
                assertEquals(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_INFO.name(), event.getGameEventType());
                Map<String, LinkedTreeMap> map = (Map<String, LinkedTreeMap>) event.getGameEventCommand().getCommand().getCommandArgs();
                for (String key: map.keySet()) {
                    System.out.println(key);
                        if (key.equals("id")) {
                            assertEquals(0.0, map.get(key));
                        } else if (key.equals("type")){
                            assertEquals("Go", map.get(key));
                        } else if (key.equals("name")) {
                            assertEquals("Go", map.get(key));
                        }
                        System.out.println(map.get(key));
                    }
            }
            }
        }
}

