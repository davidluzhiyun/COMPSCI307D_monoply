package ooga.model.place;


import ooga.model.StationaryAction;

import java.util.List;

public class Go extends ConcretePlace{

  public Go() {
    super(0);
    setStationaryActions(List.of(StationaryAction.ROLL_DICE));
  }

  @Override
  public double getMoney() {
    return 200;
  }
}
