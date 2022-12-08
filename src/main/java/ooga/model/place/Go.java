package ooga.model.place;


import ooga.event.GameEventHandler;
import ooga.model.PlaceAction;

import java.util.Collection;
import ooga.model.Player;

public class Go extends AbstractPlace {

  public Go() {
    super("0");
//    addStationaryAction(StationaryAction.ROLL_DICE);
  }

  @Override
  public double getMoney() {
    return 200;
  }

  @Override
  public void updatePlaceActions(Player player) {
    return;
  }

  @Override
  public void landingEffect(Player player) {
    player.setMoney(player.getTotalMoney() + getMoney());
    GameEventHandler gameEventHandler = new GameEventHandler();
    gameEventHandler.publish("MODEL_TO_MODEL_COLLECT_SALARY");
  }
}
