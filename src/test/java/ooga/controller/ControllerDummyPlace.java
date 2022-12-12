package ooga.controller;

import java.util.ArrayList;
import java.util.Collection;

import ooga.model.*;
import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.Place;

public class ControllerDummyPlace implements Place {

  private String id;

  public ControllerDummyPlace(String id) {
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
    public void updateCurrentPlayerPlaceActions(Player player) {
        // do nothing
    }

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
  public void landingEffect(Player player) {

  }

  @Override
  public Collection<PlaceAction> getPlaceActions() {
    ArrayList<PlaceAction> expectedActions = new ArrayList<>();
    expectedActions.add(PlaceAction.VIEW_INFO);
    expectedActions.add(PlaceAction.BUILD_HOUSE);
    return expectedActions;
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
