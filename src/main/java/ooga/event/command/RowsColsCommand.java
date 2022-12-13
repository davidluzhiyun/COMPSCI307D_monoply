package ooga.event.command;

import ooga.controller.RowsColsRecord;

public class RowsColsCommand implements Command{

    private RowsColsRecord record;

    public RowsColsCommand(RowsColsRecord record) {
        this.record = record;
    }

    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
