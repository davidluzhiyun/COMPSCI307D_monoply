package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.command.Command;

public class GameStartRunnable implements EventGenerator {

    private static final String eventName = GameEventNames.GAME_START.name();
    private Command command;

    public GameStartRunnable(Command arguments) {
        this.command = arguments;
    }

    @Override
    public GameEvent processEvent() {
        return GameEventHandler.makeGameEventwithCommand(eventName, command);
    }
}
