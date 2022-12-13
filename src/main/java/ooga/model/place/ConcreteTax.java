package ooga.model.place;

import ooga.event.GameEventHandler;
import ooga.model.GameState;
import ooga.model.Player;

import static ooga.model.component.ConcretePlayerTurn.modelToken;

public class ConcreteTax extends AbstractPlace{
  public ConcreteTax(String id, GameEventHandler gameEventHandler) {
    super(id, gameEventHandler);
  }

  @Override
  public double getMoney(Player player) {
    return -100;
    //TODO:
  }

  @Override
  public void landingEffect(Player player) {
    player.setMoney(player.getTotalMoney() - getMoney(player));
    getGameEventHandler().publish(modelToken + GameState.PAY_TAX);
  }
}
