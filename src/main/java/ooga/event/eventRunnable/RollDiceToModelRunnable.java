package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;

/**
 * Represents function needed to tell model to simulate dice roll
 */
public class RollDiceToModelRunnable implements EventGenerator{

    public RollDiceToModelRunnable(Command command) {};

    @Override
    public GameEvent processEvent() {
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_MODEL_ROLL_DICE.name(), null);
    }
}
