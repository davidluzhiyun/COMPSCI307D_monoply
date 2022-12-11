package ooga.event.eventRunnable;

import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventSelector {

    private final Map<String, Function<Command, ? extends EventGenerator>> eventTypeMap = new HashMap<>();

    public EventSelector() {
        eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_GAME_START.name(), GameStartRunnable::new);
        eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_GET_PLACE_INFO.name(), GetPlaceInfoRunnable::new);
        eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_LOAD_BOARD.name(), LoadBoardRunnable::new);
        eventTypeMap.put(GameState.GAME_SET_UP.name(), BoardSetUpRunnable::new);
        eventTypeMap.put(GameState.DICE_RESULT.name(), RollDiceRunnable::new);
        eventTypeMap.put(GameEventType.MODEL_TO_CONTROLLER_GET_PLACE_ACTIONS.name(), GetPlaceActionsRunnableToView::new);
        eventTypeMap.put(GameState.NEXT_PLAYER.name(), PlayerStartRunnable::new);
        eventTypeMap.put(GameState.BUY_PROPERTY.name(), BuyPropertyRunnable::new);
        eventTypeMap.put(GameState.MORTGAGE.name(), MortgageRunnable::new);
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
