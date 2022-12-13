package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GetPlayerCommand;
import ooga.model.ModelOutput;

public class OutOfJailRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public OutOfJailRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        GetPlayerCommand command = new GetPlayerCommand(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayerId()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_OUT_OF_JAIL.name(), command);
    }
}
