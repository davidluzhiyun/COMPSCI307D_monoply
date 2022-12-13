package ooga.event.command;

import ooga.controller.LoadGameStateRecord;

public class LoadGameStateCommand implements Command{

    private LoadGameStateRecord record;

    public LoadGameStateCommand(LoadGameStateRecord record) {
        this.record = record;
    }

    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
