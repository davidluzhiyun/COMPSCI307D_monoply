package ooga.event.eventRunnable;

import ooga.event.GameEvent;
import ooga.event.command.BuildHouseCommand;
import ooga.model.place.ControllerPlace;

public class BuildHouseRunnable implements EventGenerator{

    private ControllerPlace place;

    public BuildHouseRunnable(ControllerPlace place) {
        this.place = place;
    }
    @Override
    public GameEvent processEvent() {
        //BuildHouseCommand command = new
        return null;
    }
}
