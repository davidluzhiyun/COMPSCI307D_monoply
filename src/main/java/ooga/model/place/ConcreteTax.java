package ooga.model.place;

import ooga.event.GameEventHandler;
import ooga.model.GameState;
import ooga.model.Player;

import static ooga.model.components.ConcretePlayerTurn.modelToken;

public class ConcreteTax extends AbstractPlace{
  public ConcreteTax(String id) {
    super(id);
  }

  @Override
  public double getMoney(Player player) {
    return -100;
    //TODO:
  }

  @Override
  public void landingEffect(Player player) {
    player.setMoney(player.getTotalMoney() - getMoney(player));
    GameEventHandler gameEventHandler = new GameEventHandler();
    gameEventHandler.publish(modelToken + GameState.PAY_TAX);
  }
}
