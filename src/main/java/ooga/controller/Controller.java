package ooga.controller;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.eventRunnable.EventGenerator;
import ooga.event.eventRunnable.EventSelector;

import java.util.HashMap;

public class Controller implements GameEventListener {

    private GameEventHandler gameEventHandler;

    private HashMap<String, Runnable> eventMap;

    private EventSelector eventSelector;

    public Controller(GameEventHandler gameEventHandler) {
        this.gameEventHandler = gameEventHandler;
        this.eventMap = new HashMap<>();
        this.eventSelector = new EventSelector();
    }

    @Override
    public void onGameEvent(GameEvent event) {
        EventGenerator eventGenerator = getEventRunnable(event.getGameEventType(), event.getGameEventCommand().getCommand());
        GameEvent toPublish = eventGenerator.processEvent();
        this.gameEventHandler.publish(toPublish);
    }

    private EventGenerator getEventRunnable(String eventName, Command command) {
        return this.eventSelector.selectEventRunnable(eventName, command);
    }
}

