package ooga.event.eventRunnable;

import ooga.controller.PlaceActionRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.PlaceActionCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;

public class BuyPropertyRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public BuyPropertyRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        PlaceActionCommand command = new PlaceActionCommand(new PlaceActionRecord(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayerId()), this.boardInfo.getQueryIndex()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BUY_PROPERTY.name(), command);
    }
}
