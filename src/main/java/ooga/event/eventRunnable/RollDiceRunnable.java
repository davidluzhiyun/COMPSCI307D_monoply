package ooga.event.eventRunnable;

import ooga.controller.UpdateViewRecord;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.DiceResultCommand;
import ooga.event.command.UpdateViewCommand;
import ooga.model.ControllerPlayer;
import ooga.model.GameState;
import ooga.model.ModelOutput;

/**
 * Represents the logic/functions that need to occur when the Controller is updating the view information
 */
public class RollDiceRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    /**@param arguments; should be a model interface from the model**/
    public RollDiceRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        DiceResultCommand command = new DiceResultCommand(this.boardInfo.getDiceNum());
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_ROLL_DICE.name(), command);
    }
}
