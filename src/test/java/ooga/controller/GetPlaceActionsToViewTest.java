package ooga.controller;

import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.ControllerPlayer;
import ooga.model.PlaceAction;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.ControllerPlace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GetPlaceActionsToViewTest extends TestCase {

    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    private ArrayList<PlaceAction> expectedActions = new ArrayList<>();

    @Override
    public void setUp(){
        // set up here
        gameEventHandler = new GameEventHandler();
        controller = new Controller(gameEventHandler);
        gameEventHandler.addEventListener(controller);
        expectedActions.add(PlaceAction.VIEW_INFO);
        expectedActions.add(PlaceAction.BUILD_HOUSE);
        Collections.sort(expectedActions);
    }

    public void testGetPlaceActions() {
        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS.name());
        gameEventHandler.addEventListener(listener);
        GameEvent getPlaceActions = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_GET_PLACE_ACTIONS.name(), new TestCommand(new ControllerPlace() {
            @Override
            public String getPlaceId() {
                return "0";
            }

            @Override
            public Collection<ControllerPlayer> getPlayers() {
                return null;
            }

            @Override
            public double getMoney() {
                return 0;
            }

            @Override
            public Collection<PlaceAction> getPlaceActions() {
                ArrayList<PlaceAction> actions = new ArrayList<>();
                actions.add(PlaceAction.BUILD_HOUSE);
                actions.add(PlaceAction.VIEW_INFO);
                Collections.sort(actions);
                return actions;
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
        }));
        gameEventHandler.publish(getPlaceActions);
        System.out.println("GetPlaceActions event published!");
    }

    public class TestCommand implements Command {

        private final ControllerPlace place;

        public TestCommand(ControllerPlace place){
            this.place = place;
        }

        @Override
        public Object getCommandArgs() {
            return this.place;
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
                assertEquals(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS.name(), event.getGameEventType());
                assertEquals(expectedActions, event.getGameEventCommand().getCommand().getCommandArgs());
            }
        }
    }
}
