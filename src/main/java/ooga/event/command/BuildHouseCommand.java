package ooga.event.command;

import ooga.controller.BuildHouseRecord;

public class BuildHouseCommand implements Command{

    private BuildHouseRecord record;

    public BuildHouseCommand(BuildHouseRecord record) {
        this.record = record;
    }

    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
