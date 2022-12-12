package ooga.view.components;

import ooga.controller.InitBoardRecord;
import ooga.controller.LoadBoardRecord;
import ooga.controller.ParsedProperty;

public class MonopolyBoardInteractor {

  private final MonopolyBoardViewModel model;

  public MonopolyBoardInteractor(MonopolyBoardViewModel model) {
    this.model = model;
  }

  public void initialize(LoadBoardRecord boardData) {
    initializeCards(boardData);
    initializeRowCol(boardData);
  }

  public void initializeNewBoard(InitBoardRecord data) {
  }

  private void initializeRowCol(LoadBoardRecord boardData) {
    model.setBoardRow(boardData.rows());
    model.setBoardCol(boardData.columns());
    model.update();
  }

  private void initializeCards(LoadBoardRecord boardData) {
    model.getCardModels().addAll(
        boardData.places().stream().map(property -> propertyToCardMapper(property)).toList()
    );
  }

  private MonopolyCardViewModel propertyToCardMapper(ParsedProperty property) {
    return new MonopolyCardViewModel(property.type(), property.name(), property.color());
  }
}
