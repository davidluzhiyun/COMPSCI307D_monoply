package ooga.event.eventRunnable;

import ooga.event.GameEventType;
import ooga.event.command.Command;
import ooga.model.GameState;
import ooga.model.ModelOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventSelector {

  private final Map<String, Function<Command, ? extends EventGenerator>> eventTypeMap = new HashMap<>();
  private final Map<String, Function<Command, ? extends EventGenerator>> eventTypeMap2 = new HashMap<>();

  public EventSelector() {
    eventTypeMap2.put(GameState.GAME_SET_UP.name(), BoardSetUpRunnable::new);
    eventTypeMap2.put(GameState.DICE_RESULT.name(), RollDiceRunnable::new);
    eventTypeMap2.put(GameState.GET_PLACE_ACTIONS.name(), GetPlaceActionsRunnableToView::new);
    eventTypeMap2.put(GameState.NEXT_PLAYER.name(), PlayerStartRunnable::new);
    eventTypeMap2.put(GameState.BUY_PROPERTY.name(), BuyPropertyRunnable::new);
    eventTypeMap2.put(GameState.MORTGAGE.name(), MortgageRunnable::new);
    eventTypeMap2.put(GameState.MOVE.name(), MoveRunnable::new);
    eventTypeMap2.put(GameState.PAY_RENT.name(), PayRentRunnable::new);
    eventTypeMap2.put(GameState.BUILD_HOUSE.name(), BuildHouseRunnable::new);
    eventTypeMap2.put(GameState.COMMUNITY_CARD.name(), CardRunnable::new);
    eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_GAME_START.name(), GameStartRunnable::new);
    eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_GET_PLACE_INFO.name(), GetPlaceInfoRunnable::new);
    eventTypeMap.put(GameEventType.VIEW_TO_CONTROLLER_LOAD_BOARD.name(), LoadBoardRunnable::new);
    eventTypeMap.put(GameEventType.MODEL_TO_CONTROLLER_GAME_DATA.name(), c -> (eventTypeMap2.get(((ModelOutput) c.getCommandArgs()).getGameState().name()).apply(c)));
  }

  public EventGenerator selectEventRunnable(String eventName, Command command) {
    Function<Command, ? extends EventGenerator> event = findEventType(eventName);
    System.out.println(eventName);
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
