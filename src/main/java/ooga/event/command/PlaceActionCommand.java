package ooga.event.command;

import ooga.controller.PlaceActionRecord;

public class PlaceActionCommand implements Command{

    private PlaceActionRecord record;

    public PlaceActionCommand(PlaceActionRecord record) {
        this.record = record;
    }
    @Override
    public Object getCommandArgs() {
        return this.record;
    }
}
