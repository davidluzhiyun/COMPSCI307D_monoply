package ooga.model.place;


import ooga.model.PlaceAction;

import java.util.Collection;
import ooga.model.Player;

public class Go extends AbstractPlace {

  public Go() {
    super(0);
//    addStationaryAction(StationaryAction.ROLL_DICE);
  }

  @Override
  public double getMoney() {
    return 200;
  }

  @Override
  public Collection<PlaceAction> updatePlaceActions(Player player) {
    return null;
  }
}
