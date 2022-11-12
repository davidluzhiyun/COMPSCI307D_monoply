package ooga.event.eventRunnable;

import ooga.event.GameEventType;
import ooga.event.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EventSelector {

    private final Map<String, Function<Command, ? extends EventGenerator>> eventTypeMap = new HashMap<>();

    public EventSelector() {
        eventTypeMap.putIfAbsent(GameEventType.VIEW_TO_CONTROLLER_GAME_START.name(), GameStartRunnable::new);
    }

    public EventGenerator selectEventRunnable(String eventName, Command command) {
        Function<Command, ? extends EventGenerator> event = findEventType(eventName);
        return event.apply(command);
    }

    private Function<Command, ? extends EventGenerator> findEventType(String eventName) {
        for (GameEventType name : GameEventType.values()) {
            if (name.equals(eventName)) {
                if (eventTypeMap.get(eventName) != null) {
                    return eventTypeMap.get(eventName);
                } else {
                    System.out.println("Event Type not found");
                }
            }
        }
        return null;
    }
}
