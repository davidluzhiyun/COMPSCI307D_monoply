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

  private final String IMAGE = "image";

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
        Double id = (Double) parsedJson.get(key).get(ID);
        String name = getPlaceName(Integer.toString(id.intValue()));
        String image = (String) parsedJson.get(key).get(IMAGE);
        int colorId = getColorId(Integer.toString(id.intValue()));
        places.add(new ParsedProperty(Double.toString(id), type, name, colorId, image));
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
