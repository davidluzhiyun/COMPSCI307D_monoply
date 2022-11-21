package ooga.event.eventRunnable;

import ooga.controller.UpdateViewRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.UpdateViewCommand;
import ooga.model.ModelOutput;

/**
 * Represents the logic/functions that need to occur when the Controller is updating the view information
 */
public class UpdateViewRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    /**@param arguments; should be a model interface from the model**/
    public UpdateViewRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        UpdateViewRecord updateRecord = new UpdateViewRecord(this.boardInfo.getDiceNum(), this.boardInfo.getBoard(), this.boardInfo.getStationaryAction(), this.boardInfo.getPlayers(), this.boardInfo.getCurrentPlayer());
        UpdateViewCommand updateView = new UpdateViewCommand(updateRecord);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_UPDATE_DATA.name(), updateView);
    }
}
