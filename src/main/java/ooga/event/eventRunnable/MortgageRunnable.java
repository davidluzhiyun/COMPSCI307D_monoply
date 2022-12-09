package ooga.event.eventRunnable;

import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.event.command.GetPlayerCommand;
import ooga.model.ModelOutput;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.ControllerPlace;

import java.util.ArrayList;
import java.util.List;

public class MortgageRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    /**@param arguments; should be a model interface from the model**/
    public MortgageRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        GetPlayerCommand mortgageCmd = new GetPlayerCommand(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayer()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_MORTGAGE.name(), mortgageCmd);
    }

}
