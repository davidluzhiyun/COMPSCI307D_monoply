package ooga.event.eventRunnable;

import ooga.controller.InitBoardRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;

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
//        InitBoardRecord startInfo = new InitBoardRecord(this.boardInfo.getBoard(), this.boardInfo.getStationaryAction(), this.boardInfo.getPlayers(), this.boardInfo.getCurrentPlayer());
//        BoardSetUpCommand setUp = new BoardSetUpCommand(startInfo);
//        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name(), setUp);
        return null;
    }
}
