package ooga.event.command;

import ooga.controller.UpdateViewRecord;

public class UpdateViewCommand implements Command{

    private UpdateViewRecord updateViewRecord;

    public UpdateViewCommand(UpdateViewRecord record) {
        this.updateViewRecord = record;
    }

    @Override
    public Object getCommandArgs() {
        return this.updateViewRecord;
    }
}
