package ooga.model.colorSet;

import java.util.Collection;

import ooga.event.GameEventHandler;
import ooga.model.*;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.Place;

public class DummyPlace implements Place {
  private String id;
  public DummyPlace(String id){
    this.id = id;
  }

  @Override
  public String getPlaceId() {
    return this.id;
  }

  @Override
  public Collection<ControllerPlayer> getPlayers() {
    return null;
  }

  @Override
  public double getMoney(Player player) {
    return 0;
  }

  @Override
  public Collection<StationaryAction> getStationaryActions(Player player) {
    return null;
  }

  @Override
  public Collection<StationaryAction> getPlaceBasedStationaryActions(Player player) {
    return null;
  }


  @Override
  public void updateCurrentPlayerPlaceActions(Player player) {
    return;
  }

  @Override
  public double getPurchasePrice() throws IllegalStateException {
    return 0;
  }

  @Override
  public void setOwner(int playerId, Player owner) throws IllegalStateException {

  }

  @Override
  public void setHouseCount(int count) throws IllegalStateException {

  }

  @Override
  public double getHousePrice() throws IllegalStateException {
    return 0;
  }

  @Override
  public void landingEffect(Player player) {

  }

  @Override
  public GameEventHandler getGameEventHandler() {
    return null;
  }

  @Override
  public Collection<PlaceAction> getPlaceActions() {
    return null;
  }

  @Override
  public int getHouseCount() throws CannotBuildHouseException {
    return 0;
  }

  @Override
  public int getColorSetId() throws NoColorAttributeException {
    return 0;
  }

  @Override
  public int getOwnerId() {
    return 0;
  }
}
