package ooga.model;


/**
 * Board data for view to update
 */
public interface ViewBoard {
  /**
   * Get the size of the board.
   * @return size of the board
   */
  int getSize();
  /**
   * Get the place at an index.
   * @return the place at an index
   */
  ViewPlace getPlaceAt();
}
