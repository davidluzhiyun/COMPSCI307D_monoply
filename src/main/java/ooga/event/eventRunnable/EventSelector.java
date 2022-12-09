package ooga.event.eventRunnable;

import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.event.command.GetPlaceActionsToViewCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventSelector {

    private final Map<String, Function<Command, ? extends EventGenerator>> eventTypeMap = new HashMap<>();

    public EventSelector() {
        eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_GAME_START.name(), GameStartRunnable::new);
        eventTypeMap.put(GameEventType.VIEW_TO_MODEL_ROLL_DICE.name(), RollDiceToModelRunnable::new);
        eventTypeMap.put(GameEventType.MODEL_TO_CONTROLLER_BOARD_SET_UP.name(), BoardSetUpRunnable::new);
        eventTypeMap.put(GameEventType.MODEL_TO_CONTROLLER_UPDATE_DATA.name(), UpdateViewRunnable::new);
        eventTypeMap.put(GameEventType.MODEL_TO_CONTROLLER_GET_PLACE_ACTIONS.name(), GetPlaceActionsRunnableToView::new);
        eventTypeMap.put(GameEventType.MODEL_TO_CONTROLLER_PLAYER_START.name(), PlayerStartRunnable::new);
        eventTypeMap.put(GameEventType.MODEL_TO_CONTROLLER_BUY_PROPERTY.name(), BuyPropertyRunnable::new);
        eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_GET_PLACE_INFO.name(), GetPlaceInfoRunnable::new);
        eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_LOAD_BOARD.name(), LoadBoardRunnable::new);
    }

    public EventGenerator selectEventRunnable(String eventName, Command command) {
        Function<Command, ? extends EventGenerator> event = findEventType(eventName);
        return event.apply(command);
    }

    private Function<Command, ? extends EventGenerator> findEventType(String eventName) {
        if (eventTypeMap.get(eventName) != null) {
            return eventTypeMap.get(eventName);
        } else {
            System.out.println("Event Type not found");
        }
        return null;
    }
}
