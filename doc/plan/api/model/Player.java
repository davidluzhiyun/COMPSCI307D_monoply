package model;

import com.google.gson.Gson;

public interface Player {

  /**
   * Information about a player, in json format.
   * For example, ALL ZERO-INDEX
   * {
   *   {
   *     "PLAYER_ID: 0,
   *     "CURRENT_SPACE": 2,
   *     "IS_IN_JAIL": true,
   *     "REMAINING_JAIL_TURNS": 2,
   *     "PROPERTIES": 500,
   *     "HOUSES_AND_HOTELS": 600,
   *     "MONEY": 400,
   *     "TOTAL": 1500,
   *   },
   */
  Gson getPlayerInfo();
}
