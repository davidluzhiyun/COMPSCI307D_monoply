package ooga.event;

import ooga.event.command.Command;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GameEventHandler {

  private final Collection<GameEventListener> subscribers;

  public GameEventHandler() {
    subscribers = new ConcurrentLinkedQueue<>();
  }

  public void addEventListener(GameEventListener subscriber) {
    subscribers.add(subscriber);
  }

  public void removeEventListener(GameEventListener subscriber) {
    subscribers.remove(subscriber);
  }

  public void publish(GameEvent e) {
    subscribers.forEach(subscriber -> subscriber.onGameEvent(e));
  }

  public void publish(String s) {
    GameEventType t = getGameEventTypefromString(s);
    publish(new GameEvent(t));
  }

  public void publish(GameEventType gameEventType) {
    publish(new GameEvent(gameEventType));
  }

  public static GameEvent makeGameEventwithCommand(String eventType, Command command) {
    GameEventType t = getGameEventTypefromString(eventType);
    GameEventCommand cmd = new GameEventCommand(t, command);
    return new GameEvent(t, cmd);
  }

  public GameEvent makeGameEventwithCommand(GameEventType t, Command command) {
    GameEventCommand cmd = new GameEventCommand(t, command);
    return new GameEvent(t, cmd);
  }

  public static GameEventType getGameEventTypefromString(String s) {
    return GameEventType.valueOf(s);
  }
}