package ooga.model.colorSet;

import java.util.Collection;
import java.util.List;

import ooga.model.*;
import ooga.model.place.Place;

class DummyPlace implements Place {
  private int id;
  DummyPlace(int id){
    this.id = id;
  }

  @Override
  public int getPlaceId() {
    return this.id;
  }

  @Override
  public Collection<? extends Player> getPlayers() {
    return null;
  }

  @Override
  public double getMoney() {
    return 0;
  }

  @Override
  public Collection<StationaryAction> getStationaryActions(Player player) {
    return null;
  }


  @Override
  public List<PlaceAction> getPlaceActions(Player player) {
    return null;
  }
}
