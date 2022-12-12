package ooga.view.components;

import java.util.List;
import ooga.controller.InitBoardRecord;
import ooga.controller.LoadBoardRecord;
import ooga.controller.ParsedProperty;

public class MonopolyBoardInteractor {

  private final MonopolyBoardViewModel model;

  public MonopolyBoardInteractor(MonopolyBoardViewModel model) {
    this.model = model;
  }

  public void initialize(LoadBoardRecord boardData) {
    initializeCards(boardData.places());
    initializeRowCol(boardData.rows(), boardData.columns());
  }

  public void initializeNewBoard(InitBoardRecord data) {
    initializeCards(data.places());
    initializeRowCol(data.rows(), data.cols());
  }

  private void initializeRowCol(int rows, int cols) {
    model.setBoardRow(rows);
    model.setBoardCol(cols);
    model.update();
  }

  private void initializeCards(List<ParsedProperty> places) {
    model.getCardModels().addAll(
        places.stream().map(property -> propertyToCardMapper(property)).toList()
    );
  }

  private MonopolyCardViewModel propertyToCardMapper(ParsedProperty property) {
    return new MonopolyCardViewModel(property.type(), property.name(), property.color());
  }
}
