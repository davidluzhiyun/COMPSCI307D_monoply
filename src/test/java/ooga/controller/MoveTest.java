//package ooga.controller;
//
//import junit.framework.TestCase;
//import ooga.event.GameEvent;
//import ooga.event.GameEventHandler;
//import ooga.event.GameEventListener;
//import ooga.event.GameEventType;
//import ooga.event.command.Command;
//import ooga.model.*;
//import ooga.model.place.ControllerPlace;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class MoveTest extends TestCase {
//
//    private GameEventHandler gameEventHandler;
//
//    private Controller controller;
//
//    private MockListener listener;
//
//    private ControllerPlayer player;
//
//    private List<StationaryAction> actions = new ArrayList<>();
//
//    private List<ControllerPlayer> players = new ArrayList<>();
//
//    @Override
//    public void setUp(){
//        // set up here
//        gameEventHandler = new GameEventHandler();
//        controller = new Controller(gameEventHandler);
//        gameEventHandler.addEventListener(controller);
//        player = new ControllerPlayer() {
//            @Override
//            public int getPlayerId() {
//                return 0;
//            }
//
//            @Override
//            public int getCurrentPlaceIndex() {
//                return 0;
//            }
//
//            @Override
//            public int remainingJailTurns() {
//                return 0;
//            }
//
//            @Override
//            public Collection<Integer> getPropertyIndices() {
//                return null;
//            }
//
//            @Override
//            public double getTotalMoney() {
//                return 0;
//            }
//
//            @Override
//            public boolean hasNextDice() {
//                return false;
//            }
//
//            @Override
//            public int getDice() {
//                return 0;
//            }
//        };
//        players.add(player);
//        actions.add(StationaryAction.ROLL_DICE);
//        actions.add(StationaryAction.BUY_PROPERTY);
//    }
//
//    public void testMove() {
//        listener = new MockListener(GameEventType.CONTROLLER_TO_VIEW_MOVE.name());
//        gameEventHandler.addEventListener(listener);
//        GameEvent move = GameEventHandler.makeGameEventwithCommand(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name(), new TestCommand(new ModelOutput() {
//            @Override
//            public GameState getGameState() {
//                return GameState.MOVE;
//            }
//
//            @Override
//            public Point getDiceNum() {
//                return null;
//            }
//
//            @Override
//            public int getCurrentPlayer() {
//                return player.getPlayerId();
//            }
//
//            @Override
//            public java.util.List<ControllerPlayer> getPlayers() {
//                return players;
//            }
//
//            @Override
//            public List<ControllerPlace> getBoard() {
//                return null;
//            }
//
//            @Override
//            public Collection<StationaryAction> getStationaryAction() {
//                return actions;
//            }
//
//            @Override
//            public int getQueryIndex() {
//                return -1;
//            }
//        }));
//        gameEventHandler.publish(move);
//        System.out.println("Move event published!");
//    }
//
//    public class TestCommand implements Command {
//
//        private final ModelOutput boardInfo;
//
//        public TestCommand(ModelOutput info){
//            this.boardInfo = info;
//        }
//
//        @Override
//        public Object getCommandArgs() {
//            return this.boardInfo;
//        }
//    }
//
//    public class MockListener implements GameEventListener {
//
//        private String eventToTest;
//        public MockListener(String testEvent) {
//            eventToTest = testEvent;
//        }
//        @Override
//        public void onGameEvent(GameEvent event) {
//            if (event.getGameEventType().equals(eventToTest)) {
//                System.out.println("Got game event:");
//                System.out.println(event.getGameEventType());
//                assertEquals(GameEventType.CONTROLLER_TO_VIEW_MOVE.name(), event.getGameEventType());
//                MoveRecord record = new MoveRecord(actions, player.getCurrentPlaceIndex());
//                assertEquals(record, event.getGameEventCommand().getCommand().getCommandArgs());
//            }
//        }
//    }
//
//
//}
