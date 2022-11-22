package ooga.model.place;


import ooga.model.ControllerPlayer;

import java.util.Collection;

public interface Jail extends Place{
  /**
   * Get the id of player(s) in jail.
   * @return the id of player(s) in jail
   */
  Collection<ControllerPlayer> getPlayersInJail();
}
