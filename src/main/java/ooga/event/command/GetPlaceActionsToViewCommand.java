package ooga.event.command;

import ooga.model.PlaceAction;

import java.util.List;

public class GetPlaceActionsToViewCommand implements Command{

    private List<PlaceAction> placeActions;

    public GetPlaceActionsToViewCommand(List<PlaceAction> actions) {
        this.placeActions = actions;
    }

    @Override
    public Object getCommandArgs() {
        return this.placeActions;
    }
}
