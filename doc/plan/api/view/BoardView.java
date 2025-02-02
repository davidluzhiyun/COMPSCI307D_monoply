package view;

/**
 * The actual board arrangement displayed to the user; contains property names, player icons, etc.
 */
public interface BoardView {


  /**
   * Arranges the board based on info from controller. Initializes players on the GO cell.
   */
  public void setUpBoard();

  /**
   * Moves a player to a designated spot on the board. (Should probably also display the result of
   * their dice roll/how far they are being moved).
   */
  public void movePlayer();

  /**
   * Add a "house" to a specified property/location within the board.
   */
  public void buildHouse();

  /**
   * Through some sort of color change or addition of an icon, can indicate that a property has been
   * bought by another player.
   */
  public void indicateOwnedProperty();

  /**
   * Remove a player's game piece from the board, change the board so that all of the properties
   * they own are now shown to be available
   */
  public void removePlayer();


}
