package ooga.event.eventRunnable;

import com.google.gson.Gson;
import javafx.util.Pair;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GameStartCommand;

import java.io.*;
import java.util.Map;

/**
 * GameStartRunnable: class that runs the logic needed when controller listens for
 * the game start event from the view. Parses the config file in order to pass to the
 * model.
 */
public class GameStartRunnable implements EventGenerator {

    private File file;

    private Map<String, Pair<String, ?>> parsedJson;

    public GameStartRunnable(Command arguments) {
        this.file = (File) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        parseJSON();
        GameStartCommand gameStart = new GameStartCommand(parsedJson);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_MODEL_GAME_START.name(), gameStart);
    }

    private void parseJSON() {

        try (Reader reader = new FileReader(file)) {
            // Convert JSON File to Java Object
            parsedJson = new Gson().fromJson(reader, Map.class);
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found1");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException thrown1");
            throw new RuntimeException(e);
        }
    }
}
