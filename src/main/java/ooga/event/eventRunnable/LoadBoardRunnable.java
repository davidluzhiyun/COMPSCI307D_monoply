package ooga.event.eventRunnable;

import com.google.gson.internal.LinkedTreeMap;
import ooga.controller.LoadBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.LoadBoardCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadBoardRunnable extends ParsingJsonRunnable implements EventGenerator {

  private File gameConfig;

  private final String META = "meta";

  private final String ROWS = "rows";

  private final String COLS = "columns";

  private final String TYPE = "type";

  private final String ID = "id";

  public LoadBoardRunnable(Command arguments) {
    this.gameConfig = (File) arguments.getCommandArgs();
  }

  @Override
  public GameEvent processEvent() {
    LoadBoardRecord record = makeRecord();
    LoadBoardCommand command = new LoadBoardCommand(record);
    return GameEventHandler.makeGameEventwithCommand(
        GameEventType.CONTROLLER_TO_VIEW_LOAD_BOARD.name(), command);
  }

  private LoadBoardRecord makeRecord() {
    Map<String, LinkedTreeMap> parsedJson = parseJSON(this.gameConfig);
    int rows = 0;
    int cols = 0;
    List<ParsedProperty> places = new ArrayList<>();
    for (Object key : parsedJson.keySet()) {
      if (key.equals(META)) {
        Double r = (Double) parsedJson.get(META).get(ROWS);
        rows = r.intValue();
        Double c = (Double) parsedJson.get(META).get(COLS);
        cols = c.intValue();
      } else {
        String type = (String) parsedJson.get(key).get(TYPE);
        String id = (String) parsedJson.get(key).get(ID);
        String name = getPlaceName(id);
        int colorId = getColorId(id);
        String image = (String) parsedJson.get(key).get("image");
        String fileName = PLACE_PATH + id + JSON_EXTENSION;
        String upperText = getString(fileName, upperTextRegex);
        String lowerText = getString(fileName, lowerTextRegex);
        boolean isCorner = (boolean) parsedJson.get(key).get("corner");
        places.add(
            new ParsedProperty(id, type, name, colorId, image, upperText, lowerText, isCorner));
      }
    }
    return new LoadBoardRecord(rows, cols, places);
  }

  private String getPlaceName(String id) {
    String fileName = PLACE_PATH + id + JSON_EXTENSION;
    return getString(fileName, nameRegex);
  }

  private int getColorId(String id) {
    String fileName = PLACE_PATH + id + JSON_EXTENSION;
    String colorId = getString(fileName, colorRegex);
    if (colorId == null) {
      return -1;
    }
    return Integer.parseInt(getString(fileName, colorRegex));
  }
}
