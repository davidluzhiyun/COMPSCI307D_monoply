package ooga.controller;

import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventListener;
import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.RowsColsCommand;
import ooga.event.eventRunnable.BoardSetUpRunnable;
import ooga.event.eventRunnable.EventGenerator;
import ooga.event.eventRunnable.EventSelector;
import ooga.model.GameState;
import ooga.model.ModelOutput;
import ooga.model.place.ControllerPlace;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Controller implements GameEventListener {

    private final String pattern = ".+CONTROLLER\\w+";

    private static RowsColsRecord dimension;

    private static GameEventHandler gameEventHandler;

    private HashMap<String, Runnable> eventMap;

    private EventSelector eventSelector;

    private static List<ParsedProperty> places;

    public Controller(GameEventHandler gameEventHandler) {
        this.gameEventHandler = gameEventHandler;
        this.eventMap = new HashMap<>();
        this.eventSelector = new EventSelector();
    }

    @Override
    public void onGameEvent(GameEvent event) {
        boolean isControllerEvent = Pattern.matches(pattern, event.getGameEventType());
        if (isControllerEvent) {
            if (event.getGameEventType().equals(GameEventType.CONTROLLER_TO_CONTROLLER_ROW_COL.name())) {
                this.dimension = (RowsColsRecord) event.getGameEventCommand().getCommand().getCommandArgs();
            } else {
                if (event.getGameEventType().equals(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name())) {
                    ModelOutput boardInfo = (ModelOutput) event.getGameEventCommand().getCommand().getCommandArgs();
                    if (boardInfo.getGameState().equals(GameState.GAME_SET_UP)) {
                        this.places = BoardSetUpRunnable.getParsedProperty(boardInfo);
                    }
                }
                EventGenerator eventGenerator = getEventRunnable(event.getGameEventType(), event.getGameEventCommand().getCommand());
                GameEvent toPublish = eventGenerator.processEvent();
                this.gameEventHandler.publish(toPublish);
            }
        }
    }

    public static List<ParsedProperty> getControllerPlaces() {
        return places;
    }

    public static GameEventHandler getGameEventHandler() {
        return gameEventHandler;
    }

    public static RowsColsRecord getDimension() {
        return dimension;
    }

    private EventGenerator getEventRunnable(String eventName, Command command) {
//        String eventSelector;
//        if (eventName.equals(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name())) {
//            ModelOutput modelOutput = (ModelOutput) command.getCommandArgs();
//            eventSelector = modelOutput.getGameState().name();
//        } else {
//            eventSelector = eventName;
//        }
        return this.eventSelector.selectEventRunnable(eventName, command);
    }
}

