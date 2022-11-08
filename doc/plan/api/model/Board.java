package model;


import model.place.Place;

public interface Board {
  /**
   * Get the size of the board.
   * @return size of the board
   */
  int getSize();
  /**
   * Get the place at an index.
   * @return the place at an index
   */
  Place getPlaceAt();
}
