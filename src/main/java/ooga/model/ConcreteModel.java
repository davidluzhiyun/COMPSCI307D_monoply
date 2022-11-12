package ooga.model;
;

import ooga.model.place.Place;
import ooga.model.place.property.Property;

import java.util.List;

public class ConcreteModel implements Model {
  @Override
  public Object getCommandArgs() {
    return null;
  }

  @Override
  public void publishDice() {
    ViewPlayer currentPlayer = null;
    Place place = null;
    currentPlayer.move(place);
    List<StationaryAction> list;
  }

  public void buyProperty() {
    ViewPlayer currentPlayer = null;
    Property place = null;
    currentPlayer.purchase(place);
  }

  @Override
  public void currentPlayer() {

  }

  @Override
  public void playersData() {

  }

  @Override
  public void boardData() {

  }

  @Override
  public void stationaryAction() {

  }

  @Override
  public void boardUpdateData() {

  }
}
