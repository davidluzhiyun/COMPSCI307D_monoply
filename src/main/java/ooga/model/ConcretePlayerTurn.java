package ooga.model;

public class ConcretePlayerTurn implements PlayerTurn {
  private int currentPlayerId;
  private int currentPlayerTurnsLeft = 0;
  private int currentPlayerTurnsUsed = 0;
  private final int playerCount;

  public ConcretePlayerTurn(int playerCount, int startingPlayerId) {
    currentPlayerId = startingPlayerId;
    currentPlayerTurnsLeft = 1;
    this.playerCount = playerCount;
  }

  @Override
  public int getCurrentPlayerTurnId() {
    return 0;
  }

  public void decrementTurn() {
    currentPlayerTurnsLeft--;
    currentPlayerTurnsUsed++;
  }

  public void addTurn() {
    currentPlayerTurnsLeft++;
  }

  @Override
  public void nextTurn() {
    if (currentPlayerTurnsLeft == 0) {
      if (currentPlayerId < playerCount)
        currentPlayerId++;
      else
        currentPlayerId = 0;
    }
    else
      decrementTurn();
  }
}
