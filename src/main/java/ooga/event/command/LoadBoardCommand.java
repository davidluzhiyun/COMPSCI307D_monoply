package ooga.event.command;

import ooga.controller.LoadBoardRecord;

public class LoadBoardCommand implements Command{

    private LoadBoardRecord record;

    public LoadBoardCommand(LoadBoardRecord boardRecord) {
        this.record = boardRecord;
    }

    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
