package ooga.event.eventRunnable;

import ooga.controller.BankruptcyRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BankruptcyCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;

public class BankruptcyRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public BankruptcyRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        BankruptcyCommand command = new BankruptcyCommand(new BankruptcyRecord(this.boardInfo.getBoard(), this.boardInfo.getPlayers()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BANKRUPTCY.name(), command);
    }
}
