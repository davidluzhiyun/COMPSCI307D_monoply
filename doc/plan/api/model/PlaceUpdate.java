package model;

public interface PlaceUpdate {
  int getOwnerId();
  Collection<Player> getPlayers();
  int getHousesNum();
  Collection<PlaceAction> getPlaceActions();
}
