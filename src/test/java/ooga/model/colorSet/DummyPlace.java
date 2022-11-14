package ooga.model.colorSet;

import java.util.Collection;
import ooga.model.ViewPlayer;
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
  public Collection<ViewPlayer> getPlayers() {
    return null;
  }

  @Override
  public double getMoney() {
    return 0;
  }
}
