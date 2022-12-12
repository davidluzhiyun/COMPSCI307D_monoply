package ooga.event.eventRunnable;

import ooga.controller.PayRentRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.PayRentCommand;
import ooga.model.ModelOutput;

public class PayRentRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public PayRentRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        PayRentCommand command = new PayRentCommand(new PayRentRecord(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayer()), this.boardInfo.getPlayers().get(this.boardInfo.getBoard().get(this.boardInfo.getQueryIndex()).getOwnerId())));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_PAY_RENT.name(), command);
    }
}
