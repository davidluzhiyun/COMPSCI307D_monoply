package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.command.Command;
import ooga.model.Model;

/**
 * Represents the logic/functions that need to occur when the Controller sends the board set up information to the view
 */
public class BoardSetUpRunnable implements EventGenerator{

    private Model boardInfo;

    /**@param arguments; should be a model interface from the model**/
    public BoardSetUpRunnable(Command arguments) {
        this.boardInfo = (Model) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
//        InitBoardRecord startInfo = new InitBoardRecord(this.boardInfo.boardData(), this.boardInfo.stationaryActions(), this.boardInfo.playersData(), this.boardInfo.publishCurrentPlayer());
//        BoardSetUpCommand setUp = new BoardSetUpCommand(startInfo);
//        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name(), setUp);
        return null;
    }
}
