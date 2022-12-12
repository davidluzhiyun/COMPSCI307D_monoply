package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.PayTaxCommand;
import ooga.model.ModelOutput;

public class CollectSalaryRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public CollectSalaryRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        PayTaxCommand command = new PayTaxCommand(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayerId()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_COLLECT_SALARY.name(), command);
    }
}
