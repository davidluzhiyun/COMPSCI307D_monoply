package view;

/**
 * A section displaying all relevant info for one player.
 * Maybe we could make a new instance of this every time we start a player's turn?
 */
public interface PlayerInfo {

  /**
   * Edits/updates the information displayed (dynamically). In the future, might want to make
   * a separate method for each type of information displayed.
   */
  public void updateInfo();

}