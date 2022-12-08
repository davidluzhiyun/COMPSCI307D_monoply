package ooga.view.components;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class MonopolyBoardBuilder implements Builder<Region> {

  private Pane board;
  private Region grid;

  public MonopolyBoardBuilder(MonopolyBoardViewModel model) {
    board = new AnchorPane();
    grid = new MonopolyGridBuilder(model, board).build();
    addCardsToGrid(grid);
  }

  private void addCardsToGrid(Region grid) {

  }

  @Override
  public Region build() {
    return board;
  }
}
