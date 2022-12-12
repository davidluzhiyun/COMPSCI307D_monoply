package ooga.event.eventRunnable;

import ooga.controller.BuildHouseRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BuildHouseCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;
import ooga.model.place.ControllerPlace;

public class BuildHouseRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public BuildHouseRunnable(Command command) {
        this.boardInfo = (ModelOutput) command.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        BuildHouseCommand command = new BuildHouseCommand(new BuildHouseRecord(this.boardInfo.getPlayers().get(this.boardInfo.getCurrentPlayer()), this.boardInfo.getBoard().get(this.boardInfo.getQueryIndex()).getHouseCount()));
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BUILD_HOUSE.name(), command);
    }
}
