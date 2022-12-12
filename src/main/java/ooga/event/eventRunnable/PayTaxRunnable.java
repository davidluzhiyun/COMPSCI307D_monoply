package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.PayTaxCommand;
import ooga.model.ModelOutput;

public class PayTaxRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public PayTaxRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        PayTaxCommand command = new PayTaxCommand(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayer()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_PAY_TAX.name(), command);
    }
}
