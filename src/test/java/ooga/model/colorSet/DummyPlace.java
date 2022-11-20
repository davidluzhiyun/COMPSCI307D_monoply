package ooga.model.colorSet;

import java.util.Collection;
import java.util.List;

import ooga.model.*;
import ooga.model.place.Place;

public class DummyPlace implements Place {
  private int id;

  private int color;
  public DummyPlace(int id, int color){
    this.id = id;
    this.color = color;
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

  @Override
  public int getHousesBuilt() {
    return 0;
  }

  @Override
  public String getType() {
    return "dummy";
  }

  @Override
  public int getColor() {
    return 0;
  }
}
