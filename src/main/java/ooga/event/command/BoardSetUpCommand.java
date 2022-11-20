package ooga.event.command;

import ooga.controller.InitBoardRecord;

public class BoardSetUpCommand implements Command{

    private InitBoardRecord startInfo;

    public BoardSetUpCommand(InitBoardRecord startInfo) {
        this.startInfo = startInfo;
    }

    @Override
    public Object getCommandArgs() {
        return this.startInfo;
    }
}
