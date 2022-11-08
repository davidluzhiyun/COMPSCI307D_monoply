package model.place;

public interface Jail extends Place{
  /**
   * Get the id of player(s) in jail.
   * @return the id of player(s) in jail
   */
  int getInJailPlayersID();
}
