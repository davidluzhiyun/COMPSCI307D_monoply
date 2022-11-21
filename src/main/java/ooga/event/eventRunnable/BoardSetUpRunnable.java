package ooga.event.eventRunnable;

import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;
import ooga.model.place.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the logic/functions that need to occur when the Controller sends the board set up information to the view
 */
public class BoardSetUpRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    /**@param arguments; should be a model interface from the model**/
    public BoardSetUpRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        InitBoardRecord startInfo = new InitBoardRecord(getParsedProperty(), this.boardInfo.getStationaryAction(), this.boardInfo.getPlayers(), this.boardInfo.getCurrentPlayer());
        BoardSetUpCommand setUp = new BoardSetUpCommand(startInfo);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name(), setUp);
    }

    private List<ParsedProperty> getParsedProperty() {
        List<ParsedProperty> parsedProperties = new ArrayList<>();
        for(Place place : this.boardInfo.getBoard()) {
            parsedProperties.add(new ParsedProperty(Integer.toString(place.getPlaceId()), place.getColorSetId())); //TODO: change later
        }
        return parsedProperties;
    }
}
