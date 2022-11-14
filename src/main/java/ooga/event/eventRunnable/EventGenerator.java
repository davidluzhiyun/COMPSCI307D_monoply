package ooga.event.eventRunnable;

import ooga.event.GameEvent;

public interface EventGenerator {

    /**
     * processEvent: for each type of event, do the logic to do whatever the controller needs
     * to do in order to send it forward
     */
    GameEvent processEvent();
}
