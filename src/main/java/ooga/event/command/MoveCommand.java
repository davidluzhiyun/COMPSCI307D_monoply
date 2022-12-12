package ooga.event.command;

import ooga.controller.MoveRecord;

public class MoveCommand implements Command{

    private MoveRecord record;

    public MoveCommand(MoveRecord record) {
        this.record = record;
    }
    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
