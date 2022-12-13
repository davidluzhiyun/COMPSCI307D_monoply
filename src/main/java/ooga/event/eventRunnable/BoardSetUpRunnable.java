package ooga.event.eventRunnable;

import com.google.gson.internal.LinkedTreeMap;
import ooga.controller.Controller;
import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.controller.RowsColsRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;
import ooga.model.exception.MonopolyException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.ControllerPlace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the logic/functions that need to occur when the Controller sends the board set up
 * information to the view
 */
public class BoardSetUpRunnable extends ParsingJsonRunnable implements EventGenerator {

  private ModelOutput boardInfo;

  private static Map<String, LinkedTreeMap> parsedJson;

  /**
   * @param arguments; should be a model interface from the model
   **/
  public BoardSetUpRunnable(Command arguments) {
    this.boardInfo = (ModelOutput) arguments.getCommandArgs();
  }

  @Override
  public GameEvent processEvent() {
    RowsColsRecord dimension = Controller.getDimension();
    InitBoardRecord startInfo = new InitBoardRecord(dimension.rows(), dimension.cols(),
        getParsedProperty(boardInfo), this.boardInfo.getStationaryAction(),
        this.boardInfo.getPlayers(), this.boardInfo.getCurrentPlayerId());
    BoardSetUpCommand setUp = new BoardSetUpCommand(startInfo);
    return GameEventHandler.makeGameEventwithCommand(
        GameEventType.CONTROLLER_TO_VIEW_START_GAME.name(), setUp);
  }

  public static List<ParsedProperty> getParsedProperty(ModelOutput boardInfo) {
    List<ParsedProperty> parsedProperties = new ArrayList<>();
    parsedJson = parseJSON(Controller.getConfigFile());
    for (ControllerPlace place : boardInfo.getBoard()) {
      try {
        parsedProperties.add(
            new ParsedProperty(getId(place), getPlaceType(place), getPlaceName(place),
                place.getColorSetId(), getImage(place), getUpperText(place), getLowerText(place),
                getCorner(place)));
      } catch (MonopolyException e) {
        parsedProperties.add(
            new ParsedProperty(getId(place), getPlaceType(place), getPlaceName(place), -1,
                getImage(place), getUpperText(place), getLowerText(place), getCorner(place)));
      }
    }
    return parsedProperties;
  }

  private static String getImage(ControllerPlace place) {
    for (String key : parsedJson.keySet()) {
      if (parsedJson.get(key).get("id") != null && parsedJson.get(key).get("id")
          .equals(place.getPlaceId())) {
        return (String) parsedJson.get(key).get("image");
      }
    }
    return null;
  }

  private static boolean getCorner(ControllerPlace place) {
    for (String key : parsedJson.keySet()) {
      if (parsedJson.get(key).get("id") != null && parsedJson.get(key).get("id")
          .equals(place.getPlaceId())) {
        return (boolean) parsedJson.get(key).get("corner");
      }
    }
    return false;
  }
}
