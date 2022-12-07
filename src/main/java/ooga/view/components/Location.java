package ooga.view.components;

public record Location(int row, int col) {

  public boolean equals(Location other) {
    if (other == null) {
      return false;
    }
    return ((this.row() == other.row()) && (this.col() == other.col()));
  }

  @Override
  public String toString() {
    return row + ":" + col;
  }
}
