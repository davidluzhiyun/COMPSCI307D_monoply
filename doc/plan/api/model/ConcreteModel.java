package model;

import model.Player;
import model.place.Place;
import model.place.property.Property;

import java.util.List;

public class ConcreteModel implements model.Model {
  @Override
  public Object getCommandArgs() {
    return null;
  }

  @Override
  public void publishDice() {
    Player currentPlayer = null;
    Place place = null;
    currentPlayer.move(place);
    List<StationaryAction> list;
  }

  public void buyProperty() {
    Player currentPlayer = null;
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
