package ooga.event.command;

import ooga.controller.GetPlaceActionsRecord;
import ooga.model.PlaceAction;

import java.util.List;

public class GetPlaceActionsToViewCommand implements Command{

    private GetPlaceActionsRecord record;

    public GetPlaceActionsToViewCommand(GetPlaceActionsRecord record) {
        this.record = record;
    }

    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
