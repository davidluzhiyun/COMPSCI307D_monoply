package ooga.model.place;


import ooga.model.PlaceAction;

import java.util.Collection;
import ooga.model.Player;

public class Go extends AbstractPlace {

  public Go() {
    super(0);
  }

  @Override
  public double getMoney() {
    return 200;
  }

  @Override
  public Collection<PlaceAction> getPlaceActions(Player player) {
    return null;
  }
}
