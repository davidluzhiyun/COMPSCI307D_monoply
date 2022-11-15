package ooga.model;


import java.util.Iterator;

/**
 * Board data for view to initialize the board
 */
public interface ViewBoard extends Iterable<ViewPlace>{
  /**
   * Get the size of the board.
   * @return size of the board
   */
  int getSize();
  /**
   * Get the place at an index.
   * @return the place at an index
   */
  ViewPlace getPlaceAt(int i);

  /**
   * iterable version of the ViewBoard
   * @return iterable version of the ViewBoard
   */
  Iterator<ViewPlace> iterator();
}
