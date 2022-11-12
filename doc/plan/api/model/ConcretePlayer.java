package model;

import model.place.Place;
import model.place.property.Property;

import java.util.Collection;

public class ConcretePlayer implements Player{
  private double money;
  private Collection<Property> properties;
  @Override
  public int getPlayId() {
    return 0;
  }

  @Override
  public int getCurrentSpace() {
    return 0;
  }

  @Override
  public Boolean isInJail() {
    return null;
  }

  @Override
  public int remainingJailTurns() {
    return 0;
  }

  @Override
  public Collection<Property> getProperties() {
    return null;
  }

  @Override
  public int getTotalMoney() {
    return 0;
  }

  @Override
  public void move(Place place) {
    //Auto part
    money += place.getMoney();
  }

  @Override
  public void purchase(Property place) {
    money -= place.getPurchasePrice();
    properties.add(place);
  }
}
