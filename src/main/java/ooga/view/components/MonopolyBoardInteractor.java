package ooga.view.components;

public class MonopolyBoardInteractor {

  private final MonopolyBoardViewModel model;

  public MonopolyBoardInteractor(MonopolyBoardViewModel model) {
    this.model = model;
    initializeCards();
  }

  private void initializeCards() {
    model.getCardModels().addAll();
  }
}
