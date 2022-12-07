package ooga.event.eventRunnable;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
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
public class GameStartRunnable extends ParsingJsonRunnable implements EventGenerator {

    private File file;

    private Map<String, LinkedTreeMap> parsedJson;

    /**
     * Represents the logic/functions that need to occur when Controller receives game start from view
     * @param arguments; should be the input config file
     */
    public GameStartRunnable(Command arguments) {
        this.file = (File) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        this.parsedJson = parseJSON(this.file);
        GameStartCommand gameStart = new GameStartCommand(parsedJson);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_MODEL_GAME_START.name(), gameStart);
    }

}
