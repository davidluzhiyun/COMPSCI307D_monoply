package ooga.event.eventRunnable;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import ooga.controller.Controller;
import ooga.controller.RowsColsRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GameStartCommand;
import ooga.event.command.RowsColsCommand;

import java.io.*;
import java.util.Map;

/**
 * GameStartRunnable: class that runs the logic needed when controller listens for
 * the game start event from the view. Parses the config file in order to pass to the
 * model.
 */
public class LoadGameRunnable extends ParsingJsonRunnable implements EventGenerator {

  private File file;

  private Map<String, LinkedTreeMap> parsedJson;

  private final String META = "meta";

  private final String ROWS = "rows";

  private final String COLS = "columns";

  /**
   * Represents the logic/functions that need to occur when Controller receives game start from view
   * @param arguments; should be the input config file
   */
  public LoadGameRunnable(Command arguments) {
    this.file = (File) arguments.getCommandArgs();
  }

  @Override
  public GameEvent processEvent() {
    this.parsedJson = parseJSON(this.file);
    GameStartCommand gameStart = new GameStartCommand(parsedJson);
    returnRowsCols(this.parsedJson);
    return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_MODEL_LOAD_GAME.name(), gameStart);
  }

  private void returnRowsCols(Map<String, LinkedTreeMap> parsedJson) {
    Double r = (Double) ((Map<String, LinkedTreeMap>) parsedJson.get("initConfig")).get(META).get(ROWS);
    int rows = r.intValue();
    Double c = (Double) ((Map<String, LinkedTreeMap>) parsedJson.get("initConfig")).get(META).get(COLS);
    int cols = c.intValue();
    RowsColsCommand command = new RowsColsCommand(new RowsColsRecord(rows, cols));
    Controller.getGameEventHandler().publish(GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_CONTROLLER_ROW_COL.name(), command));
  }

}
