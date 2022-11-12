package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.Command;

public class GameStartRunnable implements EventGenerator {
    private Command command;

    public GameStartRunnable(Command arguments) {
        this.command = arguments;
    }

    @Override
    public GameEvent processEvent() {
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_MODEL_GAME_START.name(), command);
    }
}
