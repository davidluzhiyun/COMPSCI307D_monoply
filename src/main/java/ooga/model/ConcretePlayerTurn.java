package ooga.model;

public class ConcretePlayerTurn implements PlayerTurn {
  private int currentPlayerId;
  private int currentPlayerTurnsLeft = 0;
  private int currentPlayerTurnsUsed = 0;
  private final int playerCount;

  public ConcretePlayerTurn(int playerCount) {
    currentPlayerId = 0;
    currentPlayerTurnsLeft = 1;
    this.playerCount = playerCount;
  }

  public int roll() {
    int r1 = (int) ((Math.random() * 6) + 1);
    int r2 = (int) ((Math.random() * 6) + 1);
    currentPlayerTurnsLeft--;
    currentPlayerTurnsUsed++;
    if (r1 == r2)
      currentPlayerTurnsLeft++;
    if (currentPlayerTurnsUsed == 3)
      return -1;
    System.out.printf("%d %d%n", r1,r2);
    return r1 + r2;
  }

  @Override
  public int getCurrentPlayerTurnId() {
    return currentPlayerId;
  }

  public int getCurrentPlayerTurnsLeft() {
    return currentPlayerTurnsLeft;
  }

  @Override
  public void nextTurn() {
    if (currentPlayerId < playerCount)
      currentPlayerId++;
    else
      currentPlayerId = 0;
  }
}
