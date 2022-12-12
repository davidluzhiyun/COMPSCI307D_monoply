package ooga.event.eventRunnable;

import ooga.controller.MoveRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.MoveCommand;
import ooga.model.ModelOutput;
import ooga.model.StationaryAction;

import java.util.List;

public class MoveRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public MoveRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        MoveCommand command = new MoveCommand(new MoveRecord((List<StationaryAction>) this.boardInfo.getStationaryAction(), this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayerId()).getCurrentPlaceIndex()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_MOVE.name(), command);
    }
}
