package ooga.event.eventRunnable;

import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.ControllerPlace;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the logic/functions that need to occur when the Controller sends the board set up information to the view
 */
public class BoardSetUpRunnable extends ParsingJsonRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    /**@param arguments; should be a model interface from the model**/
    public BoardSetUpRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        InitBoardRecord startInfo = new InitBoardRecord(getParsedProperty(this.boardInfo), this.boardInfo.getStationaryAction(), this.boardInfo.getPlayers(), this.boardInfo.getCurrentPlayer());
        BoardSetUpCommand setUp = new BoardSetUpCommand(startInfo);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_START_GAME.name(), setUp);
    }

    public static List<ParsedProperty> getParsedProperty(ModelOutput boardInfo) {
        List<ParsedProperty> parsedProperties = new ArrayList<>();
        for(ControllerPlace place : boardInfo.getBoard()) {
            try {
                parsedProperties.add(new ParsedProperty(getId(place), getPlaceType(place), getPlaceName(place), place.getColorSetId(), getImage(place)));
            } catch (NoColorAttributeException e) {
                parsedProperties.add(new ParsedProperty(getId(place), getPlaceType(place), getPlaceName(place), -1, getImage(place)));
            }
        }
        return parsedProperties;
    }

    /** Prints out the file read for testing purposes **/
//    private static void printFile(File file) {
//
//        List<String> lines;
//        try {
//            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
//            lines.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
