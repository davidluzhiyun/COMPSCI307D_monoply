package ooga.model.place;


import ooga.model.PlaceAction;

import java.util.Collection;

public class Go extends AbstractPlace {

  public Go() {
    super(0);
  }

  @Override
  public double getMoney() {
    return 200;
  }

  @Override
  public int getHousesNum() {
    return 0;
  }

  @Override
  public Collection<PlaceAction> getPlaceActions() {
    return null;
  }
}
