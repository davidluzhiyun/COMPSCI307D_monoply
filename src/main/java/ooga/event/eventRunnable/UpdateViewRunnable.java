package ooga.event.eventRunnable;

import ooga.controller.UpdateViewRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.UpdateViewCommand;
import ooga.model.ControllerPlayer;
import ooga.model.ModelOutput;
import ooga.model.place.ControllerPlace;

import java.util.List;

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
        UpdateViewRecord updateRecord = new UpdateViewRecord(this.boardInfo.getDiceNum(), getCurrentPlaceIndex(), this.boardInfo.getStationaryAction(), getCurrentPlayer());
        UpdateViewCommand updateView = new UpdateViewCommand(updateRecord);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_ROLL_DICE.name(), updateView);
    }

    private int getCurrentPlaceIndex() {
        ControllerPlayer currentPlayer = getCurrentPlayer();
        for (int i = 0; i < this.boardInfo.getBoard().size(); i++) {
            if (this.boardInfo.getBoard().get(i).getPlayers().contains(currentPlayer)) {
                return i;
            }
        }
        return -1;
    }

    private ControllerPlayer getCurrentPlayer() {
        for (ControllerPlayer player : this.boardInfo.getPlayers()) {
            if (player.getPlayerId() == this.boardInfo.getCurrentPlayer()) {
                return player;
            }
        }
        return null;
    }
}
