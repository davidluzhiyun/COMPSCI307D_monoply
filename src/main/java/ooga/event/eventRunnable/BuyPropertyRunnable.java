package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GetPlayerCommand;
import ooga.model.ModelOutput;

public class BuyPropertyRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public BuyPropertyRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        GetPlayerCommand buyPropertyCommand = new GetPlayerCommand(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayer()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BUY_PROPERTY.name(), buyPropertyCommand);
    }
}
