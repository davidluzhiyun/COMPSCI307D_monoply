package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GetPlaceActionsToViewCommand;
import ooga.model.ModelOutput;
import ooga.model.PlaceAction;

import java.util.Collections;
import java.util.List;

/**
 * Represents the logic/functions that need to occur when the Controller sends the current place actions to the view
 */
public class GetPlaceActionsRunnableToView implements EventGenerator{

    private ModelOutput boardInfo;

    /**@param arguments; should be a model interface from the model**/
    public GetPlaceActionsRunnableToView(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }
    @Override
    public GameEvent processEvent() {
        List<PlaceAction> placeActions = (List<PlaceAction>) this.boardInfo.getBoard().get(this.boardInfo.getQueryIndex()).getPlaceActions();
        Collections.sort(placeActions);
        GetPlaceActionsToViewCommand commandToView = new GetPlaceActionsToViewCommand(placeActions);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS.name(), commandToView);
    }
}
