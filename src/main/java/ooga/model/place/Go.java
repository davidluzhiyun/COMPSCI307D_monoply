package ooga.model.place;


import ooga.model.PlaceAction;
import ooga.model.StationaryAction;

import java.util.Collection;
import java.util.List;

public class Go extends ConcretePlace{

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
