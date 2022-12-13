package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GetPlayerCommand;
import ooga.model.ModelOutput;

public class ToJailRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public ToJailRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        GetPlayerCommand command = new GetPlayerCommand(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayerId()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_TO_JAIL.name(), command);
    }
}
