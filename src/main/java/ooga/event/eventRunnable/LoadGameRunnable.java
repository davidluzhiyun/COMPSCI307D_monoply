package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.command.Command;
import ooga.model.ModelOutput;

public class LoadGameRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public LoadGameRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        return null;
    }
}
