package ooga.event.eventRunnable;

import ooga.controller.LoadGameStateRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.LoadGameStateCommand;
import ooga.model.ModelOutput;
import ooga.model.place.ControllerPlace;

import java.util.ArrayList;
import java.util.List;

public class LoadGameStateRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public LoadGameStateRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        List<Integer> numHouses = new ArrayList<>();
        for (ControllerPlace place : this.boardInfo.getBoard()) {
            numHouses.add(place.getHouseCount());
        }
        LoadGameStateRecord record = new LoadGameStateRecord(this.boardInfo.getPlayers(), numHouses);
        LoadGameStateCommand command = new LoadGameStateCommand(record);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_LOAD_GAME_STATE.name(), command);
    }
}
