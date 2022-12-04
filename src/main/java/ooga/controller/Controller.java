package ooga.controller;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.eventRunnable.EventGenerator;
import ooga.event.eventRunnable.EventSelector;
import ooga.model.ModelOutput;
import ooga.model.place.ControllerPlace;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Controller implements GameEventListener {

    private final String pattern = ".+CONTROLLER\\w+";

    private GameEventHandler gameEventHandler;

    private HashMap<String, Runnable> eventMap;

    private EventSelector eventSelector;

    private static List<ControllerPlace> places;

    public Controller(GameEventHandler gameEventHandler) {
        this.gameEventHandler = gameEventHandler;
        this.eventMap = new HashMap<>();
        this.eventSelector = new EventSelector();
    }

    @Override
    public void onGameEvent(GameEvent event) {
        boolean isControllerEvent = Pattern.matches(pattern, event.getGameEventType());
        if (isControllerEvent) {
            if (event.getGameEventType().equals(GameEventType.MODEL_TO_CONTROLLER_BOARD_SET_UP.name())) {
                ModelOutput boardInfo = (ModelOutput) event.getGameEventCommand().getCommand().getCommandArgs();
                this.places = boardInfo.getBoard();
            }
            EventGenerator eventGenerator = getEventRunnable(event.getGameEventType(), event.getGameEventCommand().getCommand());
            GameEvent toPublish = eventGenerator.processEvent();
            this.gameEventHandler.publish(toPublish);
        }
    }

    public static List<ControllerPlace> getControllerPlaces() {
        return places;
    }

    private EventGenerator getEventRunnable(String eventName, Command command) {
        return this.eventSelector.selectEventRunnable(eventName, command);
    }
}

