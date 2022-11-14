package ooga.controller;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.command.Command;
import ooga.event.eventRunnable.EventGenerator;
import ooga.event.eventRunnable.EventSelector;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Controller implements GameEventListener {

    private final String pattern = ".+CONTROLLER\\w+";

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
        boolean isControllerEvent = Pattern.matches(pattern, event.getGameEventType());
        if (isControllerEvent) {
            EventGenerator eventGenerator = getEventRunnable(event.getGameEventType(), event.getGameEventCommand().getCommand());
            GameEvent toPublish = eventGenerator.processEvent();
            this.gameEventHandler.publish(toPublish);
        }
    }

    private EventGenerator getEventRunnable(String eventName, Command command) {
        return this.eventSelector.selectEventRunnable(eventName, command);
    }
}

