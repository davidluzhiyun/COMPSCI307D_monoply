package ooga.controller;

import com.google.gson.internal.LinkedTreeMap;
import junit.framework.TestCase;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;

import java.io.File;
import java.util.Map;

public class GameStartEventTest extends TestCase {
    private GameEventHandler gameEventHandler;

    private Controller controller;

    private MockListener listener;

    private final double expectedIds[] = {0.0, 121.0, 123.0, 125.0};

    private final String[] expectedTypes = {"go", "street", "street", "street"};
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

    public static class TestCommand implements Command{

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
                assertEquals(GameEventType.CONTROLLER_TO_MODEL_GAME_START.name(), event.getGameEventType());
                Map<String, LinkedTreeMap> map = (Map<String, LinkedTreeMap>) event.getGameEventCommand().getCommand().getCommandArgs();
                int i = -1;
                for (String key: map.keySet()) {
                    System.out.println(key);
                    // assertEquals(Integer.toString(i), key);
                    for (Object hashk: map.get(key).keySet()) {
                        if (hashk.equals("players")){
                            assertEquals(4.0, map.get(key).get(hashk));
                        } else if (hashk.equals("rows")){
                            assertEquals(4.0, map.get(key).get(hashk));
                        } else if (hashk.equals("columns")) {
                            assertEquals(5.0, map.get(key).get(hashk));
                        } else if (hashk.equals("id")) {
                            assertEquals(expectedIds[i], map.get(key).get(hashk));
                        } else if (hashk.equals("type")){
                            assertEquals(expectedTypes[i], map.get(key).get(hashk));
                        }
                        System.out.println(hashk);
                        System.out.println(map.get(key).get(hashk));
                    }
                    i++;
                }
            }
        }
    }
}
